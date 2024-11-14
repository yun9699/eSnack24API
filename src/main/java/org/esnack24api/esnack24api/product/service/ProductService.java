package org.esnack24api.esnack24api.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.common.page.PageResponse;
import org.esnack24api.esnack24api.product.domain.ProductEntity;
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

//    public ProductDetailDTO getProductDetail(Long pno) { // 해당 내용은 productAllergyService의 productAllergyDetail로 사용
//        log.info("getProductDetail");
//
//        ProductDetailDTO result = productMapper.getOne(pno);
//
//        return result;
//    }



    public void saveProductDetail() {
        ProductEntity product = ProductEntity.builder()
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

        ProductEntity product = productRepository.findById(pno).orElseThrow();

        product.setPrice(price);

        productRepository.save(product);


    } // update test







}
