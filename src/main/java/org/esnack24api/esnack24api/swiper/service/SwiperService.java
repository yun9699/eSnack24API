package org.esnack24api.esnack24api.swiper.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.swiper.dto.SwiperDTO;
import org.esnack24api.esnack24api.swiper.mapper.SwiperMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class SwiperService {

    private final SwiperMapper swiperMapper;

    public List<SwiperDTO> getMainSwipers() {

       return swiperMapper.getMainSwipers();
    }

    public List<SwiperDTO> getListSwipers() {

        return swiperMapper.getListSwipers();
    }
}
