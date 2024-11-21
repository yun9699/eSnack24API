package org.esnack24api.esnack24api.cart.dto;

import lombok.Data;

@Data
public class CartListDTO {

    private String ptitle_ko;

    private String ptitle_en;

    private String ptitle_ja;

    private String ptitle_zh;

    private int cqty;

    private int price;
}
