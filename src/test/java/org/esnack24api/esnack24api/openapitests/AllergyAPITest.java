package org.esnack24api.esnack24api.openapitests;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Log4j2
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // db로 테스트?
public class AllergyAPITest {

    @Test
    public void AllergyAPILogTest() {
        String serviceKey = "Dxhv/FADXXMPmKxLHMxOkoyMrWL45dwTybbI8frUxCT1eyJKz0WstFSGR5f0XppdMp51F/kluvX3+m4oTgJHJQ==";
        String baseUrl = "http://apis.data.go.kr/B553748/CertImgListServiceV3/getCertImgListServiceV3";

        // 파라미터 설정
        Map<String, String> params = new HashMap<>();
        params.put("serviceKey", serviceKey);
        params.put("page", "1");
        params.put("perPage", "10");
        params.put("prdlstNm", "포카칩");
        params.put("returnType", "json");




    }

}

