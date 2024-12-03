package org.esnack24api.esnack24api.community.mapper;

import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.community.dto.RequestProductDTO;

import java.util.List;

public interface RequestProductMapper {

    List<RequestProductDTO> getRequestProductList(Long cpno, PageRequest pageRequest);

    int count(Long cpno);

    RequestProductDTO getRequestProductDetail(Long cpno);

}
