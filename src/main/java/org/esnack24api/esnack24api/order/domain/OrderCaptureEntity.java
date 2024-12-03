package org.esnack24api.esnack24api.order.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"order"})
@Table(name = "tbl_order_capture")
public class OrderCaptureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ocno;  //primary key

    private BigDecimal capture_amount;  //캡쳐된 금액

    private String capture_status;  //결제 캡쳐 상태(성공, 실패)

    private String transaction_id;  //paypal 에서 생성한 transaction id

    private Timestamp capdate;  //capture 시점

    @ManyToOne
    @JoinColumn(name = "ono")
    private OrderEntity order;  //order primary key
}
