package org.esnack24api.esnack24api.user.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserDTO {

    private Long uno;

    private String email;

    private String pw;

    private String username;

    private String gender;

    private String callNumber;

    private String address;

    private Timestamp birth;

    private boolean isNew;  //새로운 사용자 Flag
}
