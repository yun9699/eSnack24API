package org.esnack24api.esnack24api.customersupport.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QNARegisterDTO {

    private Long uno;
    private Long pno;
    private String qtitle;
    private String qcontent;
    private String qfilename;

}
