package org.esnack24api.esnack24api.producttests;


import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.product.repository.ProductRepository;
import org.esnack24api.esnack24api.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Log4j2
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // db로 테스트?
public class ProductTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    @Transactional
    @Commit
    public void RegisterProductTest() {

        productService.saveProductDetail();

    }

    @Test
    @Transactional
    @Commit
    public void DeleteProductTest() {
        productService.deleteProductDetail(74L);
    }

    @Test
    @Transactional
    @Commit
    public void UpdateProductTest() {

        productService.updateProductDetail(1L, 300000000);

    }

    @Test
    @Transactional
    @Commit
    public void listProductTest() {

        Pageable pageable = PageRequest.of(0, 10);

        productRepository.listProducts(pageable);

    }



}
