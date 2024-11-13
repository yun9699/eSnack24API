package org.esnack24api.esnack24api.product.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.esnack24api.esnack24api.allergy.domain.AllergyEntity;

@Entity
@ToString(exclude = {"product", "allergy"})
@Table(name = "tbl_review")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductAllergyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoIncrement로 자동 생성되는 pk
    private Long pano;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="ano")
    private AllergyEntity allergy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="pno")
    private ProductEntity product;
}
