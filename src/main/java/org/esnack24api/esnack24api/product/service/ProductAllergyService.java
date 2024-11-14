package org.esnack24api.esnack24api.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.product.dto.ProductAllergyDetailDTO;
import org.esnack24api.esnack24api.product.dto.ProductDetailDTO;
import org.esnack24api.esnack24api.product.mapper.ProductAllergyMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class ProductAllergyService {

    private final ProductAllergyMapper productAllergyMapper;

    public ProductAllergyDetailDTO productAllergyDetail(Long pno) {
        log.info("productAllergyDetailDTO");

        ProductAllergyDetailDTO result = productAllergyMapper.getOne(pno);

        return result;
    }
}
