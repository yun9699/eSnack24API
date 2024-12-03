package org.esnack24api.esnack24api.cart.repository;

import org.esnack24api.esnack24api.cart.domain.CartEntity;
import org.esnack24api.esnack24api.cart.domain.CartItemEntity;
import org.esnack24api.esnack24api.product.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

    Optional<CartItemEntity> findByProduct(ProductEntity product);

    Optional<CartItemEntity> deleteAllByCart(CartEntity cart);
}
