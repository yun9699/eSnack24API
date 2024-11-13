package org.esnack24api.esnack24api.cart.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.cart.domain.Cart;
import org.esnack24api.esnack24api.cart.dto.CartAddDTO;
import org.esnack24api.esnack24api.cart.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@Log4j2
@RequiredArgsConstructor
@PreAuthorize("permitAll()")
public class CartController {


    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<Cart> addCart(@RequestBody CartAddDTO cartAddDTO) {

        log.info("add Controller");

        Cart addCart = cartService.addCart(cartAddDTO);


        return ResponseEntity.ok(addCart);
    }

}
