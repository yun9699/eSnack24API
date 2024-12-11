package org.esnack24api.esnack24api.cart.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.cart.dto.AddCartDTO;
import org.esnack24api.esnack24api.cart.dto.AddCartItemDTO;
import org.esnack24api.esnack24api.cart.dto.ListCartDTO;
import org.esnack24api.esnack24api.cart.repository.CartItemRepository;
import org.esnack24api.esnack24api.cart.service.CartItemService;
import org.esnack24api.esnack24api.cart.service.CartService;
import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.common.page.PageResponse;
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
    private final CartItemService cartItemService;
    private final CartItemRepository cartItemRepository;

    @PostMapping("add/{uno}")
    public ResponseEntity<String> addCart(
            @PathVariable Long uno, @RequestBody AddCartDTO addCartDTO) {

        Long cno = cartService.findCno(uno);

        AddCartItemDTO dto = new AddCartItemDTO();

        dto.setPno(addCartDTO.getPno());
        dto.setCno(cno);
        dto.setCiqty(addCartDTO.getCiqty());

        cartItemService.addCartItem(dto);

        return ResponseEntity.ok("Added cart successfully");
    }

    @GetMapping("list/{uno}")
    public ResponseEntity<PageResponse<ListCartDTO>> getCartList(
            @PathVariable Long uno, PageRequest pageRequest) {

        return ResponseEntity.ok(cartService.getCartList(cartService.findCno(uno), pageRequest));
    }

    @PutMapping("incqty/{cino}")
    public ResponseEntity<String> incQty(@PathVariable Long cino) {

        cartItemService.incQtyService(cino);

        return ResponseEntity.ok("Success Increase Qty");
    }

    @PutMapping("decqty/{cino}")
    public ResponseEntity<String> decQty(@PathVariable Long cino) {

        cartItemService.decQtyService(cino);

        return ResponseEntity.ok(cartItemService.decQtyService(cino));
    }

    @DeleteMapping("deleteItem/{cino}")
    public ResponseEntity<String> deleteItem(@PathVariable Long cino) {

        cartItemService.deleteCartItemService(cino);

        return ResponseEntity.ok("Success Delete Item");
    }

    @DeleteMapping("clearItem/{uno}")
    public ResponseEntity<String> clearCart(@PathVariable Long uno) {

        return ResponseEntity.ok(cartService.clearCart(cartService.findCno(uno)));
    }
}
