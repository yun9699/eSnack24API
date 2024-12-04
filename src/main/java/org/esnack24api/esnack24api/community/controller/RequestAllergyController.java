package org.esnack24api.esnack24api.community.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.common.page.PageResponse;
import org.esnack24api.esnack24api.community.dto.RequestAllergyDTO;
import org.esnack24api.esnack24api.community.service.RequestAllergyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/request/allergy")
@RequiredArgsConstructor
@Log4j2
public class RequestAllergyController {

    private final RequestAllergyService requestAllergyService;

    @GetMapping("list")
    public ResponseEntity<PageResponse<RequestAllergyDTO>> getRequestAllergyList(Long cano, PageRequest pageRequest) {

        return ResponseEntity.ok(requestAllergyService.getRequestAllergyLists(cano, pageRequest));

    }

    @GetMapping("detail/{cano}")
    public ResponseEntity<RequestAllergyDTO> getRequestAllergyDetail(@PathVariable("cano") Long cano) {

        return ResponseEntity.ok(requestAllergyService.getRequestAllergyDetail(cano));
    }

    @PostMapping("add")
    public ResponseEntity<RequestAllergyDTO> registerRequestAllergy(@RequestBody RequestAllergyDTO requestAllergyDTO) {

        return ResponseEntity.ok(requestAllergyService.registerRequestAllergy(requestAllergyDTO));
    }

    @PutMapping("edit/{cano}")
    public ResponseEntity<RequestAllergyDTO> editRequestAllergy(
            @PathVariable Long cano,
            @RequestBody RequestAllergyDTO requestAllergyDTO) {
        requestAllergyDTO.setCano(cano);

        return ResponseEntity.ok(requestAllergyService.updateRequestAllergy(requestAllergyDTO));
    }

    @DeleteMapping("delete/{cano}")
    public ResponseEntity<RequestAllergyDTO> deleteRequestAllergy(@PathVariable Long cano) {
        requestAllergyService.deleteRequestAllergy(cano);

        return ResponseEntity.noContent().build();
    }

}
