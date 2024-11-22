package org.esnack24api.esnack24api.cart.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.cart.domain.CartEntity;
import org.esnack24api.esnack24api.cart.dto.AddCartDTO;
import org.esnack24api.esnack24api.cart.dto.AddCartItemDTO;
import org.esnack24api.esnack24api.cart.service.CartItemService;
import org.esnack24api.esnack24api.cart.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cart")
@Log4j2
@RequiredArgsConstructor
@PreAuthorize("permitAll()")
public class CartController {

    private final CartService cartService;
    private final CartItemService cartItemService;

    @PostMapping("add/{pno}")
    public ResponseEntity<String> addCart(@PathVariable Long pno, @RequestBody AddCartDTO addCartDTO) {

        cartService.addCart(addCartDTO.getUno());

        Long cno = cartService.findCno(addCartDTO.getUno());

        AddCartItemDTO dto = new AddCartItemDTO();

        dto.setPno(pno);
        dto.setCno(cno);
        dto.setCiqty(addCartDTO.getCiqty());

        cartItemService.addCartItem(dto);

        return ResponseEntity.ok("Added cart successfully");
    }
}
