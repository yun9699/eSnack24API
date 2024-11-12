package org.esnack24api.esnack24api.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.esnack24api.esnack24api.user.domain.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
