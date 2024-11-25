package org.esnack24api.esnack24api.search.mapper;

import org.esnack24api.esnack24api.search.dto.ProductSearchDTO;

import java.util.List;

public interface ProductSearchMapper {

    List<ProductSearchDTO> getSearchList(String keyword);


}
