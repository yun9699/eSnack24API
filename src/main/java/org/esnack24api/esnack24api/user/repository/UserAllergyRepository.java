package org.esnack24api.esnack24api.user.repository;

import org.esnack24api.esnack24api.user.domain.UserAllergyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAllergyRepository extends JpaRepository<UserAllergyEntity, Long> {
}
