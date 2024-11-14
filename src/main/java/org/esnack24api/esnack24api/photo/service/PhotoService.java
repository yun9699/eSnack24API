package org.esnack24api.esnack24api.photo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.photo.domain.PhotoEntity;
import org.esnack24api.esnack24api.photo.repository.PhotoRepository;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository; // FileRepository 주입

    // base64 인코딩된 문자열을 디코딩하여 그 결과만 로그로 출력
    public String[] decoding(String encoding)  {
        log.info("디코딩전-------------------------------------------------------------");
        log.info(encoding);
        if (encoding == null || encoding.isEmpty()) {
            throw new IllegalArgumentException("Encoding cannot be null or empty");
        }


        // 공백 제거 및 유효하지 않은 문자 제거
        String resultEncoding = encoding.replaceAll("\\s+", "");  // 공백 제거
        resultEncoding = resultEncoding.replaceAll("[^A-Za-z0-9+/=]", "");  // 유효하지 않은 문자 제거

        // Base64 문자열의 길이가 4의 배수가 되지 않으면 패딩 추가
        int paddingLength = resultEncoding.length() % 4;
        if (paddingLength != 0) {
            resultEncoding += "=".repeat(4 - paddingLength);  // 패딩 추가
        }

        log.info("디코딩후-------------------------------------------------------------");
        log.info(resultEncoding);
        log.info(resultEncoding.length());

        UUID uuid = UUID.randomUUID();
        String[] filename = new String[1];
        filename[0] = uuid + ".png";

        try {

            byte[] decodedBytes = Base64.getDecoder().decode(resultEncoding);
            log.info("디코딩시도했다.");

            // 디코딩한 파일 폴더에 저장
            String filePath = "C:\\decoding\\" + filename[0];
            try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {

                fileOutputStream.write(decodedBytes);
                log.info(filename);
                log.info("파일저장완료----------------------");


                saveFilenameToDb(filename[0]);

            } catch (IOException e) {
                log.info("파일저장실패----------------------");
                e.printStackTrace();
            }

        } catch (IllegalArgumentException e) {
            log.error("잘못된 Base64 인코딩: {}", encoding, e);
        } catch (Exception e) {
            log.error("디코딩 중 예상치 못한 오류 발생", e);
        }

        return filename;
    }


    public void saveFilenameToDb(String filename) {
        PhotoEntity photoEntity = PhotoEntity.builder()
                .photoFilename(filename)
                .build();

        photoRepository.save(photoEntity);
        log.info("파일명 '{}' 데이터베이스에 저장 완료", filename);
    }
}
