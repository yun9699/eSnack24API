package org.esnack24api.esnack24api.product.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductMainDTO {

    private Long pno;
    private int price;
    private int pqty;
    private boolean pdelete;
    private String pfilename;

    private LocalDateTime pregdate;
    private LocalDateTime pmoddate;

    private String ptitle_ko;
    private String pcontent_ko;
    private String pcategory_ko;


}
