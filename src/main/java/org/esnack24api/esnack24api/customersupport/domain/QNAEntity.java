package org.esnack24api.esnack24api.customersupport.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tbl_qna")
public class QNAEntity {

    @Id
    private Long qno;

    private Long uno;

    private Long pno;

    private String qtitle;

    private String qcontent;

    private String qanswer;

    @Builder.Default
    private boolean qdelete = false;

    private String qfilename;

    @CreatedDate
    private LocalDateTime qregdate;

    @LastModifiedDate
    private LocalDateTime qmoddate;
}