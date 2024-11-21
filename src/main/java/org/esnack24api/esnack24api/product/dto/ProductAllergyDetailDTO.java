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

    private String ptitle_en;
    private String ptitle_ja;
    private String ptitle_zh;
    private String pcontent_en;
    private String pcontent_ja;
    private String pcontent_zh;
    private String pcategory_en;
    private String pcategory_ja;
    private String pcategory_zh;

    private List<String> atitle_en;
    private List<String> atitle_ja;
    private List<String> atitle_zh;


}
