package org.esnack24api.esnack24api.allergy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.allergy.dto.KoAllergyListDTO;
import org.esnack24api.esnack24api.allergy.mapper.AllergyMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class AllergyService {

    private final AllergyMapper allergyMapper;

    public List<KoAllergyListDTO> getKoAllergyList() {

        return allergyMapper.getKoAllergyList();
    }
}
