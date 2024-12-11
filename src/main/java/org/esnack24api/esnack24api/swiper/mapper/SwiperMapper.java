package org.esnack24api.esnack24api.swiper.mapper;

import org.esnack24api.esnack24api.swiper.dto.SwiperDTO;

import java.util.List;

public interface SwiperMapper {

    List<SwiperDTO> getMainSwipers();

    List<SwiperDTO> getListSwipers();
}
