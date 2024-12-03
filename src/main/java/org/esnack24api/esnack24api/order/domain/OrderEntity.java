package org.esnack24api.esnack24api.order.domain;

import jakarta.persistence.*;
import lombok.*;
import org.esnack24api.esnack24api.user.domain.UserEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"user"})
@Table(name = "tbl_order")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ono;   //primary key

    @Column(name = "paypal_order_id")
    private String paypalOrderId;   //paypal order id

    private BigDecimal total_amount;    //총 금액

    private String currency;    //결제 통화

    private String status;  //주문 상태(결제 완료, 대기 등)

    private Timestamp oregdate; //주문 생성 시간

    @ManyToOne
    @JoinColumn(name = "uno")
    private UserEntity user;    //user primary key
}
