package org.esnack24api.esnack24api.order.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailDTO {

    private Long pno;

    private String pfilename;

    private String ptitle_ko;

    private int price;

    private int oiqty;

    private BigDecimal total_amount;
}
