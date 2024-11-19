package org.esnack24api.esnack24api.order.paypal.repository;

import org.esnack24api.esnack24api.order.paypal.domain.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
