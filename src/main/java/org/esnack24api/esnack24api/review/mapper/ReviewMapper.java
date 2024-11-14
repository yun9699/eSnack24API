package org.esnack24api.esnack24api.review.mapper;

import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.review.dto.ReviewDetailDTO;
import org.esnack24api.esnack24api.review.dto.ReviewListDTO;

import java.util.List;


public interface ReviewMapper {

    List<ReviewListDTO> getReviewList(Long rno ,PageRequest pageRequest);

    int countReview(Long rno);

    ReviewDetailDTO getOneReview(Long rno);

}
