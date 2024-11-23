package org.esnack24api.esnack24api.cart.dto;

import lombok.Data;

@Data
public class ListCartDTO {

    private Long pno;

    private String ptitle_ko;

    private String ptitle_en;

    private String ptitle_ja;

    private String ptitle_zh;

    private Long ciqty;

    private String price;

    private String pfilename;
}
