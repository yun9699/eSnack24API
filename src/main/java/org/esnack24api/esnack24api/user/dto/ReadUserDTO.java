package org.esnack24api.esnack24api.user.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReadUserDTO {

    private String username;

    private String ugender;

    private String ucallnumber;

    private Timestamp ubirth;
}
