package org.esnack24api.esnack24api.product.repository.search;


import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.product.domain.Product;
import org.esnack24api.esnack24api.product.domain.QProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;


@Log4j2
public class ProductSearchImpl extends QuerydslRepositorySupport implements ProductSearch {

    public ProductSearchImpl() {
        super(Product.class);
    }

    @Override
    public Page<Product> listProducts(Pageable pageable) {

        QProduct product = QProduct.product;

        JPQLQuery<Product> query = from(product);
        query.where(product.pno.gt(0));

        this.getQuerydsl().applyPagination(pageable, query);

        List<Product> productList = query.fetch();

        long total = query.fetchCount();

        return new PageImpl<>(productList, pageable, total);
    }
}
