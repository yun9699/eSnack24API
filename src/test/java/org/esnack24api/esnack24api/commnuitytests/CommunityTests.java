package org.esnack24api.esnack24api.commnuitytests;

import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.common.page.PageResponse;
import org.esnack24api.esnack24api.community.dto.RequestAllergyDTO;
import org.esnack24api.esnack24api.community.service.RequestAllergyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Log4j2
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CommunityTests {

    @Autowired
    private RequestAllergyService requestAllergyService;

    @Test
    @Transactional
    public void getListTest() {

        PageRequest pageRequest = new PageRequest();

        PageResponse<RequestAllergyDTO> response = requestAllergyService.getRequestAllergyLists(2L, pageRequest);

        log.info("Response: {}", response);
    }

}
