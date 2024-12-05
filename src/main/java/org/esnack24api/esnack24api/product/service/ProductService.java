package org.esnack24api.esnack24api.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.common.page.PageResponse;
import org.esnack24api.esnack24api.product.domain.ProductEntity;
import org.esnack24api.esnack24api.product.dto.ProductDetailDTO;
import org.esnack24api.esnack24api.product.dto.ProductListDTO;
import org.esnack24api.esnack24api.product.dto.ProductPopularDTO;
import org.esnack24api.esnack24api.product.mapper.ProductMapper;

import org.esnack24api.esnack24api.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

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

    public PageResponse<ProductListDTO> getProductFilterList(Long uno, PageRequest pageRequest) {
        log.info("getProductFilterList");

        PageResponse<ProductListDTO> pageResponse =
                PageResponse.<ProductListDTO>with()
                        .list(productMapper.getFilterList(uno,pageRequest))
                        .total(productMapper.count(pageRequest))
                        .pageRequest(pageRequest)
                        .build();

        return pageResponse;


    }

    public PageResponse<ProductPopularDTO> getPopular(PageRequest pageRequest) {
        log.info("getPopular");

        PageResponse<ProductPopularDTO> pageResponse =
                PageResponse.<ProductPopularDTO>with()
                        .list(productMapper.getPopular(pageRequest))
                        .total(productMapper.count(pageRequest))
                        .pageRequest(pageRequest)
                        .build();

        return pageResponse;
    }







}
