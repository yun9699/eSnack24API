package org.esnack24api.esnack24api.order.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateOrderDTO {

    private Long[] pnos;

    private int[] ciqtys;
}
