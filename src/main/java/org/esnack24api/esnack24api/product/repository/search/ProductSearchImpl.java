package org.esnack24api.esnack24api.product.repository.search;


import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.common.dto.PageRequestDTO;
import org.esnack24api.esnack24api.common.dto.PageResponseDTO;
import org.esnack24api.esnack24api.product.domain.ProductEntity;
import org.esnack24api.esnack24api.product.domain.QProduct;
import org.esnack24api.esnack24api.product.dto.ProductListDTO;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;


@Log4j2
public class ProductSearchImpl extends QuerydslRepositorySupport implements ProductSearch {

    public ProductSearchImpl() {
        super(ProductEntity.class);
    }

    @Override
    public Page<ProductEntity> listProducts(Pageable pageable) {

        QProduct product = QProduct.product;

        JPQLQuery<ProductEntity> query = from(product);
        query.where(product.pno.gt(0));

        this.getQuerydsl().applyPagination(pageable, query);

        List<ProductEntity> productList = query.fetch();

        long total = query.fetchCount();

        return new PageImpl<>(productList, pageable, total);
    }

    @Override
    public PageResponseDTO<ProductListDTO> listProductAll(PageRequestDTO pageRequestDTO) {

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage()-1,
                pageRequestDTO.getSize(),
                Sort.by("pno").descending()
        );

        QProduct product = QProduct.product;
        JPQLQuery<ProductEntity> query = from(product);

        this.getQuerydsl().applyPagination(pageable, query);

        JPQLQuery<ProductListDTO> dtojpqlQuery = query.select(
                Projections.bean(ProductListDTO.class,
                        product.pno,
                        product.price,
                        product.pdelete,
                        product.ptitle_ko,
                        product.pcontent_ko,
                        product.pcategory_ko
                        )
        );


        List<ProductListDTO> list = dtojpqlQuery.fetch();

        long total = dtojpqlQuery.fetchCount();

        return PageResponseDTO.<ProductListDTO>withAll()
                .dtoList(list)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(total)
                .build();
    }
}
