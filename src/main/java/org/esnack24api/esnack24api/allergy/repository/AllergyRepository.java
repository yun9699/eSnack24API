package org.esnack24api.esnack24api.allergy.repository;

import org.esnack24api.esnack24api.allergy.domain.AllergyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergyRepository extends JpaRepository<AllergyEntity, Long> {
}
