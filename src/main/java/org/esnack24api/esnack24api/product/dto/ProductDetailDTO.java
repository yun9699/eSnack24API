package org.esnack24api.esnack24api.product.dto;

import lombok.Data;

@Data
public class ProductDetailDTO {

    private Long pno;
    private int price;
    private boolean pdelete;
    private String pfilename;

    private String ptitle_ko;
    private String pcontent_ko;
    private String pcategory_ko;
}
