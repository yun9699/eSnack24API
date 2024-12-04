package org.esnack24api.esnack24api.community.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.common.page.PageResponse;
import org.esnack24api.esnack24api.community.dto.RequestProductDTO;
import org.esnack24api.esnack24api.community.service.RequestProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/request/product")
@RequiredArgsConstructor
@Log4j2
public class RequestProductController {
    
    private final RequestProductService requestProductService;

    @GetMapping("list")
    public ResponseEntity<PageResponse<RequestProductDTO>> getRequestProductList(Long cpno, PageRequest pageRequest) {

        return ResponseEntity.ok(requestProductService.getRequestProductLists(cpno, pageRequest));

    }

    @GetMapping("detail/{cpno}")
    public ResponseEntity<RequestProductDTO> getRequestProductDetail(@PathVariable("cpno") Long cpno) {

        return ResponseEntity.ok(requestProductService.getRequestProductDetail(cpno));
    }

    @PostMapping("add")
    public ResponseEntity<RequestProductDTO> registerRequestProduct(@RequestBody RequestProductDTO RequestProductDTO) {

        return ResponseEntity.ok(requestProductService.registerRequestProduct(RequestProductDTO));
    }

    @PutMapping("edit/{cpno}")
    public ResponseEntity<RequestProductDTO> editRequestProduct(
            @PathVariable Long cpno,
            @RequestBody RequestProductDTO RequestProductDTO) {
        RequestProductDTO.setCpno(cpno);

        return ResponseEntity.ok(requestProductService.updateRequestProduct(RequestProductDTO));
    }

    @DeleteMapping("delete/{cpno}")
    public ResponseEntity<RequestProductDTO> deleteRequestProduct(@PathVariable Long cpno) {
        requestProductService.deleteRequestProduct(cpno);

        return ResponseEntity.noContent().build();
    }
    
}
