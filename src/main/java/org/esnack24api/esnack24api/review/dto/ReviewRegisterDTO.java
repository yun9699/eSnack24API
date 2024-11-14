package org.esnack24api.esnack24api.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRegisterDTO {

    private Long rno;
    private Long pno;
    private Long uno;

    private String rcontent;
    private int rstar;
    private String rimage;

}
