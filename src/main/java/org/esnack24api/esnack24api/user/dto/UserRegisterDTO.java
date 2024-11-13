package org.esnack24api.esnack24api.user.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserRegisterDTO {

    private String username;

    private String gender;

    private String callNumber;

    private String address;

    private Timestamp birth;
}
