package org.esnack24api.esnack24api.review.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReviewDetailDTO {

    private Long rno;
    private Long pno;
    private Long uno;

    private String rcontent;
    private boolean rdelete;
    private int rstar;
    private String rimage;

    private Timestamp rregDate;
    private Timestamp rmodDate;

}
