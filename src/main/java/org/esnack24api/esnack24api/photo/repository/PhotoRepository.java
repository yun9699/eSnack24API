package org.esnack24api.esnack24api.photo.repository;

import org.esnack24api.esnack24api.photo.domain.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<PhotoEntity, Long> {

}
