package org.esnack24api.esnack24api.order.mapper;

import org.esnack24api.esnack24api.order.dto.OrderViewDTO;

public interface OrderMapper{

    OrderViewDTO getOrderView(Long ono);
}
