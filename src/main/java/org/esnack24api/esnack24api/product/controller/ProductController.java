package org.esnack24api.esnack24api.product.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.common.page.PageResponse;
import org.esnack24api.esnack24api.product.dto.ProductDetailDTO;
import org.esnack24api.esnack24api.product.dto.ProductListDTO;
import org.esnack24api.esnack24api.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
@Log4j2
@RequiredArgsConstructor
@PreAuthorize("permitAll()")
public class ProductController {

    private final ProductService productService;

    @GetMapping("list")
    public ResponseEntity<PageResponse<ProductListDTO>> getMainList(PageRequest pageRequest) {
        log.info("Get Controller");

        return ResponseEntity.ok(productService.getProductMainList(pageRequest));
    }

    @GetMapping("detail/{pno}")
    public ResponseEntity<ProductDetailDTO> getDetail(@PathVariable Long pno){
        log.info("Get Controller");

        return ResponseEntity.ok(productService.getProductDetail(pno));
    }
}
