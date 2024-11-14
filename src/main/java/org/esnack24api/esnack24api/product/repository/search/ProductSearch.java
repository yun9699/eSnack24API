package org.esnack24api.esnack24api.product.repository.search;

import org.esnack24api.esnack24api.common.dto.PageRequestDTO;
import org.esnack24api.esnack24api.common.dto.PageResponseDTO;
import org.esnack24api.esnack24api.product.domain.ProductEntity;
import org.esnack24api.esnack24api.product.dto.ProductListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductSearch {


    Page<ProductEntity> listProducts(Pageable pageable);

    PageResponseDTO<ProductListDTO> listProductAll(PageRequestDTO pageRequestDTO);



}
