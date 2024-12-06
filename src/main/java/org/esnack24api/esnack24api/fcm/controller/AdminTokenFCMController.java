package org.esnack24api.esnack24api.fcm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.fcm.dto.AdminTokenFCMDTO;
import org.esnack24api.esnack24api.fcm.service.AdminTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/api/v1/fcm")
@Log4j2
@RequiredArgsConstructor
@PreAuthorize("permitAll()")
public class AdminTokenFCMController {

    private final AdminTokenService adminTokenService;

    @GetMapping("/gettoken")
    public ResponseEntity<List<String>> getAdminTokens() {

        List<String> adminToken = adminTokenService.getFCMToken();

        return ResponseEntity.ok(adminToken);
    }
}
