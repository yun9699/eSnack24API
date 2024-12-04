package org.esnack24api.esnack24api.order.toss.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.order.toss.service.TossService;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/toss")
@Log4j2
@RequiredArgsConstructor
@PreAuthorize("permitAll()")
public class TossController {

    private final TossService tossService;

    /**
     * 결제 승인 요청
     */
    @RequestMapping(value = "/confirm")
    public ResponseEntity<JSONObject> confirmPayment(@RequestBody String jsonBody) throws Exception {

        return tossService.confirmPayment(jsonBody);
    }
}
