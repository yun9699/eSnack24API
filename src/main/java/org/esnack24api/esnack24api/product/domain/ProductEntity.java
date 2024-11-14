package org.esnack24api.esnack24api.product.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Setter
@Entity
@Table(name = "tbl_Product")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoIncrement로 자동 생성되는 pk
    private Long pno;


    private int price;


    private int pqty;


    private boolean pdelete;


    private String pfilename;


    private Timestamp pregdate;


    private Timestamp pmoddate;

    // ko


    private String ptitle_ko;


    private String pcontent_ko;


    private String pcategory_ko;

    // en


    private String ptitle_en;


    private String pcontent_en;


    private String pcategory_en;

    // ja


    private String ptitle_ja;


    private String pcontent_ja;


    private String pcategory_ja;

    // zh


    private String ptitle_zh;


    private String pcontent_zh;


    private String pcategory_zh;





}
