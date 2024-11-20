package org.esnack24api.esnack24api.product.dto;

import lombok.Data;

@Data
public class ProductPopularDTO {


    private Long pno;
    private Long ppno;
    private String ptitle_ko;
    private String pcontent_ko;

    private int price;
    private String pfilename;


}
