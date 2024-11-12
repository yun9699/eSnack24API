package org.esnack24api.esnack24api.product.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "tbl_product")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoIncrement로 자동 생성되는 pk
    private Long pno;

    @Column(nullable = false) // not null, 200자
    private int price;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int pqty;

    @Column(columnDefinition = "boolean default false")
    private boolean pdelete;

    @Column(length = 200)
    private String pfilename;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp pregdate;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp pmoddate;

    // ko

    @Column(nullable = false, length = 100)
    private String ptitle_ko;

    @Column(nullable = false, length = 300)
    private String pcontent_ko;

    @Column(length = 100)
    private String pcategory_ko;

    // en

    @Column(length = 100)
    private String ptitle_en;

    @Column(length = 300)
    private String pcontent_en;

    @Column(length = 100)
    private String pcategory_en;

    // ja

    @Column(length = 100)
    private String ptitle_ja;

    @Column(length = 300)
    private String pcontent_ja;

    @Column(length = 100)
    private String pcategory_ja;

    // zh

    @Column(length = 100)
    private String ptitle_zh;

    @Column(length = 300)
    private String pcontent_zh;

    @Column(length = 100)
    private String pcategory_zh;





}
