package org.esnack24api.esnack24api.cart.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.cart.domain.Cart;
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
    private final ProductRepository productRepository; // ProductRepository 추가
    private final UserRepository userRepository; // UserRepository 추가



    public void addCart(Product pno, UserEntity uno, int cqty ) {

        Product product = productRepository.findById(pno.getPno())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        UserEntity user = userRepository.findById(uno.getUno())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = Cart.builder()
                .cqty(cqty)
                .product(pno)
                .user(uno)
                .build();

        cartRepository.save(cart);

    }

}
