package org.esnack24api.esnack24api.customersupport.repository;

import org.esnack24api.esnack24api.customersupport.domain.QNAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CSRepository extends JpaRepository<QNAEntity, Long> {

}
