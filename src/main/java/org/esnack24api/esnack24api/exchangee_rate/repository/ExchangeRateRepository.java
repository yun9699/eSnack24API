package org.esnack24api.esnack24api.exchangee_rate.repository;

import org.esnack24api.esnack24api.exchangee_rate.domain.ExchangeRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRateEntity, Long> {
}
