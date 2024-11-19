package org.esnack24api.esnack24api.order.paypal.repository;

import org.esnack24api.esnack24api.order.paypal.domain.OrderCaptureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCaptureRepository extends JpaRepository<OrderCaptureEntity, Long> {
}
