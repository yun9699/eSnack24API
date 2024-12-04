package org.esnack24api.esnack24api.order.toss.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.order.domain.OrderEntity;
import org.esnack24api.esnack24api.order.repository.OrderRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Base64;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class TossService {

    private final OrderRepository orderRepository;

    /**
     * 결제 승인 처리
     */
    public ResponseEntity<JSONObject> confirmPayment(String jsonBody) throws Exception {
        JSONParser parser = new JSONParser();
        String orderId;
        String amount;
        String paymentKey;

        try {
            // 클라이언트에서 받은 JSON 요청 바디입니다.
            JSONObject requestData = (JSONObject) parser.parse(jsonBody);
            paymentKey = (String) requestData.get("paymentKey");
            orderId = (String) requestData.get("orderId");
            amount = (String) requestData.get("amount");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        JSONObject obj = new JSONObject();
        obj.put("orderId", orderId);
        obj.put("amount", amount);
        obj.put("paymentKey", paymentKey);

        // 시크릿 키 설정
        String widgetSecretKey = "test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6";

        // 인증 정보 설정
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode((widgetSecretKey + ":").getBytes(StandardCharsets.UTF_8));
        String authorizations = "Basic " + new String(encodedBytes);

        // 결제 승인 API 호출
        URL url = new URL("https://api.tosspayments.com/v1/payments/confirm");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", authorizations);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(obj.toString().getBytes("UTF-8"));

        int code = connection.getResponseCode();
        boolean isSuccess = code == 200;

        InputStream responseStream = isSuccess ? connection.getInputStream() : connection.getErrorStream();

        // 응답 처리
        Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        responseStream.close();


        int underscoreIndex = orderId.indexOf("_");
        Long ono = Long.parseLong(orderId.substring(underscoreIndex + 1));

        OrderEntity order = orderRepository.findById(ono).orElseThrow();

        order.setMethod("Toss");
        order.setStatus("Complete");
        order.setTransactionId(orderId);
        order.setCurrency("KRW");
        order.setTossPaymentKey(paymentKey);
        order.setOcompletedate(Timestamp.from(Instant.now()));
        order.setTotal_amount(new BigDecimal(amount));

        log.info("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB___+_+_+_++___+_+");
        log.info(order);

        orderRepository.save(order);

        return ResponseEntity.status(code).body(jsonObject);
    }
}
