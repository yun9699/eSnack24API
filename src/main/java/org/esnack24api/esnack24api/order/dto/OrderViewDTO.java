package org.esnack24api.esnack24api.order.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderViewDTO {

    private BigDecimal total_amount;

    private String currency;
}
