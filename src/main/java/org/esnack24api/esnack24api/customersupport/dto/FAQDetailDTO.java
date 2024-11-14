package org.esnack24api.esnack24api.customersupport.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FAQDetailDTO {

    private Long fno;
    private Long adno;
    private String ftitle;
    private String fcategory;
    private String fcontent;

}
