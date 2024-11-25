package org.esnack24api.esnack24api.allergy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.allergy.dto.AllergyListDTO;
import org.esnack24api.esnack24api.allergy.service.AllergyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/allergy")
@RequiredArgsConstructor
@Log4j2
public class AllergyController {

    private final AllergyService allergyService;

    @GetMapping("list")
    public ResponseEntity<List<AllergyListDTO>> getAllergyList() {

        return ResponseEntity.ok(allergyService.getAllergyList());
    }
}
