package org.esnack24api.esnack24api.customersupport.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.common.page.PageResponse;
import org.esnack24api.esnack24api.customersupport.dto.QNADetailDTO;
import org.esnack24api.esnack24api.customersupport.dto.QNAListDTO;
import org.esnack24api.esnack24api.customersupport.domain.QNAEntity;
import org.esnack24api.esnack24api.customersupport.service.CSService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/qna")
@RequiredArgsConstructor
@Log4j2
public class CSController {

    private final CSService csService;

    // QNA 목록 조회
    @GetMapping("/list")
    public ResponseEntity<PageResponse<QNAListDTO>> getQNAList(@RequestParam Long uno, PageRequest pageRequest) {
        log.info("QNA List for user: {}", uno);
        PageResponse<QNAListDTO> response = csService.getQNAList(uno, pageRequest);
        return ResponseEntity.ok(response);
    }

    // QNA 상세 조회 (아코디언 확장 시 사용)
    @GetMapping("/detail/{qno}")
    public ResponseEntity<QNADetailDTO> getQNAOne(@PathVariable Long qno) {
        log.info("QNA details for qno: {}", qno);
        QNADetailDTO qnaDetail = csService.getQNAOne(qno);
        return ResponseEntity.ok(qnaDetail);
    }

//    // QNA 등록
//    @PostMapping("/add")
//    public ResponseEntity<QNADetailDTO> createQNA(@RequestBody QNADetailDTO qnaDetailDTO) {
//        log.info("Creating QNA: {}", qnaDetailDTO);
//        QNAEntity createdQna = csService.createQNA(qnaDetailDTO);
//        return ResponseEntity.ok(convertToDetailDTO(createdQna));
//    }
//
//    // QNA 수정
//    @PutMapping("/edit")
//    public ResponseEntity<QNADetailDTO> updateQNA(@RequestParam Long qno, @RequestBody QNADetailDTO qnaDetailDTO) {
//        log.info("Updating QNA qno: {}", qno);
//        QNAEntity updatedQna = csService.updateQNA(qno, qnaDetailDTO);
//        return ResponseEntity.ok(convertToDetailDTO(updatedQna));
//    }
//
//    // QNA 삭제
//    @DeleteMapping("/delete")
//    public ResponseEntity<Void> deleteQNA(@RequestParam Long qno) {
//        log.info("Deleting QNA qno: {}", qno);
//        csService.deleteQNA(qno);
//        return ResponseEntity.noContent().build();
//    }
//
}
