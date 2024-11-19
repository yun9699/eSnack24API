package org.esnack24api.esnack24api.order.paypal.service;

import com.paypal.sdk.PaypalServerSdkClient;
import com.paypal.sdk.authentication.ClientCredentialsAuthModel;
import com.paypal.sdk.controllers.OrdersController;
import com.paypal.sdk.exceptions.ApiException;
import com.paypal.sdk.http.response.ApiResponse;
import com.paypal.sdk.models.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.paypal.sdk.Environment;
import org.slf4j.event.Level;

import java.io.IOException;
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

    public Order createOrder(String cart) throws IOException, ApiException {
        OrdersCreateInput ordersCreateInput = new OrdersCreateInput.Builder(
                null,
                new OrderRequest.Builder(
                        CheckoutPaymentIntent.CAPTURE,
                        Arrays.asList(
                                new PurchaseUnitRequest.Builder(
                                        new AmountWithBreakdown.Builder(
                                                "USD",
                                                "100.00")
                                                .build())
                                        .build()))
                        .build())
                .build();

        OrdersController ordersController = paypalClient().getOrdersController();
        ApiResponse<Order> apiResponse = ordersController.ordersCreate(ordersCreateInput);

        return apiResponse.getResult();
    }

    public Order captureOrder(String orderID) throws IOException, ApiException {
        OrdersCaptureInput ordersCaptureInput = new OrdersCaptureInput.Builder(
                orderID,
                null)
                .build();

        OrdersController ordersController = paypalClient().getOrdersController();
        ApiResponse<Order> apiResponse = ordersController.ordersCapture(ordersCaptureInput);

        return apiResponse.getResult();
    }
}
