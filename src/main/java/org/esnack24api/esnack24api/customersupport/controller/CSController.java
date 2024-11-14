package org.esnack24api.esnack24api.customersupport.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.common.page.PageResponse;
import org.esnack24api.esnack24api.customersupport.dto.*;
import org.esnack24api.esnack24api.customersupport.service.CSService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Log4j2
@PreAuthorize("permitAll()")
public class CSController {

    private final CSService csService;

// QNAController
    // QNA 목록 조회
    @GetMapping("/qna/list")
    public ResponseEntity<PageResponse<QNAListDTO>> getQNAList(@RequestParam Long uno, PageRequest pageRequest) {
        log.info("getQNAList: {}", uno);

        return ResponseEntity.ok(csService.getQNAList(uno, pageRequest));
    }

    // QNA 상세 조회 (아코디언 확장 시 사용)
    @GetMapping("/qna/detail/{qno}")
    public ResponseEntity<QNADetailDTO> getQNAOne(@PathVariable Long qno) {
        log.info("getQNAOne: {}", qno);

        return ResponseEntity.ok(csService.getQNAOne(qno));
    }

    // QNA 등록
    @PostMapping("/qna/add")
    public ResponseEntity<QNADetailDTO> registerQNA(@RequestBody QNARegisterDTO qnaRegisterDTO) {
        log.info("registerQNA: {}", qnaRegisterDTO);

        return ResponseEntity.ok(csService.registerQNA(qnaRegisterDTO));
    }


    // QNA 수정
    @PutMapping("/qna/edit/{qno}")
    public ResponseEntity<QNADetailDTO> updateQNA(
            @PathVariable Long qno,
            @RequestBody QNAEditDTO qnaEditDTO) {
        log.info("updateQNA: qno={}, dto={}", qno, qnaEditDTO);

        qnaEditDTO.setQno(qno);

        return ResponseEntity.ok(csService.updateQNA(qnaEditDTO));
    }


    // QNA 삭제
    @DeleteMapping("/qna/delete/{qno}")
    public ResponseEntity<Void> deleteQNA(@PathVariable Long qno) {
        log.info("deleteQNA: {}", qno);

        csService.deleteQNA(qno);

        return ResponseEntity.noContent().build();
    }

//FAQController
    // FAQ 목록 조회
    @GetMapping("/faq/list")
    public ResponseEntity<PageResponse<FAQListDTO>> getFAQList(
            PageRequest pageRequest,
            @RequestParam(required = false) String fcategory
    ) {
        log.info("getFAQList category: {}", fcategory);
        return ResponseEntity.ok(csService.getFAQList(fcategory, pageRequest));
    }

    // FAQ 상세 조회
    @GetMapping("/faq/{fno}")
    public ResponseEntity<FAQDetailDTO> getFAQOne(@PathVariable Long fno) {
        log.info("getFAQOne: {}", fno);
        return ResponseEntity.ok(csService.getFAQOne(fno));
    }

}