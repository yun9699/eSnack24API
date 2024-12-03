package org.esnack24api.esnack24api.order.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.order.dto.CreateOrderDTO;
import org.esnack24api.esnack24api.order.dto.OrderDetailDTO;
import org.esnack24api.esnack24api.order.dto.OrderViewDTO;
import org.esnack24api.esnack24api.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@Log4j2
@RequiredArgsConstructor
@PreAuthorize("permitAll()")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("create/{uno}")
    public ResponseEntity<Long> createOrder(
            @PathVariable Long uno, @RequestBody CreateOrderDTO createOrderDTO) {

        return ResponseEntity.ok(orderService.createOrder(uno, createOrderDTO));
    }

    @GetMapping("view/{ono}")
    public ResponseEntity<OrderViewDTO> viewOrder(@PathVariable Long ono) {

        return ResponseEntity.ok(orderService.viewOrder(ono));
    }

    @GetMapping("detail/{ono}")
    public ResponseEntity<List<OrderDetailDTO>> orderDetail(@PathVariable Long ono) {

        return ResponseEntity.ok(orderService.orderDetail(ono));
    }
}
