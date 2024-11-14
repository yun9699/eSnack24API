package org.esnack24api.esnack24api.review.repository;

import org.esnack24api.esnack24api.review.domain.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
}
