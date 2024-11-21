package org.esnack24api.esnack24api.user.domain;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adno;

    private String address_line1;

    private String address_line2;

    private int zipcode;

    private boolean is_primary;

    private String country;

    private Timestamp adregdate;

    private Timestamp admoddate;

    private String state;

    private String city;

    private String phonenumber;

    @ManyToOne
    @JoinColumn(name = "uno")
    private UserEntity user;
}
