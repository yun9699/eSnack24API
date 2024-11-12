package org.esnack24api.esnack24api.product.repository;

import org.esnack24api.esnack24api.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
