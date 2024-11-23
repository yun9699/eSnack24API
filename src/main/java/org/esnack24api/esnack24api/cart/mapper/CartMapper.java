package org.esnack24api.esnack24api.cart.mapper;


import org.apache.ibatis.annotations.Param;
import org.esnack24api.esnack24api.cart.dto.ListCartDTO;
import org.esnack24api.esnack24api.common.page.PageRequest;

import java.util.List;

public interface CartMapper {

    List<ListCartDTO> getCartList(
            @Param("cno") Long cno, @Param("pageRequest") PageRequest pageRequest);

    int count(Long cno);
}
