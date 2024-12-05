package org.esnack24api.esnack24api.exchangee_rate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.exchangee_rate.service.ExchangeRateService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/exchange-rate")
@Log4j2
@RequiredArgsConstructor
@PreAuthorize("permitAll()")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    @GetMapping("get")
    public ResponseEntity<BigDecimal> getExchangeRate(@RequestParam String targetCurrency) {

        return ResponseEntity.ok(exchangeRateService.getExchangeRate(targetCurrency));
    }
}
