package org.esnack24api.esnack24api.order.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class OrderDetailDTO {

    private Long pno;

    private String pfilename;

    private String ptitle_ko;

    private int price;

    private int oiqty;

    private BigDecimal total_amount;

    private Timestamp oregdate;

    private Long ono;
}
