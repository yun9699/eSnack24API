package org.esnack24api.esnack24api.photo.domain;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_photo_files")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pfno;

    private String photoFilename;

}
