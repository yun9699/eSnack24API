package org.esnack24api.esnack24api.allergy.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KoAllergyListDTO {

    private Long ano;

    private String atitle_ko;
}
