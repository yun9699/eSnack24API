package org.esnack24api.esnack24api.customersupport.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.esnack24api.esnack24api.customersupport.dto.QNADetailDTO;
import org.esnack24api.esnack24api.customersupport.dto.QNAListDTO;
import org.esnack24api.esnack24api.common.page.PageRequest;

import java.util.List;

@Mapper
public interface QNAMapper {
    // 내가 작성한 QNA 목록 조회
    List<QNAListDTO> getList(Long uno, PageRequest pageRequest);

    // QNA 상세 내용 조회
    QNADetailDTO getOne(Long qno);

    // 페이징 처리
    int count(PageRequest pageRequest);
}