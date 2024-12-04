package org.esnack24api.esnack24api.review.controller;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.upload.service.ImageUploadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
@Log4j2
public class ImageUploadController {

    private final ImageUploadService imageUploadService;

    @PostMapping("/upload-image")
    public ResponseEntity<?> uploadBase64Image(@RequestBody String base64File) {
        log.info("수신된 Base64 데이터: {}", base64File.substring(0, Math.min(100, base64File.length())) + "...");

        // 불필요한 쌍따옴표 제거
        if (base64File.startsWith("\"") && base64File.endsWith("\"")) {
            base64File = base64File.substring(1, base64File.length() - 1);
        }

        if (!base64File.startsWith("data:image/")) {
            log.error("잘못된 Base64 데이터 형식: {}", base64File.substring(0, 50));
            return ResponseEntity.badRequest().body(Map.of("error", "잘못된 Base64 데이터 형식입니다."));
        }

        try {
            String imageUrl = imageUploadService.uploadBase64File(base64File);
            log.info("이미지 업로드 성공: {}", imageUrl);
            return ResponseEntity.ok(Map.of("url", imageUrl));
        } catch (Exception e) {
            log.error("업로드 실패: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
        }
    }





}
