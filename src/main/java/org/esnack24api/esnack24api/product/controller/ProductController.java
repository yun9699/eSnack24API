package org.esnack24api.esnack24api.product.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.common.page.PageResponse;
import org.esnack24api.esnack24api.product.dto.ProductMainDTO;
import org.esnack24api.esnack24api.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
@Log4j2
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("list")
    public ResponseEntity<PageResponse<ProductMainDTO>> getMainList(PageRequest pageRequest) {
        log.info("Get Controller");

        return ResponseEntity.ok(productService.getProductMainList(pageRequest));
    }
}
