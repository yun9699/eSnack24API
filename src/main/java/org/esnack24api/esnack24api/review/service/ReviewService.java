package org.esnack24api.esnack24api.review.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.common.page.PageResponse;
import org.esnack24api.esnack24api.product.domain.ProductEntity;
import org.esnack24api.esnack24api.review.domain.ReviewEntity;
import org.esnack24api.esnack24api.review.dto.ReviewDetailDTO;
import org.esnack24api.esnack24api.review.dto.ReviewEditDTO;
import org.esnack24api.esnack24api.review.dto.ReviewListDTO;
import org.esnack24api.esnack24api.review.dto.ReviewRegisterDTO;
import org.esnack24api.esnack24api.review.mapper.ReviewMapper;
import org.esnack24api.esnack24api.review.repository.ReviewRepository;
import org.esnack24api.esnack24api.user.domain.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;


    //리뷰 리스트 조회
    public PageResponse<ReviewListDTO> getReviewList(Long rno ,PageRequest pageRequest) {

        PageResponse<ReviewListDTO> pageResponse = PageResponse.<ReviewListDTO>with()
                .list(reviewMapper.getReviewList(rno,pageRequest))
                .total(reviewMapper.countReview(rno))
                .pageRequest(pageRequest)
                .build();


        return pageResponse;

    }

    //리뷰 상세 조회
    public ReviewDetailDTO getReviewDetail(Long rno) {

        ReviewDetailDTO result = reviewMapper.getOneReview(rno);

        return result;

    }

    //리뷰 등록
    public ReviewDetailDTO createReview(ReviewRegisterDTO dto) {

        UserEntity user = UserEntity.builder()
                .uno(dto.getUno())
                .build();

        ProductEntity product = ProductEntity.builder()
                .pno(dto.getPno())
                .build();

        ReviewEntity review = ReviewEntity.builder()
                .user(user)
                .product(product)
                .rstar(dto.getRstar())
                .rdelete(false)
                .rcontent(dto.getRcontent())
                .rimage(dto.getRimage())
                .build();

        ReviewEntity savedReview = reviewRepository.save(review);
        return reviewMapper.getOneReview(savedReview.getRno());

    }

    //리뷰 수정
    public ReviewDetailDTO updateReview(ReviewEditDTO dto) {

        ReviewEntity review = reviewRepository.findById(dto.getRno())
                .orElseThrow(() -> new IllegalArgumentException("Review not found" + dto.getRno()));

        review.updateReview(
                dto.getRcontent(),
                dto.getRstar(),
                dto.getRimage()
        );

        reviewRepository.save(review);

        return reviewMapper.getOneReview(dto.getRno());

    }

    //리뷰 삭제
    public void deleteReview(Long rno) {

        ReviewEntity review = reviewRepository.findById(rno)
                .orElseThrow(() -> new IllegalArgumentException("Review not found" + rno));

        review.deleteReview();
        reviewRepository.save(review);
    }







}
