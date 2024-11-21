package org.esnack24api.esnack24api.cart.mapper;

import org.apache.ibatis.annotations.Param;
import org.esnack24api.esnack24api.cart.dto.CartListDTO;
import org.esnack24api.esnack24api.common.page.PageRequest;

import java.util.List;

public interface CartMapper {

    List<CartListDTO> getCartList(@Param("uno") Long uno, @Param("pageRequest") PageRequest pageRequest);

    int count(@Param("uno") Long uno, @Param("pageRequest") PageRequest pageRequest);
}
