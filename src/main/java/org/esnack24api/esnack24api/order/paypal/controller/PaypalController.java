package org.esnack24api.esnack24api.order.paypal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.sdk.models.Order;
import lombok.RequiredArgsConstructor;
import org.esnack24api.esnack24api.order.paypal.service.PaypalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/paypal")
@RequiredArgsConstructor
public class PaypalController {

    private final ObjectMapper objectMapper;
    private final PaypalService paypalService;

    @PostMapping("orders")
    public ResponseEntity<Order> createOrder(@RequestBody Map<String, Object> request) {

        try {

            String cart = objectMapper.writeValueAsString(request.get("cart"));

            Order response = paypalService.createOrder(cart);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {

            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("orders/{orderID}/capture")
    public ResponseEntity<Order> captureOrder(@PathVariable String orderID) {

        try {

            Order response = paypalService.captureOrder(orderID);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {

            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
