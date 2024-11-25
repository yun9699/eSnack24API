package org.esnack24api.esnack24api.search.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.product.mapper.ProductMapper;
import org.esnack24api.esnack24api.search.dto.ProductSearchDTO;
import org.esnack24api.esnack24api.search.mapper.ProductSearchMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class ProductSearchService {

    private final ProductSearchMapper productSearchMapper;

    public List<ProductSearchDTO> getSearchList(String keyword){
        log.info("getSearchList Service");

        return productSearchMapper.getSearchList(keyword);
    }
}
