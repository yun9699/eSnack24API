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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int qno;

    private int uno;

    private int pno;

    @Column(nullable = false, length = 100)
    private String qtitle;

    @Column(nullable = false, length = 500)
    private String qcontent;

    @Column(length = 500)
    private String qanswer;

    @Builder.Default
    private boolean qdelete = false;

    @Column(nullable = false, length = 300)
    private String qfilename;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime qregdate;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime qmoddate;
}
