package org.esnack24api.esnack24api.cart.repository;

import org.esnack24api.esnack24api.cart.domain.CartEntity;
import org.esnack24api.esnack24api.cart.domain.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

    void deleteAllByCart(CartEntity cart);
}
