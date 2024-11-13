package org.esnack24api.esnack24api.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.common.page.PageResponse;
import org.esnack24api.esnack24api.product.domain.Product;
import org.esnack24api.esnack24api.product.dto.ProductDetailDTO;
import org.esnack24api.esnack24api.product.dto.ProductListDTO;
import org.esnack24api.esnack24api.product.mapper.ProductMapper;

import org.esnack24api.esnack24api.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public PageResponse<ProductListDTO> getProductMainList(PageRequest pageRequest) {
        log.info("getProductMainList");

        PageResponse<ProductListDTO> pageResponse =
                PageResponse.<ProductListDTO>with()
                        .list(productMapper.getList(pageRequest))
                        .total(productMapper.count(pageRequest))
                        .pageRequest(pageRequest)
                        .build();

        return pageResponse;


    }

    public ProductDetailDTO getProductDetail(Long pno) {
        log.info("getProductDetail");

        ProductDetailDTO result = productMapper.getOne(pno);

        return result;
    }



    public void saveProductDetail() {
        Product product = Product.builder()
                .pcategory_ko("demo")
                .price(3000)
                .ptitle_ko("cookie")
                .pcontent_ko("ddd")
                .build();

        productRepository.save(product);
    } // register test

    public void deleteProductDetail(Long pno) {

        productRepository.deleteById(pno);

    } // delete test

    public void updateProductDetail(Long pno, int price) {

        Product product = productRepository.findById(pno).orElseThrow();

        product.setPrice(price);

        productRepository.save(product);


    } // update test







}
