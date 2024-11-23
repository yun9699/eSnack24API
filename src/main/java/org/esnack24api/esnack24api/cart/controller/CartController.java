package org.esnack24api.esnack24api.cart.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.cart.domain.CartEntity;
import org.esnack24api.esnack24api.cart.dto.AddCartDTO;
import org.esnack24api.esnack24api.cart.dto.AddCartItemDTO;
import org.esnack24api.esnack24api.cart.dto.ListCartDTO;
import org.esnack24api.esnack24api.cart.dto.UpdateCartDTO;
import org.esnack24api.esnack24api.cart.repository.CartItemRepository;
import org.esnack24api.esnack24api.cart.service.CartItemService;
import org.esnack24api.esnack24api.cart.service.CartService;
import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.common.page.PageResponse;
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
    private final CartItemRepository cartItemRepository;

    @PostMapping("add/{uno}")
    public ResponseEntity<String> addCart(
            @PathVariable Long uno, @RequestBody AddCartDTO addCartDTO) {

        cartService.addCart(uno);

        Long cno = cartService.findCno(uno);

        AddCartItemDTO dto = new AddCartItemDTO();

        dto.setPno(addCartDTO.getPno());
        dto.setCno(cno);
        dto.setCiqty(addCartDTO.getCiqty());

        cartItemService.addCartItem(dto);

        return ResponseEntity.ok("Added cart successfully");
    }

    @PostMapping("update/{uno}")
    public ResponseEntity<String> updateCart(
            @PathVariable Long uno, @RequestBody UpdateCartDTO updateCartDTO) {

        Long cno = cartService.findCno(uno);

        cartItemService.updateCartItem(cno, updateCartDTO);

        return ResponseEntity.ok("Updated cart successfully");
    }

    @GetMapping("list/{uno}")
    public ResponseEntity<PageResponse<ListCartDTO>> getCartList(
            @PathVariable Long uno, PageRequest pageRequest) {

        return ResponseEntity.ok(cartService.getCartList(cartService.findCno(uno), pageRequest));
    }
}
