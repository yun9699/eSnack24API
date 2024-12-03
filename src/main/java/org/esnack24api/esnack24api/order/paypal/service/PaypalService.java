package org.esnack24api.esnack24api.order.paypal.service;

import com.paypal.sdk.PaypalServerSdkClient;
import com.paypal.sdk.authentication.ClientCredentialsAuthModel;
import com.paypal.sdk.controllers.OrdersController;
import com.paypal.sdk.exceptions.ApiException;
import com.paypal.sdk.http.response.ApiResponse;
import com.paypal.sdk.models.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.order.domain.OrderCaptureEntity;
import org.esnack24api.esnack24api.order.domain.OrderEntity;
import org.esnack24api.esnack24api.order.paypal.dto.PaypalOrderDTO;
import org.esnack24api.esnack24api.order.repository.OrderCaptureRepository;
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
import java.text.NumberFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.Locale;

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
    private final OrderCaptureRepository orderCaptureRepository;

    private String exchange(BigDecimal amount) {

        BigDecimal tmp = new BigDecimal("0.000713");

        BigDecimal result = tmp.multiply(amount).setScale(2, RoundingMode.HALF_UP);

        log.info("private String exchange");
        log.info(result.toString());

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

        String total_amount = exchange(order.getTotal_amount());

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

        order.setPaypalOrderId(apiResponse.getResult().getId());
        order.setTotal_amount(new BigDecimal(total_amount));
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

        OrderEntity order = orderRepository.findByPaypalOrderId(orderID).orElseThrow();
        order.setStatus("Complete");

        OrderCaptureEntity orderCapture = OrderCaptureEntity.builder()
                .order(order)
                .capdate(Timestamp.from(Instant.now()))
                .capture_amount(order.getTotal_amount())
                .transaction_id(orderID)
                .capture_status("Complete")
                .build();

        orderRepository.save(order);
        orderCaptureRepository.save(orderCapture);

        return apiResponse.getResult();
    }
}
