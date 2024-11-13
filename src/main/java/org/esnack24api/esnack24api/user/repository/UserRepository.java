package org.esnack24api.esnack24api.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.esnack24api.esnack24api.user.domain.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByUemail(String email);
}
