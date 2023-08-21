package com.baby.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baby.model.Criteria;
import com.baby.model.ReviewDTO;
import com.baby.model.ReviewPageDTO;
import com.baby.service.ReviewService;

@RestController
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;	
	
	
	/* 리뷰 등록 */
	@PostMapping("/enroll")
	public void enrollReplyPOST(ReviewDTO dto) {
		reviewService.enrollReview(dto);
	}
	
	/* 리뷰 체크 */
	/* memberId, productId 파라미터 */
	/* 존재 : 1 / 존재x : 0 */
	@PostMapping("/check")
	public String reviewCheckPOST(ReviewDTO dto) {
		return reviewService.checkReview(dto);
	}
	
	/* 리뷰 페이징 */
	@GetMapping(value="/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ReviewPageDTO reviewListPOST(Criteria cri) {
		return reviewService.reviewList(cri);
	}
	
	/* 리뷰 수정 */
	@PostMapping("/update")
	public void reviewModifyPOST(ReviewDTO dto) {
		reviewService.updateReview(dto);
	}
	
	/* 리뷰 삭제 */
	@PostMapping("/delete")
	public void replyDeletePOST(ReviewDTO dto) {
		reviewService.deleteReview(dto);
	}
	

}
