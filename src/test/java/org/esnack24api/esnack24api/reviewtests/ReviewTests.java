package org.esnack24api.esnack24api.reviewtests;

import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.review.dto.ReviewDetailDTO;
import org.esnack24api.esnack24api.review.dto.ReviewEditDTO;
import org.esnack24api.esnack24api.review.dto.ReviewRegisterDTO;
import org.esnack24api.esnack24api.review.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Log4j2
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReviewTests {

    @Autowired
    private ReviewService reviewService;

    // 리뷰 등록 테스트
    @Test
    @Transactional
    @Commit
    public void testRegisterReview() {
        ReviewRegisterDTO dto = ReviewRegisterDTO.builder()
                .uno(3L)
                .pno(1L)
                .rstar(4)
                .rcontent("리뷰 작성 예시")
                .rimage("testImage")
                .build();

        ReviewDetailDTO result = reviewService.createReview(dto);
        log.info(result);
    }

    // 리뷰 수정 테스트
    @Test
    @Transactional
    @Commit
    public void testEditReview() {
        ReviewEditDTO dto = ReviewEditDTO.builder()
                .rno(8L)
                .rstar(2)
                .rimage("testUpdateImage")
                .rcontent("testUpdateContent")
                .build();

        ReviewDetailDTO result = reviewService.updateReview(dto);
        log.info(dto);
    }

    //리뷰 삭제 테스트
    @Test
    @Transactional
    @Commit
    public void testDeleteReview() {
        reviewService.deleteReview(8L);

    }









}
