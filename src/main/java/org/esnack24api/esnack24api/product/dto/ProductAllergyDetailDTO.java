package org.esnack24api.esnack24api.product.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductAllergyDetailDTO {

    private Long pno;
    private int price;
    private String pfilename;
    private String ptitle_ko;
    private String pcontent_ko;
    private String pcategory_ko;

    private List<String> atitle_ko;


}
