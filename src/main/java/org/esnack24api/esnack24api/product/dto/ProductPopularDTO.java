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

    private String ptitle_en;
    private String ptitle_ja;
    private String ptitle_zh;
    private String pcontent_en;
    private String pcontent_ja;
    private String pcontent_zh;
}
