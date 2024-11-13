package org.esnack24api.esnack24api.customersupport.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class QNADetailDTO {
    private Long qno;
    private Long uno;
    private Long pno;
    private String qtitle;
    private String qcontent;
    private String qanswer;
    private String qfilename;
    private LocalDateTime qregdate;
    private LocalDateTime qmoddate;
}