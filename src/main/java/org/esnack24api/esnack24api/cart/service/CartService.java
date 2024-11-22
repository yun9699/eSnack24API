package org.esnack24api.esnack24api.cart.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Param;
import org.esnack24api.esnack24api.cart.domain.CartEntity;
import org.esnack24api.esnack24api.cart.dto.CartAddDTO;
import org.esnack24api.esnack24api.cart.dto.CartListDTO;
import org.esnack24api.esnack24api.cart.mapper.CartMapper;
import org.esnack24api.esnack24api.cart.repository.CartRepository;
import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.common.page.PageResponse;
import org.esnack24api.esnack24api.product.domain.ProductEntity;
import org.esnack24api.esnack24api.user.domain.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    private final CartMapper cartMapper;


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

    public PageResponse<CartListDTO> getCartList(@Param("uno") Long uno, @Param("pageRequest") PageRequest pageRequest) {

        PageResponse<CartListDTO> pageResponse =
                PageResponse.<CartListDTO>with()
                        .list(cartMapper.getCartList(uno, pageRequest))
                        .total(cartMapper.count(uno, pageRequest))
                        .pageRequest(pageRequest)
                        .build();

        return pageResponse;
    }

    public String increaseCartItem(Long cno) {

        Optional<CartEntity> result = cartRepository.findById(cno);

        CartEntity cart = result.orElseThrow();

        cart.setCqty(cart.getCqty() + 1);

        cartRepository.save(cart);

        return "Success Increase Cart Item";
    }

    public String decreaseCartItem(Long cno) {

        Optional<CartEntity> result = cartRepository.findById(cno);

        CartEntity cart = result.orElseThrow();

        if(cart.getCqty() > 0) {

            cart.setCqty(cart.getCqty() - 1);

            cartRepository.save(cart);

            return "Success Increase Item";
        }

        return "Cart Item cannot be decreased";
    }

    public String deleteCartItem(Long cno) {

        cartRepository.deleteById(cno);

        return "Success Delete Cart Item";
    }

}
