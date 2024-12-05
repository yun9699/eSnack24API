package org.esnack24api.esnack24api.exchangee_rate.mapper;

import java.math.BigDecimal;

public interface ExchangeRateMapper {

    BigDecimal getExchangeRate(String target_currency);
}
