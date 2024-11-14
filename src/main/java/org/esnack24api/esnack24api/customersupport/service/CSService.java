package org.esnack24api.esnack24api.customersupport.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.common.page.PageResponse;
import org.esnack24api.esnack24api.customersupport.domain.QNAEntity;
import org.esnack24api.esnack24api.customersupport.dto.*;
import org.esnack24api.esnack24api.customersupport.mapper.FAQMapper;
import org.esnack24api.esnack24api.customersupport.mapper.QNAMapper;
import org.esnack24api.esnack24api.customersupport.repository.CSRepository;
import org.esnack24api.esnack24api.product.domain.ProductEntity;
import org.esnack24api.esnack24api.user.domain.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
public class CSService {
    private final QNAMapper qnaMapper;
    private final FAQMapper faqMapper;
    private final CSRepository csRepository;

//QNA Service
    // QNA 리스트 조회
    @Transactional(readOnly = true)
    public PageResponse<QNAListDTO> getQNAList(Long uno, PageRequest pageRequest) {
        log.info("getQNAList");
        return PageResponse.<QNAListDTO>with()
                .list(qnaMapper.getList(uno, pageRequest))
                .total(qnaMapper.count(uno))
                .pageRequest(pageRequest)
                .build();
    }

    // QNA 상세 조회
    @Transactional(readOnly = true)
    public QNADetailDTO getQNAOne(Long qno) {
        log.info("getQNAOne: {}", qno);
        QNADetailDTO result = qnaMapper.getOne(qno);
        if (result == null) {
            throw new IllegalArgumentException("QNA not found: " + qno);
        }
        return result;
    }

    // QNA 등록
    @Transactional
    public QNADetailDTO registerQNA(QNARegisterDTO dto) {
        log.info("Registering new QNA: {}", dto);

        // UserEntity, ProductEntity 참조
        UserEntity user = UserEntity.builder()
                .uno(dto.getUno())
                .build();

        ProductEntity product = ProductEntity.builder()
                .pno(dto.getPno())
                .build();

        // QNA 엔티티 생성
        QNAEntity qna = QNAEntity.builder()
                .user(user)
                .product(product)
                .qtitle(dto.getQtitle())
                .qcontent(dto.getQcontent())
                .qfilename(dto.getQfilename())
                .qdelete(false)
                .build();

        // 저장 후 상세 정보 조회하여 반환
        QNAEntity savedQNA = csRepository.save(qna);
        return qnaMapper.getOne(savedQNA.getQno());
    }

    // QNA 수정
    @Transactional
    public QNADetailDTO updateQNA(QNAEditDTO dto) {
        log.info("Updating QNA: {}", dto);

        QNAEntity qna = csRepository.findById(dto.getQno())
                .orElseThrow(() -> new IllegalArgumentException("QNA not found: " + dto.getQno()));

        qna.updateQNA(
                dto.getQtitle(),
                dto.getQcontent(),
                dto.getQfilename()
        );

        csRepository.save(qna);
        return qnaMapper.getOne(qna.getQno());
    }

    // QNA 삭제
    @Transactional
    public void deleteQNA(Long qno) {
        log.info("Deleting QNA: {}", qno);

        QNAEntity qna = csRepository.findById(qno)
                .orElseThrow(() -> new IllegalArgumentException("QNA not found: " + qno));

        qna.deleteQNA();
        csRepository.save(qna);
    }


//FAQ Service
    // FAQ 리스트 조회
    @Transactional(readOnly = true)
    public PageResponse<FAQListDTO> getFAQList(String fcategory, PageRequest pageRequest) {
        log.info("getFAQList");
        return PageResponse.<FAQListDTO>with()
                .list(faqMapper.getList(pageRequest, fcategory))
                .total(faqMapper.count(fcategory))
                .pageRequest(pageRequest)
                .build();
    }

    // FAQ 상세 조회
    @Transactional(readOnly = true)
    public FAQDetailDTO getFAQOne(Long fno) {
        log.info("getFAQOne: {}", fno);
        FAQDetailDTO result = faqMapper.getOne(fno);
        if (result == null) {
            throw new IllegalArgumentException("FAQ not found: " + fno);
        }
        return result;
    }

}