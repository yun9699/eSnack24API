package org.esnack24api.esnack24api.allergytests;

import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.product.service.ProductAllergyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Log4j2
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AllergyTests {

    @Autowired
    private ProductAllergyService productAllergyService;

    @Test
    @Transactional
    @Commit
    public void allergyDetailTests() {

        productAllergyService.productAllergyDetailByFilename("df2b342b-b139-46ba-8654-9b6ae8f2427e.jpg");

    }

}
