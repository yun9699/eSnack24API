package org.esnack24api.esnack24api.search.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.search.dto.ProductSearchDTO;
import org.esnack24api.esnack24api.search.service.ProductSearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
@Log4j2
@RequiredArgsConstructor
public class ProductSearchController {

    private final ProductSearchService productSearchService;

    @GetMapping("product")
    public ResponseEntity<List<ProductSearchDTO>> searchProducts(@RequestParam String param) {

        log.info("Searching products Controller");

        return ResponseEntity.ok(productSearchService.getSearchList(param));

    }

}
