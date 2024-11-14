package org.esnack24api.esnack24api.product.mapper;

import org.esnack24api.esnack24api.product.dto.ProductAllergyDetailDTO;

public interface ProductAllergyMapper {

    ProductAllergyDetailDTO getOne(Long pno);

}
