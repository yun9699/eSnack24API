package org.esnack24api.esnack24api.cart.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.cart.domain.CartEntity;
import org.esnack24api.esnack24api.cart.domain.CartItemEntity;
import org.esnack24api.esnack24api.cart.dto.AddCartItemDTO;
import org.esnack24api.esnack24api.cart.dto.UpdateCartDTO;
import org.esnack24api.esnack24api.cart.repository.CartItemRepository;
import org.esnack24api.esnack24api.cart.repository.CartRepository;
import org.esnack24api.esnack24api.product.domain.ProductEntity;
import org.esnack24api.esnack24api.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

        Optional<CartItemEntity> cartItem = cartItemRepository.findByProduct(product);

        if (cartItem.isPresent()) {

            cartItem.get().setCiqty(cartItem.get().getCiqty() + addCartItemDTO.getCiqty());

            log.info("--------CartItem add & isPresent: true---------");
            log.info(cartItem.get().toString());

            cartItemRepository.save(cartItem.get());

            return;
        }

        CartItemEntity cartItemEntity = CartItemEntity.builder()
                .product(product)
                .cart(cart)
                .ciqty(addCartItemDTO.getCiqty())
                .build();

        cartItemRepository.save(cartItemEntity);
    }

    public void updateCartItem(Long cno, UpdateCartDTO updateCartDTO) {

        CartEntity cart = cartRepository.findById(cno).orElseThrow();

        cartItemRepository.deleteAllByCart(cart);

        for (int i = 0; i < updateCartDTO.getPnos().length; i++) {

            ProductEntity product = productRepository.findById(updateCartDTO.getPnos()[i]).orElseThrow();

            CartItemEntity tmp = CartItemEntity.builder()
                    .ciqty(updateCartDTO.getCiqtys()[i])
                    .cart(cart)
                    .product(product)
                    .build();

            cartItemRepository.save(tmp);
        }
    }
}
