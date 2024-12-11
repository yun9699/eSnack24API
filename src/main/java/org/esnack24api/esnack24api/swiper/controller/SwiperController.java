package org.esnack24api.esnack24api.swiper.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.common.page.PageResponse;
import org.esnack24api.esnack24api.product.dto.ProductListDTO;
import org.esnack24api.esnack24api.swiper.dto.SwiperDTO;
import org.esnack24api.esnack24api.swiper.service.SwiperService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/swiper")
@Log4j2
@RequiredArgsConstructor
@PreAuthorize("permitAll()")
public class SwiperController {

    private final SwiperService swiperService;

    @GetMapping("main")
    public ResponseEntity<List<SwiperDTO>> getMainSwiper( ) {
        log.info("Get main");

        return ResponseEntity.ok(swiperService.getMainSwipers());
    }

    @GetMapping("list")
    public ResponseEntity<List<SwiperDTO>> getListSwiper( ) {
        log.info("Get list");

        return ResponseEntity.ok(swiperService.getListSwipers());
    }
}
