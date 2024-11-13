package org.esnack24api.esnack24api.photo.repository;

import org.esnack24api.esnack24api.photo.domain.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

}
