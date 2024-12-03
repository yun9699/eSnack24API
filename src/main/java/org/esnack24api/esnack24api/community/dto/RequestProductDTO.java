package org.esnack24api.esnack24api.community.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class RequestProductDTO {

    private Long cpno;

    private String cpproduct;

    private String cptitle;

    private String cpanswer;

    private boolean cpdelete;

    private Timestamp cpregdate;

    private Timestamp cpmoddate;

}
