package org.esnack24api.esnack24api.customersupport.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class QNAEditDTO {

    private Long qno;
    private String qtitle;
    private String qcontent;
    private String qfilename;

}