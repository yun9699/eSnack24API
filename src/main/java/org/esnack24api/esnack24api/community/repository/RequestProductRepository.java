package org.esnack24api.esnack24api.community.repository;

import org.esnack24api.esnack24api.community.domain.RequestProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestProductRepository extends JpaRepository<RequestProductEntity, Long> {
}
