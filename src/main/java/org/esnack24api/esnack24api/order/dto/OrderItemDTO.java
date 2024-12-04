package org.esnack24api.esnack24api.order.dto;

import lombok.Data;

@Data
public class OrderItemDTO {

    private String ptitleKo; // 상품 제목

    private int price; // 상품 가격

    private String pfilename; // 상품 이미지 파일명

    private int oiqty; // 상품 수량
}