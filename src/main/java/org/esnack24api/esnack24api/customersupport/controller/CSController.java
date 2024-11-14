package org.esnack24api.esnack24api.customersupport.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.common.page.PageResponse;
import org.esnack24api.esnack24api.customersupport.dto.QNADetailDTO;
import org.esnack24api.esnack24api.customersupport.dto.QNAEditDTO;
import org.esnack24api.esnack24api.customersupport.dto.QNAListDTO;
import org.esnack24api.esnack24api.customersupport.dto.QNARegisterDTO;
import org.esnack24api.esnack24api.customersupport.service.CSService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/qna")
@RequiredArgsConstructor
@Log4j2
@PreAuthorize("permitAll()")
public class CSController {

    private final CSService csService;

    // QNA 목록 조회
    @GetMapping("/list")
    public ResponseEntity<PageResponse<QNAListDTO>> getQNAList(@RequestParam Long uno, PageRequest pageRequest) {
        log.info("getQNAList: {}", uno);

        return ResponseEntity.ok(csService.getQNAList(uno, pageRequest));
    }

    // QNA 상세 조회 (아코디언 확장 시 사용)
    @GetMapping("/detail/{qno}")
    public ResponseEntity<QNADetailDTO> getQNAOne(@PathVariable Long qno) {
        log.info("getQNAOne: {}", qno);

        return ResponseEntity.ok(csService.getQNAOne(qno));
    }

    // QNA 등록
    @PostMapping("/add")
    public ResponseEntity<QNADetailDTO> registerQNA(@RequestBody QNARegisterDTO qnaRegisterDTO) {
        log.info("registerQNA: {}", qnaRegisterDTO);

        return ResponseEntity.ok(csService.registerQNA(qnaRegisterDTO));
    }


    // QNA 수정
    @PutMapping("/edit/{qno}")
    public ResponseEntity<QNADetailDTO> updateQNA(
            @PathVariable Long qno,
            @RequestBody QNAEditDTO qnaEditDTO) {
        log.info("updateQNA: qno={}, dto={}", qno, qnaEditDTO);

        qnaEditDTO.setQno(qno);

        return ResponseEntity.ok(csService.updateQNA(qnaEditDTO));
    }


    // QNA 삭제
    @DeleteMapping("/delete/{qno}")
    public ResponseEntity<Void> deleteQNA(@PathVariable Long qno) {
        log.info("deleteQNA: {}", qno);

        csService.deleteQNA(qno);

        return ResponseEntity.noContent().build();
    }


}
