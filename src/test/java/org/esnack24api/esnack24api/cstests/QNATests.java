package org.esnack24api.esnack24api.cstests;

import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.common.page.PageResponse;
import org.esnack24api.esnack24api.customersupport.dto.QNADetailDTO;
import org.esnack24api.esnack24api.customersupport.dto.QNAEditDTO;
import org.esnack24api.esnack24api.customersupport.dto.QNAListDTO;
import org.esnack24api.esnack24api.customersupport.dto.QNARegisterDTO;
import org.esnack24api.esnack24api.customersupport.service.CSService;
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

    @Test
    @Transactional
    @Commit
    public void testRegisterQNA() {
        QNARegisterDTO dto = QNARegisterDTO.builder()
                .uno(3L)
                .pno(1L)
                .qtitle("테스트 제목")
                .qcontent("테스트 내용")
                .qfilename("test.jpg")
                .build();

        QNADetailDTO result = csService.registerQNA(dto);
        log.info("등록된 QNA: {}", result);
    }

    @Test
    @Transactional
    public void testGetQNAList() {
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPage(1);
        pageRequest.setSize(10);

        PageResponse<QNAListDTO> result = csService.getQNAList(3L, pageRequest);
        log.info("QNA 목록: {}", result);
    }

    @Test
    @Transactional
    public void testGetQNAOne() {
        QNADetailDTO result = csService.getQNAOne(5L);
        log.info("QNA 상세: {}", result);
    }

    @Test
    @Transactional
    @Commit
    public void testUpdateQNA() {
        QNAEditDTO dto = QNAEditDTO.builder()
                .qno(7L)
                .qtitle("업데이트된 제목")
                .qcontent("업데이트된 내용")
                .qfilename("updated.jpg")
                .build();

        QNADetailDTO result = csService.updateQNA(dto);
        log.info("수정된 QNA: {}", result);
    }

    @Test
    @Transactional
    @Commit
    public void testDeleteQNA() {
        csService.deleteQNA(8L);  // 실제 존재하는 QNA 번호
        log.info("QNA 삭제 완료");
    }
}