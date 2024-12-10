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
    private Long admno;
    private String ftitle;
    private String ftitle_en;
    private String ftitle_ja;
    private String ftitle_zh;
    private String fcategory;
    private String fcontent;
    private String fcontent_en;
    private String fcontent_ja;
    private String fcontent_zh;

}
