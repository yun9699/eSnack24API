package org.esnack24api.esnack24api.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.user.service.UserAllergyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@Log4j2
@RequiredArgsConstructor
public class UserAllergyController {

    private final UserAllergyService userAllergyService;

    @PostMapping("setallergies/{uno}")
    public ResponseEntity<String> registerPersonalAllergies(
            @PathVariable Long uno,
            @RequestBody List<Long> allergies) {

        return ResponseEntity.ok(userAllergyService.registerPersonalAllergy(allergies, uno));

    }
}
