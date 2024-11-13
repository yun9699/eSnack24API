package org.esnack24api.esnack24api.carttests;

import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.cart.repository.CartRepository;
import org.esnack24api.esnack24api.cart.service.CartService;
import org.esnack24api.esnack24api.product.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Log4j2
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // db로 테스트?
public class CartTests {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;


    @Test
    @Transactional
    @Commit
    public void cartAdd() {


    }
}
