package org.esnack24api.esnack24api.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEditDTO {

    private Long rno;
    private String rcontent;
    private int rstar;
    private String rimage;

}
