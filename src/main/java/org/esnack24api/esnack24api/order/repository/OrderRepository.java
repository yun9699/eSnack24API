package org.esnack24api.esnack24api.order.repository;

import org.esnack24api.esnack24api.order.domain.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> findByTransactionId(String orderId);
}
