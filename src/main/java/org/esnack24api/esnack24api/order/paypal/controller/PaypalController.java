package org.esnack24api.esnack24api.order.paypal.controller;


import com.paypal.sdk.models.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.order.paypal.dto.PaypalOrderDTO;
import org.esnack24api.esnack24api.order.paypal.service.PaypalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/paypal")
@RequiredArgsConstructor
@Log4j2
public class PaypalController {

    private final PaypalService paypalService;

    @PostMapping("orders")
    public ResponseEntity<Order> createOrder(@RequestBody PaypalOrderDTO paypalOrderDTO) {

        try {

            Order response = paypalService.createOrder(paypalOrderDTO);

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
