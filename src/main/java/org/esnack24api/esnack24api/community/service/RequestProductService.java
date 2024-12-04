package org.esnack24api.esnack24api.community.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.common.page.PageResponse;
import org.esnack24api.esnack24api.community.domain.RequestProductEntity;
import org.esnack24api.esnack24api.community.dto.RequestProductDTO;
import org.esnack24api.esnack24api.community.mapper.RequestProductMapper;
import org.esnack24api.esnack24api.community.repository.RequestProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class RequestProductService {
    
    private final RequestProductRepository requestProductRepository;
    
    private final RequestProductMapper requestProductMapper;

    public PageResponse<RequestProductDTO> getRequestProductLists(Long cpno, PageRequest pageRequest) {

        return PageResponse.<RequestProductDTO>with()
                .list(requestProductMapper.getRequestProductList(cpno, pageRequest))
                .total(requestProductMapper.count(cpno))
                .pageRequest(pageRequest)
                .build();
    }

    public RequestProductDTO getRequestProductDetail(Long cpno) {

        return requestProductMapper.getRequestProductDetail(cpno);
    }

    public RequestProductDTO registerRequestProduct(RequestProductDTO RequestProductDTO) {

        RequestProductEntity requestProduct = RequestProductEntity.builder()
                .cptitle(RequestProductDTO.getCptitle())
                .cpproduct(RequestProductDTO.getCpproduct())
                .cpdelete(false)
                .build();

        RequestProductEntity savedRequestProduct = requestProductRepository.save(requestProduct);
        return requestProductMapper.getRequestProductDetail(savedRequestProduct.getCpno());
    }

    public RequestProductDTO updateRequestProduct(RequestProductDTO requestProductDTO) {

        RequestProductEntity requestProduct = requestProductRepository.findById(requestProductDTO.getCpno())
                .orElseThrow(() -> new IllegalArgumentException("알러지 요청을 찾을 수 없습니다." + requestProductDTO.getCpno()));

        requestProduct.updateRequestProduct(
                requestProductDTO.getCptitle(),
                requestProductDTO.getCpproduct()
        );

        requestProductRepository.save(requestProduct);

        return requestProductMapper.getRequestProductDetail(requestProductDTO.getCpno());
    }

    public void deleteRequestProduct(Long cpno) {

        RequestProductEntity requestProduct = requestProductRepository.findById(cpno)
                .orElseThrow(() -> new IllegalArgumentException("not found" + cpno));

        requestProduct.deleteRequestProduct();
        requestProductRepository.save(requestProduct);
    }


}
