package org.esnack24api.esnack24api.community.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Setter
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_community_allergy")
public class RequestAllergyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cano;

    private String caallergy;

    private String catitle;

    private String caanswer;

    private boolean cadelete;

    private Timestamp caregdate;

    private Timestamp camoddate;

    public void updateRequestAllergy(String catitle, String caallergy){
        this.catitle = catitle;
        this.caallergy = caallergy;
    }

    public void deleteRequestAllergy(){ this.cadelete = true; }
}
