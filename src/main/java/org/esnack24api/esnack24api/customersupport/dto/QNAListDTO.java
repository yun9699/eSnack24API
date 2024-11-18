package org.esnack24api.esnack24api.customersupport.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QNAListDTO {

    private Long qno;
    private Long uno;
    private String qtitle;
    private boolean qstatus;
    private LocalDateTime qregdate;

    private String ptitle_ko;
    private String ptitle_en;
    private String ptitle_ja;
    private String ptitle_zh;
}
