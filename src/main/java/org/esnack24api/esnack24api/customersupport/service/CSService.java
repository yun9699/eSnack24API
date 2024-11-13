package org.esnack24api.esnack24api.customersupport.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.common.page.PageResponse;
import org.esnack24api.esnack24api.customersupport.dto.QNADetailDTO;
import org.esnack24api.esnack24api.customersupport.dto.QNAListDTO;
import org.esnack24api.esnack24api.customersupport.mapper.FAQMapper;
import org.esnack24api.esnack24api.customersupport.mapper.QNAMapper;
import org.esnack24api.esnack24api.customersupport.repository.CSRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class CSService {
    private final QNAMapper qnaMapper;
//    private final FAQMapper faqMapper;
//    private final CSRepository csRepository;

    // QNA 관련 메서드
    public PageResponse<QNAListDTO> getQNAList(Long uno, PageRequest pageRequest) {
        log.info("getQNAList");

        PageResponse<QNAListDTO> pageResponse =
                PageResponse.<QNAListDTO>with()
                        .list(qnaMapper.getList(uno, pageRequest))
                        .total(qnaMapper.count(pageRequest))
                        .pageRequest(pageRequest)
                        .build();

        return pageResponse;
    }

    public QNADetailDTO getQNAOne(Long qno) {
        log.info("getQNAOne");

        QNADetailDTO result = qnaMapper.getOne(qno);

        return result;
    }

    //QNA 등록
    //QNA 수정
    //QNA 삭제

    // FAQ 관련 메서드 아래에 작성
}