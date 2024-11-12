package org.esnack24api.esnack24api.customerSupport.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class QNAListDTO {

    private int qno;
    private int uno;
    private String qtitle;
    private LocalDateTime qregdate;
}
