package org.esnack24api.esnack24api.photo.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.photo.service.PhotoService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Log4j2
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper;

    // 이미지 상태 저장 및 유사 이미지 검색
    @PostMapping("saveState")
    public ResponseEntity<Map<String, Object>> saveState(@RequestBody String result) throws JsonProcessingException {
        // 요청에서 base64로 인코딩된 이미지 데이터 추출
        Map<String, String> resultMap = objectMapper.readValue(result, Map.class);
        String encodingImage = resultMap.get("image");
        log.info("Received encoded image data: {}", encodingImage);

        // 디코딩된 파일명을 배열로 받아옴
        String[] filenames = photoService.decoding(encodingImage);
        log.info("Decoded filenames: {}", Arrays.toString(filenames));

        String fastApiUrl = "http://127.0.0.1:9000/upload";  // FastAPI 파일 업로드 URL
        String searchUrl = "http://127.0.0.1:9000/search";  // FastAPI 유사 이미지 검색 URL

        Map<String, Object> allResults = new HashMap<>(); // 전체 결과를 담을 Map

        for (String filename : filenames) {
            try {
                // 디코딩한 파일이 실제로 존재하는지 확인
                File file = new File("C:\\decoding\\" + filename);
                if (!file.exists()) {
                    log.warn("File does not exist: {}", filename);
                    continue;
                }

                // FastAPI로 파일 전송 헤더 및 본문 설정
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.MULTIPART_FORM_DATA);

                MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
                body.add("file", new FileSystemResource(file));

                HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

                // 파일 업로드 요청
                ResponseEntity<String> uploadResponse = restTemplate.exchange(fastApiUrl, HttpMethod.POST, requestEntity, String.class);
                log.info("File '{}' sent to FastAPI, response: {}", filename, uploadResponse.getBody());

                // 유사 이미지 검색 요청
                HttpEntity<MultiValueMap<String, Object>> searchRequestEntity = new HttpEntity<>(body, headers);
                ResponseEntity<Map> searchResponse = restTemplate.exchange(searchUrl, HttpMethod.POST, searchRequestEntity, Map.class);

                if (searchResponse.getStatusCode().is2xxSuccessful()) {
                    Map<String, Object> responseMap = searchResponse.getBody();
                    allResults.put(filename, responseMap.get("similar_images"));
                } else {
                    log.error("Failed to fetch similar images for file '{}'", filename);
                }

                // 디코딩된 파일명을 데이터베이스에 저장
                photoService.saveFilenameToDb(filename);

            } catch (Exception e) {
                log.error("Error processing file: {}", filename, e);
                return ResponseEntity.status(500).body(Map.of("error", "Failed to process file: " + filename));
            }
        }

        return ResponseEntity.ok(Map.of("similarImages", allResults));
    }
}
