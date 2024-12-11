package org.esnack24api.esnack24api.cart.service;

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

            CartItemEntity cartItemEntity = cartItem.get();

            cartItem.get().setCiqty(cartItem.get().getCiqty() + addCartItemDTO.getCiqty());

            log.info("--------CartItem add & isPresent: true---------");
            log.info(cartItem.get().toString());

            cartItemRepository.save(cartItem.get());
        } else {

            CartItemEntity cartItemEntity = CartItemEntity.builder()
                    .product(product)
                    .cart(cart)
                    .ciqty(addCartItemDTO.getCiqty())
                    .build();

            cartItemRepository.save(cartItemEntity);
        }
    }

    public void incQtyService(Long cino) {

        CartItemEntity cartItem = cartItemRepository.findById(cino).orElseThrow();

        cartItem.setCiqty(cartItem.getCiqty() + 1);

        cartItemRepository.save(cartItem);
    }

    public String decQtyService(Long cino) {

        CartItemEntity cartItem = cartItemRepository.findById(cino).orElseThrow();

        if(cartItem.getCiqty() > 1) {

            cartItem.setCiqty(cartItem.getCiqty() - 1);

            cartItemRepository.save(cartItem);

            return "Successfully decreased qty";
        }

        return "Can't decreased qty";
    }

    public void deleteCartItemService(Long cino) {

        CartItemEntity cartItem = cartItemRepository.findById(cino).orElseThrow();

        cartItemRepository.delete(cartItem);
    }
}
