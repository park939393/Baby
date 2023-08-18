package com.baby.model;

import java.util.Date;
import java.util.List;

public class ProductVO {
	
	/* 상품 id*/
	private int productId;
	
	/* 상품 이름*/
	private String productName;
	
	/* 브랜드 id*/
	private int brandId;
	
	/* 브랜드 이름*/
	private String brandName;
	
	/* 출시일*/
	private String releaseDate;
	
	/* 판매회사*/
	private String company;
	
	/* 카테고리 코드*/
	private String cateCode;
	
	/* 카테고리 이름*/
	private String cateName;
	
	/* 상품 가격*/
	private int productPrice;
	
	/* 상품재고*/
	private int productStock;
	
	/* 상품 할인률(백분율)*/
	private double productDiscount;
	
	/* 상품 소개*/
	private String productIntro;
	
	/* 상품 목차*/
	private String productContents;
	
	/* 등록 날짜*/
	private Date regDate;
	
	/* 수정 날짜*/
	private Date updateDate;
	
	/* 이미지 정보 */
	private List<AttachImageVO> imageList;
	

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCateCode() {
		return cateCode;
	}

	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductStock() {
		return productStock;
	}

	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}

	public double getProductDiscount() {
		return productDiscount;
	}

	public void setProductDiscount(double productDiscount) {
		this.productDiscount = productDiscount;
	}

	public String getProductIntro() {
		return productIntro;
	}

	public void setProductIntro(String productIntro) {
		this.productIntro = productIntro;
	}

	public String getProductContents() {
		return productContents;
	}

	public void setProductContents(String productContents) {
		this.productContents = productContents;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public List<AttachImageVO> getImageList() {
		return imageList;
	}

	public void setImageList(List<AttachImageVO> imageList) {
		this.imageList = imageList;
	}

	@Override
	public String toString() {
		return "ProductVO [productId=" + productId + ", productName=" + productName + ", brandId=" + brandId
				+ ", brandName=" + brandName + ", releaseDate=" + releaseDate + ", company=" + company + ", cateCode="
				+ cateCode + ", cateName=" + cateName + ", productPrice=" + productPrice + ", productStock="
				+ productStock + ", productDiscount=" + productDiscount + ", productIntro=" + productIntro
				+ ", productContents=" + productContents + ", regDate=" + regDate + ", updateDate=" + updateDate
				+ ", imageList=" + imageList + "]";
	}



	

}
