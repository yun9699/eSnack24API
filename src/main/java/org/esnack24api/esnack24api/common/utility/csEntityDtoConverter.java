package org.esnack24api.esnack24api.common.utility;

import org.esnack24api.esnack24api.customersupport.domain.QNAEntity;
import org.esnack24api.esnack24api.customersupport.dto.QNADetailDTO;
import org.esnack24api.esnack24api.customersupport.dto.QNAListDTO;

// MyBatis와 JPA의 혼용 환경에서 MyBatis는 SELECT 작업을 주로 처리하고 JPA는 INSERT, UPDATE, DELETE 작업을 처리
// 이로 인해 Entity와 DTO 간의 데이터 변환이 자주 필요
// 컨버터는 JPA Entity와 DTO 간의 변환을 중앙에서 관리함으로써, 각 도메인별 데이터 처리 일관성을 유지하고 코드의 중복을 줄이는 역할

public class csEntityDtoConverter {

    // QNAEntity를 QNADetailDTO로 변환하는 메서드
    public static QNADetailDTO convertToDetailDTO(QNAEntity qnaEntity) {
        return QNADetailDTO.builder()
                .qno(qnaEntity.getQno())
                .uno(qnaEntity.getUno())
                .pno(qnaEntity.getPno())
                .qtitle(qnaEntity.getQtitle())
                .qcontent(qnaEntity.getQcontent())
                .qanswer(qnaEntity.getQanswer())
                .qfilename(qnaEntity.getQfilename())
                .qregdate(qnaEntity.getQregdate())
                .qmoddate(qnaEntity.getQmoddate())
                .build();
    }

    // QNADetailDTO를 QNAEntity로 변환하는 메서드
    public static QNAEntity convertToEntity(QNADetailDTO qnaDetailDTO) {
        return QNAEntity.builder()
                .qno(qnaDetailDTO.getQno())
                .uno(qnaDetailDTO.getUno())
                .pno(qnaDetailDTO.getPno())
                .qtitle(qnaDetailDTO.getQtitle())
                .qcontent(qnaDetailDTO.getQcontent())
                .qanswer(qnaDetailDTO.getQanswer())
                .qdelete(false)  // 기본값으로 false 설정
                .qfilename(qnaDetailDTO.getQfilename())
                .build();
    }

    // QNAEntity를 QNAListDTO로 변환하는 메서드 (리스트에 사용)
    public static QNAListDTO convertToListDTO(QNAEntity qnaEntity) {
        return QNAListDTO.builder()
                .qno(qnaEntity.getQno())
                .uno(qnaEntity.getUno())
                .qtitle(qnaEntity.getQtitle())
                .qregdate(qnaEntity.getQregdate())
                .build();
    }
}
