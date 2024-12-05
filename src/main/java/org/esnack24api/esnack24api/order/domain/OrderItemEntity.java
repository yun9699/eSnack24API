package org.esnack24api.esnack24api.order.domain;

import jakarta.persistence.*;
import lombok.*;
import org.esnack24api.esnack24api.product.domain.ProductEntity;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"order", "product"})
@Table(name = "tbl_order_item")
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oino;

    @ManyToOne
    @JoinColumn(name = "pno")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "ono")
    private OrderEntity order;

    private int oiqty;
}
