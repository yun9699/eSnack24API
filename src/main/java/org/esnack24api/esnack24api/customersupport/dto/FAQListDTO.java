package org.esnack24api.esnack24api.customersupport.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FAQListDTO {

    private Long fno;
    private String ftitle;
    private String fcategory;

}
