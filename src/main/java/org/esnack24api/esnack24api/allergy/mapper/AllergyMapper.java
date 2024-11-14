package org.esnack24api.esnack24api.allergy.mapper;

import org.esnack24api.esnack24api.allergy.dto.KoAllergyListDTO;

import java.util.List;

public interface AllergyMapper {

    List<KoAllergyListDTO> getKoAllergyList();
}
