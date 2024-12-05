package org.esnack24api.esnack24api.order.mapper;

import org.esnack24api.esnack24api.order.dto.OrderDetailDTO;
import org.esnack24api.esnack24api.order.dto.OrderUserDTO;
import org.esnack24api.esnack24api.order.dto.OrderViewDTO;

import java.util.List;

public interface OrderMapper{

    OrderViewDTO getOrderView(Long ono);

    List<OrderDetailDTO> getOrderDetail(Long ono);

    List<OrderUserDTO> getOrderUser(Long uno);
}
