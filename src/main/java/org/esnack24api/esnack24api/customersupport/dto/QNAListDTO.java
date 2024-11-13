package org.esnack24api.esnack24api.customersupport.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class QNAListDTO {

    private Long qno;
    private Long uno;
    private String qtitle;
    private LocalDateTime qregdate;
}
