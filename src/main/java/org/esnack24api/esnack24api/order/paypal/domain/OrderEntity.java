package org.esnack24api.esnack24api.order.paypal.domain;

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
    private Long ono;

    private String paypal_order_id;

    private BigDecimal total_amount;

    private String currency;

    private String status;

    private Timestamp regDate;

    private Timestamp modDate;

    @ManyToOne
    @JoinColumn(name = "uno")
    private UserEntity user;
}
