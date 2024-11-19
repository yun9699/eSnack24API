package org.esnack24api.esnack24api.order.paypal.domain;

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
    private Long ocno;

    private BigDecimal capture_amount;

    private String capture_status;

    private String transaction_id;

    private Timestamp capDate;

    @ManyToOne
    @JoinColumn(name = "ono")
    private OrderEntity order;
}
