package org.esnack24api.esnack24api.cart.domain;

import jakarta.persistence.*;
import lombok.*;
import org.esnack24api.esnack24api.product.domain.ProductEntity;

import java.sql.Timestamp;

@Setter
@Entity
@Table(name = "tbl_cartitem")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"cart", "product"})
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cino;

    private Timestamp ciregdate;

    private int ciqty;

    @ManyToOne
    @JoinColumn(name = "cno")
    private CartEntity cart;

    @OneToOne
    @JoinColumn(name = "pno")
    private ProductEntity product;
}
