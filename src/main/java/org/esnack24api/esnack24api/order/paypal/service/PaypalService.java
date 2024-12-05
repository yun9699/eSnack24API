package org.esnack24api.esnack24api.order.paypal.service;

import com.paypal.sdk.PaypalServerSdkClient;
import com.paypal.sdk.authentication.ClientCredentialsAuthModel;
import com.paypal.sdk.controllers.OrdersController;
import com.paypal.sdk.exceptions.ApiException;
import com.paypal.sdk.http.response.ApiResponse;
import com.paypal.sdk.models.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.exchangee_rate.mapper.ExchangeRateMapper;
import org.esnack24api.esnack24api.order.domain.OrderEntity;
import org.esnack24api.esnack24api.order.paypal.dto.PaypalOrderDTO;
import org.esnack24api.esnack24api.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.paypal.sdk.Environment;
import org.slf4j.event.Level;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class PaypalService {

    @Value("${PAYPAL.CLIENT.ID}")
    private String PAYPAL_CLIENT_ID;

    @Value("${PAYPAL.CLIENT.SECRET}")
    private String PAYPAL_CLIENT_SECRET;

    private final OrderRepository orderRepository;

    private final ExchangeRateMapper exchangeRateMapper;

    public String exchangeUSD(BigDecimal amount) {

        BigDecimal tmp = new BigDecimal(1000);
        BigDecimal rate = exchangeRateMapper.getExchangeRate("USD")
                .divide(tmp, 6, RoundingMode.HALF_UP);

        BigDecimal result =
                rate.multiply(amount).setScale(2, RoundingMode.HALF_UP);

        return result.toString();
    }

    public PaypalServerSdkClient paypalClient() {
        return new PaypalServerSdkClient.Builder()
                .loggingConfig(builder -> builder
                        .level(Level.DEBUG)
                        .requestConfig(logConfigBuilder -> logConfigBuilder.body(true))
                        .responseConfig(logConfigBuilder -> logConfigBuilder.headers(true)))
                .httpClientConfig(configBuilder -> configBuilder
                        .timeout(0))
                .environment(Environment.SANDBOX)
                .clientCredentialsAuth(new ClientCredentialsAuthModel.Builder(
                        PAYPAL_CLIENT_ID,
                        PAYPAL_CLIENT_SECRET)
                        .build())
                .build();
    }

    public Order createOrder(PaypalOrderDTO paypalOrderDTO) throws IOException, ApiException {

        OrderEntity order = orderRepository.findById(paypalOrderDTO.getOno()).orElseThrow();

        String total_amount = exchangeUSD(order.getTotal_amount());

        OrdersCreateInput ordersCreateInput = new OrdersCreateInput.Builder(
                null,
                new OrderRequest.Builder(
                        CheckoutPaymentIntent.CAPTURE,
                        Arrays.asList(
                                new PurchaseUnitRequest.Builder(
                                        new AmountWithBreakdown.Builder(
                                                paypalOrderDTO.getCurrency(),
                                                total_amount)
                                                .build())
                                        .build()))
                        .build())
                .build();

        OrdersController ordersController = paypalClient().getOrdersController();
        ApiResponse<Order> apiResponse = ordersController.ordersCreate(ordersCreateInput);

        order.setTransactionId(apiResponse.getResult().getId());
        order.setTotal_amount(new BigDecimal(total_amount));
        order.setCurrency("USD");
        orderRepository.save(order);

        return apiResponse.getResult();
    }

    public Order captureOrder(String orderID) throws IOException, ApiException {
        OrdersCaptureInput ordersCaptureInput = new OrdersCaptureInput.Builder(
                orderID,
                null)
                .build();

        OrdersController ordersController = paypalClient().getOrdersController();
        ApiResponse<Order> apiResponse = ordersController.ordersCapture(ordersCaptureInput);

        OrderEntity order = orderRepository.findByTransactionId(orderID).orElseThrow();
        order.setStatus("Complete");
        order.setOcompletedate(Timestamp.from(Instant.now()));
        order.setMethod("Paypal");

        orderRepository.save(order);

        return apiResponse.getResult();
    }
}
