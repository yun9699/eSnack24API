package org.esnack24api.esnack24api.product.mapper;

import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.product.dto.ProductMainDTO;

import java.util.List;

public interface ProductMapper {

    List<ProductMainDTO> getList(PageRequest pageRequest);

    int count(PageRequest pageRequest);

}
