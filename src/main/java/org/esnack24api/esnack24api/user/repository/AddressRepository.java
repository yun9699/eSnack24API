package org.esnack24api.esnack24api.user.repository;

import org.esnack24api.esnack24api.user.domain.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}
