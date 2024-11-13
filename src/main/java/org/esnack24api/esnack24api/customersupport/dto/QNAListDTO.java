package org.esnack24api.esnack24api.customersupport.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class QNAListDTO {

    private int qno;
    private int uno;
    private String qtitle;
    private LocalDateTime qregdate;
}
