package org.esnack24api.esnack24api.cart.repository;

import org.esnack24api.esnack24api.cart.domain.CartEntity;
import org.esnack24api.esnack24api.cart.repository.search.CartSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository  extends JpaRepository<CartEntity, Long>, CartSearch {


}
