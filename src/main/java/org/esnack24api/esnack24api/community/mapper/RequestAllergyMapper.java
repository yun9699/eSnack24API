package org.esnack24api.esnack24api.community.mapper;

import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.community.dto.RequestAllergyDTO;


import java.util.List;

public interface RequestAllergyMapper {

    List<RequestAllergyDTO> getRequestAllergyList(Long cano, PageRequest pageRequest);

    int count(Long cano);

    RequestAllergyDTO getRequestAllergyDetail(Long cano);
}
