package org.esnack24api.esnack24api.order.repository;

import org.esnack24api.esnack24api.order.domain.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
}
