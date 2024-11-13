package org.esnack24api.esnack24api.product.mapper;

import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.product.dto.ProductDetailDTO;
import org.esnack24api.esnack24api.product.dto.ProductListDTO;

import java.util.List;

public interface ProductMapper {

    List<ProductListDTO> getList(PageRequest pageRequest);

    int count(PageRequest pageRequest);

    ProductDetailDTO getOne(Long pno);
}
