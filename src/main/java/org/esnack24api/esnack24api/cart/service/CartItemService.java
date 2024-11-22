package org.esnack24api.esnack24api.cart.service;

import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.cart.domain.CartEntity;
import org.esnack24api.esnack24api.cart.domain.CartItemEntity;
import org.esnack24api.esnack24api.cart.dto.AddCartItemDTO;
import org.esnack24api.esnack24api.cart.repository.CartItemRepository;
import org.esnack24api.esnack24api.cart.repository.CartRepository;
import org.esnack24api.esnack24api.product.domain.ProductEntity;
import org.esnack24api.esnack24api.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    public void addCartItem(AddCartItemDTO addCartItemDTO) {

        ProductEntity product =
                productRepository.findById(addCartItemDTO.getPno()).orElseThrow();

        CartEntity cart =
                cartRepository.findById(addCartItemDTO.getCno()).orElseThrow();

        CartItemEntity cartItemEntity = CartItemEntity.builder()
                .product(product)
                .cart(cart)
                .ciqty(addCartItemDTO.getCiqty())
                .build();

        cartItemRepository.save(cartItemEntity);
    }
}
