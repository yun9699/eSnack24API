package org.esnack24api.esnack24api.order.repository;

import org.esnack24api.esnack24api.order.domain.OrderCaptureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCaptureRepository extends JpaRepository<OrderCaptureEntity, Long> {
}
