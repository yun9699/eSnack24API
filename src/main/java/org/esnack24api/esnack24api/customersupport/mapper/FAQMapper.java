package org.esnack24api.esnack24api.customersupport.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.esnack24api.esnack24api.customersupport.dto.FAQDetailDTO;
import org.esnack24api.esnack24api.customersupport.dto.FAQListDTO;
import org.esnack24api.esnack24api.common.page.PageRequest;

import java.util.List;

@Mapper
public interface FAQMapper {

    // FAQ 목록 조회 (카테고리별)
    List<FAQListDTO> getList(@Param("pageRequest") PageRequest pageRequest,
                             @Param("fcategory") String fcategory);

    // FAQ 상세 조회
    FAQDetailDTO getOne(Long fno);

    // 카테고리별 총 개수
    int count(@Param("fcategory") String fcategory);
}