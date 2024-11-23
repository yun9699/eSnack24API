package org.esnack24api.esnack24api.cart.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.cart.domain.CartEntity;
import org.esnack24api.esnack24api.cart.dto.ListCartDTO;
import org.esnack24api.esnack24api.cart.mapper.CartMapper;
import org.esnack24api.esnack24api.cart.repository.CartRepository;
import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.common.page.PageResponse;
import org.esnack24api.esnack24api.user.domain.UserEntity;
import org.esnack24api.esnack24api.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    private final UserRepository userRepository;

    private final CartMapper cartMapper;

    public Long findCno(Long uno) {

        UserEntity userEntity = userRepository.findById(uno).orElseThrow();

        Optional<CartEntity> result = cartRepository.findByUser(userEntity);

        CartEntity cart = result.orElseThrow();

        return cart.getCno();
    }

    public void addCart(Long uno) {

        UserEntity userEntity = userRepository.findById(uno).orElseThrow();

        Optional<CartEntity> result = cartRepository.findByUser(userEntity);

        if(result.isEmpty()) {

            UserEntity user = UserEntity.builder()
                    .uno(uno)
                    .build();

            CartEntity cart = CartEntity.builder()
                    .user(user)
                    .build();

            cartRepository.save(cart);
        }
    }

    public PageResponse<ListCartDTO> getCartList(Long cno, PageRequest pageRequest) {

        PageResponse<ListCartDTO> pageResponse =
                PageResponse.<ListCartDTO>with()
                        .list(cartMapper.getCartList(cno, pageRequest))
                        .total(cartMapper.count(cno))
                        .pageRequest(pageRequest)
                        .build();

        return pageResponse;
    }

}
