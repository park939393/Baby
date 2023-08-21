package com.baby.mapper;

import java.util.List;

import com.baby.model.Criteria;
import com.baby.model.ReviewDTO;
import com.baby.model.UpdateReviewDTO;

public interface ReviewMapper {
	
	/* 리뷰 등록 */
	public int enrollReview(ReviewDTO dto);
	
	/* 리뷰 존재 체크 */
	public Integer checkReview(ReviewDTO dto);

	/* 리뷰 페이징 */
	public List<ReviewDTO> getReviewList(Criteria cri);
	
	/* 리뷰 총 갯수(페이징) */
	public int getReviewTotal(int productId);
	
	/* 리뷰 수정*/
	public int updateReview(ReviewDTO dto);
	
	/* 리뷰 한개 정보(수정페이지) */
	public ReviewDTO getUpdateReview(int reviewId);
	
	/* 리뷰 삭제 */
	public int deleteReview(int reviewId);
	
	/* 평점 평균 구하기 */
	public Double getRatingAverage(int productId);
	
	/* 평점 평균 반영하기 */
	public int updateRating(UpdateReviewDTO dto);
}
