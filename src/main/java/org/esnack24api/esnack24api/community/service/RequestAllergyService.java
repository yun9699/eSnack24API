package org.esnack24api.esnack24api.community.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.common.page.PageResponse;
import org.esnack24api.esnack24api.community.domain.RequestAllergyEntity;
import org.esnack24api.esnack24api.community.dto.RequestAllergyDTO;
import org.esnack24api.esnack24api.community.mapper.RequestAllergyMapper;

import org.esnack24api.esnack24api.community.repository.RequestAllergyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class RequestAllergyService {

    private final RequestAllergyMapper requestAllergyMapper;

    private final RequestAllergyRepository requestAllergyRepository;

    public PageResponse<RequestAllergyDTO> getRequestAllergyLists(Long cano, PageRequest pageRequest) {

        return PageResponse.<RequestAllergyDTO>with()
                .list(requestAllergyMapper.getRequestAllergyList(cano, pageRequest))
                .total(requestAllergyMapper.count(cano))
                .pageRequest(pageRequest)
                .build();
    }

    public RequestAllergyDTO getRequestAllergyDetail(Long cano) {

        return requestAllergyMapper.getRequestAllergyDetail(cano);
    }

    public RequestAllergyDTO registerRequestAllergy(RequestAllergyDTO requestAllergyDTO) {

        RequestAllergyEntity requestAllergy = RequestAllergyEntity.builder()
                .catitle(requestAllergyDTO.getCatitle())
                .caallergy(requestAllergyDTO.getCaallergy())
                .cadelete(false)
                .build();

        RequestAllergyEntity savedRequestAllergy = requestAllergyRepository.save(requestAllergy);
        return requestAllergyMapper.getRequestAllergyDetail(savedRequestAllergy.getCano());
    }

    public RequestAllergyDTO updateRequestAllergy(RequestAllergyDTO requestAllergyDTO) {

        RequestAllergyEntity requestAllergy = requestAllergyRepository.findById(requestAllergyDTO.getCano())
                .orElseThrow(() -> new IllegalArgumentException("알러지 요청을 찾을 수 없습니다." + requestAllergyDTO.getCano()));

        requestAllergy.updateRequestAllergy(
                requestAllergyDTO.getCatitle(),
                requestAllergyDTO.getCaallergy()
        );

        requestAllergyRepository.save(requestAllergy);

        return requestAllergyMapper.getRequestAllergyDetail(requestAllergyDTO.getCano());
    }

    public void deleteRequestAllergy(Long cano) {

        RequestAllergyEntity requestAllergy = requestAllergyRepository.findById(cano)
                .orElseThrow(() -> new IllegalArgumentException("not found" + cano));

        requestAllergy.deleteRequestAllergy();
        requestAllergyRepository.save(requestAllergy);
    }



}
