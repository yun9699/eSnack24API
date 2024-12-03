package org.esnack24api.esnack24api.community.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class RequestAllergyDTO {

    private Long cano;

    private String caallergy;

    private String catitle;

    private String caanswer;

    private boolean cadelete;

    private Timestamp caregdate;

    private Timestamp camoddate;

}
