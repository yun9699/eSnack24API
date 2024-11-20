package org.esnack24api.esnack24api.user.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class AddressRegisterDTO {

    private Long uno;

    private String address_line1;

    private String address_line2;

    private int zipcode;

    private String country;

    private boolean is_primary;

    private String state;

    private String city;

    private String phonenumber;
}
