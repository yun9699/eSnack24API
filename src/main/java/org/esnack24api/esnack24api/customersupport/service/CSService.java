package org.esnack24api.esnack24api.customersupport.service;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.catalina.User;
import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.common.page.PageResponse;
import org.esnack24api.esnack24api.customersupport.domain.QNAEntity;
import org.esnack24api.esnack24api.customersupport.dto.*;
import org.esnack24api.esnack24api.customersupport.mapper.FAQMapper;
import org.esnack24api.esnack24api.customersupport.mapper.QNAMapper;
import org.esnack24api.esnack24api.customersupport.repository.CSRepository;
import org.esnack24api.esnack24api.product.domain.ProductEntity;
import org.esnack24api.esnack24api.product.repository.ProductRepository;
import org.esnack24api.esnack24api.user.domain.UserEntity;
import org.esnack24api.esnack24api.user.repository.UserRepository;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class CSService {
    private final QNAMapper qnaMapper;
    private final FAQMapper faqMapper;
    private final CSRepository csRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

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
    public String addQNA(QNARegisterDTO qnaRegisterDTO) {

        Optional<ProductEntity> pno = productRepository.findById(qnaRegisterDTO.getPno());
        Optional<UserEntity> uno = userRepository.findById(qnaRegisterDTO.getUno());

        QNAEntity qna = QNAEntity.builder()
                .qno(qnaRegisterDTO.getPno())
                .user(uno.get())
                .product(pno.get())
                .qtitle(qnaRegisterDTO.getQtitle())
                .qcontent(qnaRegisterDTO.getQcontent())
                .qanswer("답변대기")
                .qfilename(qnaRegisterDTO.getQfilename())
                .build();

        csRepository.save(qna);

        return "등록완료";


    }


    private String qtitle;
    private String qcontent;
    private String qanswer;
    private String qfilename;

    private boolean qdelete = false;

    private boolean qstatus = false;

    @CreatedDate
    private LocalDateTime qregdate;

    @LastModifiedDate
    private LocalDateTime qmoddate;


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

        if (qna.getQstatus()) {  // true = 답변완료 상태
            throw new IllegalStateException("답변이 완료된 문의는 삭제할 수 없습니다.");
        }

        qna.deleteQNA();
        csRepository.save(qna);
    }


//FAQ Service
    // FAQ 리스트 조회
    @Transactional(readOnly = true)
    public PageResponse<FAQListDTO> getFAQList(String fcategory, PageRequest pageRequest) {
        log.info("getFAQList category: {}", fcategory);
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