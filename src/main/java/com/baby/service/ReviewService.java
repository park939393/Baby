package com.baby.service;

import com.baby.model.Criteria;
import com.baby.model.ReviewDTO;
import com.baby.model.ReviewPageDTO;

public interface ReviewService {
	
	/* 리뷰 등록 */
	public int enrollReview(ReviewDTO dto);
	
	/* 리뷰 존재 체크 */
	public String checkReview(ReviewDTO dto);
	
	/* 리뷰 페이징 */
	public ReviewPageDTO reviewList(Criteria cri);
		
	/* 리뷰 수정*/
	public int updateReview(ReviewDTO dto);
	
	/* 리뷰 한개 정보(수정페이지) */
	public ReviewDTO getUpdateReview(int reviewId);
	
	/* 리뷰 삭제 */
	public int deleteReview(ReviewDTO dto);

}
