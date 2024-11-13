package org.esnack24api.esnack24api.cart.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.cart.domain.Cart;
import org.esnack24api.esnack24api.cart.dto.CartAddDTO;
import org.esnack24api.esnack24api.cart.repository.CartRepository;
import org.esnack24api.esnack24api.product.domain.Product;
import org.esnack24api.esnack24api.product.repository.ProductRepository;
import org.esnack24api.esnack24api.user.domain.UserEntity;
import org.esnack24api.esnack24api.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class CartService {


    private final CartRepository cartRepository;


    public Cart addCart(CartAddDTO cartAddDTO) {

        Product product = Product.builder().pno(cartAddDTO.getPno()).build();
        UserEntity userEntity = UserEntity.builder().uno(cartAddDTO.getUno()).build();

        Cart cart = Cart.builder()
                .product(product)
                .user(userEntity)
                .cqty(cartAddDTO.getCqty())
                .build();

        Cart savedCart = cartRepository.save(cart);

        return savedCart;

    }

}
