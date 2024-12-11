package org.esnack24api.esnack24api.user.repository;

import org.esnack24api.esnack24api.user.domain.AddressEntity;
import org.esnack24api.esnack24api.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    @Query("SELECT a FROM AddressEntity a WHERE a.user.uno = :uno AND a.is_primary = true")
    Optional<AddressEntity> findByUno_cartAddress(@Param("uno") Long uno);
}