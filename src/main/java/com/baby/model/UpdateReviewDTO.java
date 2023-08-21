package com.baby.model;

public class UpdateReviewDTO {
	
	private Integer productId;
	
	private double ratingAvg;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public double getRatingAvg() {
		return ratingAvg;
	}

	public void setRatingAvg(double ratingAvg) {
		this.ratingAvg = ratingAvg;
	}

	@Override
	public String toString() {
		return "UpdateReplyDTO [productId=" + productId + ", ratingAvg=" + ratingAvg + "]";
	}
	
	

}
