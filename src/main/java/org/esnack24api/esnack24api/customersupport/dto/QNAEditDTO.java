package org.esnack24api.esnack24api.customersupport.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QNAEditDTO {

    private Long qno;
    private String qtitle;
    private String qcontent;
    private String qfilename;

}