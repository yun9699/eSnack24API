package org.esnack24api.esnack24api.cart.domain;

import jakarta.persistence.*;
import lombok.*;
import org.esnack24api.esnack24api.product.domain.ProductEntity;
import org.esnack24api.esnack24api.user.domain.UserEntity;

import java.sql.Timestamp;

@Setter
@Entity
@Table(name = "tbl_cart")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user"})
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoIncrement로 자동 생성되는 pk
    private Long cno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="uno")
    private UserEntity user;


}
