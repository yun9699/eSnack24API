package org.esnack24api.esnack24api.customerSupport.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class QNADetailDTO {
    private int qno;
    private int uno;
    private int pno;
    private String qtitle;
    private String qcontent;
    private String qanswer;
    private String qfilename;
    private LocalDateTime qregdate;
    private LocalDateTime qmoddate;
}