package org.esnack24api.esnack24api.exchangee_rate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.exchangee_rate.mapper.ExchangeRateMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class ExchangeRateService {

    private final ExchangeRateMapper exchangeRateMapper;

    public BigDecimal getExchangeRate(String targetCurrency) {

        BigDecimal tmp = new BigDecimal(1000);

        return exchangeRateMapper.getExchangeRate(targetCurrency)
                .divide(tmp, 6, RoundingMode.HALF_UP);
    }
}
