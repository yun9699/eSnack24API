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
@Table(name = "tbl_community_product")
public class RequestProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cpno;

    private String cpproduct;

    private String cptitle;

    private String cpanswer;

    private boolean cpdelete;

    private Timestamp cpregdate;

    private Timestamp cpmoddate;

    public void updateRequestProduct(String cptitle, String cpproduct){
        this.cptitle = cptitle;
        this.cpproduct = cpproduct;
    }

    public void deleteRequestProduct(){ this.cpdelete = true; }

}
