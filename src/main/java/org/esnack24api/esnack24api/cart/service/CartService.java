package org.esnack24api.esnack24api.cart.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.cart.domain.CartEntity;
import org.esnack24api.esnack24api.cart.dto.CartAddDTO;
import org.esnack24api.esnack24api.cart.repository.CartRepository;
import org.esnack24api.esnack24api.product.domain.ProductEntity;
import org.esnack24api.esnack24api.user.domain.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class CartService {


    private final CartRepository cartRepository;


    public CartEntity addCart(CartAddDTO cartAddDTO) {

        ProductEntity product = ProductEntity.builder().pno(cartAddDTO.getPno()).build();
        UserEntity userEntity = UserEntity.builder().uno(cartAddDTO.getUno()).build();

        CartEntity cart = CartEntity.builder()
                .product(product)
                .user(userEntity)
                .cqty(cartAddDTO.getCqty())
                .build();

        CartEntity savedCart = cartRepository.save(cart);

        return savedCart;

    }

}
