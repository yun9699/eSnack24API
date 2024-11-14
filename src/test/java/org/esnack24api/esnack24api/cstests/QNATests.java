package org.esnack24api.esnack24api.cstests;

import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.common.page.PageResponse;
import org.esnack24api.esnack24api.customersupport.dto.QNADetailDTO;
import org.esnack24api.esnack24api.customersupport.dto.QNAEditDTO;
import org.esnack24api.esnack24api.customersupport.dto.QNAListDTO;
import org.esnack24api.esnack24api.customersupport.dto.QNARegisterDTO;
import org.esnack24api.esnack24api.customersupport.repository.CSRepository;
import org.esnack24api.esnack24api.customersupport.service.CSService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Log4j2
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class QNATests {

    @Autowired
    private CSService csService;

    private Long testQnaId;

    @BeforeEach
    @Transactional
    @Commit  // 테스트 데이터는 커밋되도록 설정
    void setUp() {
        QNARegisterDTO dto = QNARegisterDTO.builder()
                .uno(1L)
                .pno(1L)
                .qtitle("테스트 문의")
                .qcontent("테스트 내용")
                .qfilename("test.jpg")
                .build();

        QNADetailDTO result = csService.registerQNA(dto);
        testQnaId = result.getQno();
    }


    @Test
    @Transactional
    @Commit
    public void testRegisterQNA() {
        QNARegisterDTO dto = QNARegisterDTO.builder()
                .uno(1L)
                .pno(1L)
                .qtitle("새로운 문의")
                .qcontent("새로운 문의 내용")
                .qfilename("new.jpg")
                .build();

        QNADetailDTO result = csService.registerQNA(dto);
        log.info("등록된 QNA: {}", result);
    }

    @Test
    @Transactional
    @Commit
    public void testUpdateQNA() {
        QNAEditDTO dto = QNAEditDTO.builder()
                .qno(testQnaId)  // BeforeEach에서 생성된 QNA 번호 사용
                .qtitle("수정된 문의")
                .qcontent("수정된 문의 내용")
                .qfilename("updated.jpg")
                .build();

        QNADetailDTO result = csService.updateQNA(dto);
        log.info("수정된 QNA: {}", result);
    }

    @Test
    @Transactional
    public void testGetQNAList() {
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPage(1);
        pageRequest.setSize(10);

        PageResponse<QNAListDTO> result = csService.getQNAList(1L, pageRequest);
        log.info("QNA 목록: {}", result);
    }

    @Test
    @Transactional
    public void testGetQNAOne() {
        QNADetailDTO result = csService.getQNAOne(testQnaId);  // BeforeEach에서 생성된 QNA 번호 사용
        log.info("QNA 상세: {}", result);
    }

    @Test
    @Transactional
    @Commit
    public void testDeleteQNA() {
        csService.deleteQNA(testQnaId);  // BeforeEach에서 생성된 QNA 번호 사용
        log.info("QNA 삭제 완료");
    }
}