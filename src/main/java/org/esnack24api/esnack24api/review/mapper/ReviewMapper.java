package org.esnack24api.esnack24api.review.mapper;

import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.review.dto.ReviewDetailDTO;
import org.esnack24api.esnack24api.review.dto.ReviewListDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ReviewMapper {

    List<ReviewListDTO> getReviewList(@Param("pno") Long pno, @Param("rno") Long rno, @Param("pageRequest") PageRequest pageRequest);

    List<ReviewListDTO> getUserReviewList(@Param("uno") Long uno, @Param("rno") Long rno, @Param("pageRequest") PageRequest pageRequest);

    int countReview(Long rno);

    ReviewDetailDTO getOneReview(Long rno);

}
