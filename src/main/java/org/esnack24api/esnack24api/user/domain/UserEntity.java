package org.esnack24api.esnack24api.user.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_user")  // 테이블 이름을 명시적으로 지정
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uno;

    private String uemail;

    private String upw;

    private String username;

    private String ugender;

    private String ucallnumber;

    private String uaddress;

    private java.sql.Date ubirth;

    private boolean udelete = false;

}
