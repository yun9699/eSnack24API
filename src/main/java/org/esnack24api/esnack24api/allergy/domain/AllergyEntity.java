package org.esnack24api.esnack24api.allergy.domain;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Entity
@Table(name = "tbl_allergy")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllergyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoIncrement로 자동 생성되는 pk
    private Long ano;

    private String atitle_ko;

    private String atitle_en;

    private String atitle_ja;

    private String atitle_zh;
}
