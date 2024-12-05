package org.esnack24api.esnack24api.order.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class OrderUserDTO {

    private Long ono; // 주문 번호

    private Timestamp oregdate; // 주문 날짜

    private List<OrderItemDTO> orderItems; // 주문 상품 리스트
}
