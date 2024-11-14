package org.esnack24api.esnack24api.review.domain;

import jakarta.persistence.*;
import lombok.*;
import org.esnack24api.esnack24api.product.domain.ProductEntity;
import org.esnack24api.esnack24api.user.domain.UserEntity;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_review")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long rno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uno")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pno")
    private ProductEntity product;

    private String rcontent;
    private boolean rdelete;
    private int rstar;
    private String rimage;

    @Column(name = "rmoddate")
    private Timestamp rmodDate;

    @Column(name = "rregdate")
    private Timestamp rregDate;

    public void updateReview(String rcontent, int rstar, String rimage) {

        this.rcontent = rcontent;
        this.rstar = rstar;
        this.rimage = rimage;

    }

    public void deleteReview() {
        this.rdelete = true;
    }



}
