package org.esnack24api.esnack24api.cstests;

import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.common.page.PageResponse;
import org.esnack24api.esnack24api.customersupport.dto.FAQDetailDTO;
import org.esnack24api.esnack24api.customersupport.dto.FAQListDTO;
import org.esnack24api.esnack24api.customersupport.service.CSService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Log4j2
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FAQTests {

    @Autowired
    private CSService csService;

    @Test
    @Transactional
    public void testGetFAQList() {
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPage(1);
        pageRequest.setSize(10);

        PageResponse<FAQListDTO> result = csService.getFAQList("order", pageRequest);
        log.info("FAQ 목록: {}", result);
    }

    @Test
    @Transactional
    public void testGetFAQOne() {
        FAQDetailDTO result = csService.getFAQOne(7L);
        log.info("FAQ 상세: {}", result);
    }

}
