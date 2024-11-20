package org.esnack24api.esnack24api.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.user.dto.AddressRegisterDTO;
import org.esnack24api.esnack24api.user.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
@Log4j2
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping("reg/{uno}")
    public ResponseEntity<String> registerAddress(
            @PathVariable Long uno,
            @RequestBody AddressRegisterDTO dto) {

        dto.setUno(uno);

        return ResponseEntity.ok(addressService.registerAddress(dto));
    }
}
