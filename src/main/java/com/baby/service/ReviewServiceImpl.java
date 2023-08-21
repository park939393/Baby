package com.baby.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baby.mapper.ReviewMapper;
import com.baby.model.Criteria;
import com.baby.model.PageDTO;
import com.baby.model.ReviewDTO;
import com.baby.model.ReviewPageDTO;
import com.baby.model.UpdateReviewDTO;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewMapper reviewMapper;

	/* 리뷰등록 */
	@Override
	public int enrollReview(ReviewDTO dto) {

		int result = reviewMapper.enrollReview(dto);
		
		setRating(dto.getProductId());

		return result;
	}

	/* 중복 리뷰 체크 */
	@Override
	public String checkReview(ReviewDTO dto) {

		Integer result = reviewMapper.checkReview(dto);

		if (result == null) {
			return "0";
		} else {
			return "1";
		}

	}

	@Override
	public ReviewPageDTO reviewList(Criteria cri) {
		ReviewPageDTO dto = new ReviewPageDTO();

		dto.setList(reviewMapper.getReviewList(cri));
		dto.setPageInfo(new PageDTO(cri, reviewMapper.getReviewTotal(cri.getProductId())));

		return dto;
	}

	/* 리뷰 수정 */
	@Override
	public int updateReview(ReviewDTO dto) {

		int result = reviewMapper.updateReview(dto);
		
		setRating(dto.getProductId());

		return result;
	}

	@Override
	public ReviewDTO getUpdateReview(int reviewId) {

		return reviewMapper.getUpdateReview(reviewId);
	}

	
	/* 리뷰 삭제 */
	@Override
	public int deleteReview(ReviewDTO dto) {

		int result = reviewMapper.deleteReview(dto.getReviewId());

		setRating(dto.getProductId());
		
		return result;
	}

	
	public void setRating(int productId) {

		Double ratingAvg = reviewMapper.getRatingAverage(productId);

		if (ratingAvg == null) {
			ratingAvg = 0.0;
		}
		ratingAvg = (double) (Math.round(ratingAvg*10));
		ratingAvg = ratingAvg / 10;

		UpdateReviewDTO urd = new UpdateReviewDTO();
		urd.setProductId(productId);
		urd.setRatingAvg(ratingAvg);

		reviewMapper.updateRating(urd);

	}

}
