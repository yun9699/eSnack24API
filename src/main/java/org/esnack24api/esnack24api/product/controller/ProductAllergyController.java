package org.esnack24api.esnack24api.product.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.product.dto.ProductAllergyDetailDTO;
import org.esnack24api.esnack24api.product.service.ProductAllergyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/allergy")
@Log4j2
@RequiredArgsConstructor
public class ProductAllergyController {

    private final ProductAllergyService productAllergyService;


    @GetMapping("detail")
    public ResponseEntity<ProductAllergyDetailDTO> getAllergyByFilename(@RequestParam String pfilename) {
        ProductAllergyDetailDTO allergyDetail = productAllergyService.productAllergyDetailByFilename(pfilename);
        return ResponseEntity.ok(allergyDetail);
    }
}
