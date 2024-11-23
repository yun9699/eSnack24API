package org.esnack24api.esnack24api.cart.dto;

import lombok.Data;

@Data
public class AddCartItemDTO {

    private Long cno;

    private Long pno;

    private int ciqty;
}
