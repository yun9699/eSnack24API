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
@Table(name = "tbl_user")  // 테이블 이름을 명시적으로 지정
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uno;

    private String uemail;

    private String upw;

    private String username;

    private String ugender;

    private String ucallnumber;

    private String uaddress;

    private Timestamp ubirth;

    private boolean udelete;

}
