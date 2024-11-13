package org.esnack24api.esnack24api.cart.repository.search;

import org.esnack24api.esnack24api.product.domain.Product;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class CartSearchImpl extends QuerydslRepositorySupport implements CartSearch {

    public CartSearchImpl() {
        super(Product.class);
    }
}
