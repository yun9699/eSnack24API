package org.esnack24api.esnack24api.review.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.common.page.PageRequest;
import org.esnack24api.esnack24api.common.page.PageResponse;
import org.esnack24api.esnack24api.review.dto.ReviewDetailDTO;
import org.esnack24api.esnack24api.review.dto.ReviewEditDTO;
import org.esnack24api.esnack24api.review.dto.ReviewListDTO;
import org.esnack24api.esnack24api.review.dto.ReviewRegisterDTO;
import org.esnack24api.esnack24api.review.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
@Log4j2
@PreAuthorize("permitAll()")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("list")
    public ResponseEntity<PageResponse<ReviewListDTO>> getReviewLists(@RequestParam Long rno ,PageRequest pageRequest) {

        return ResponseEntity.ok(reviewService.getReviewList(rno, pageRequest));
    }

    @GetMapping("detail/{rno}")
    public ResponseEntity<ReviewDetailDTO> getReviewDetails(@PathVariable("rno") Long rno) {

        return ResponseEntity.ok(reviewService.getReviewDetail(rno));
    }

    @PostMapping("add")
    public ResponseEntity<ReviewDetailDTO> registerReview(@RequestBody ReviewRegisterDTO reviewRegisterDTO) {

        return ResponseEntity.ok(reviewService.createReview(reviewRegisterDTO));
    }

    @PostMapping("edit/{rno}")
    public ResponseEntity<ReviewDetailDTO> editReview(
            @PathVariable Long rno ,
            @RequestBody ReviewEditDTO reviewEditDTO) {
        reviewEditDTO.setRno(rno);

        return ResponseEntity.ok(reviewService.updateReview(reviewEditDTO));
    }

    @PostMapping("delete/{rno}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long rno) {
        reviewService.deleteReview(rno);
        return ResponseEntity.noContent().build();
    }


}
