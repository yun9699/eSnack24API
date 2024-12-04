package org.esnack24api.esnack24api.admin.repository;

import org.esnack24api.esnack24api.admin.domain.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

}
