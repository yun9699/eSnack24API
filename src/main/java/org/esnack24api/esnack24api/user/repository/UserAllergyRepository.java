package org.esnack24api.esnack24api.user.repository;

import org.esnack24api.esnack24api.user.domain.UserAllergyEntity;
import org.esnack24api.esnack24api.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAllergyRepository extends JpaRepository<UserAllergyEntity, Long> {

    List<UserAllergyEntity> findAllByUserUno(Long uno);

    void deleteAllByUser(UserEntity user);
}
