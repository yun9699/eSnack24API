package org.esnack24api.esnack24api.cart.repository;

import org.esnack24api.esnack24api.cart.domain.Cart;
import org.esnack24api.esnack24api.cart.repository.search.CartSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository  extends JpaRepository<Cart, Long>, CartSearch {


}
