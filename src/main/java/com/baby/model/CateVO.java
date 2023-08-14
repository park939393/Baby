package com.baby.model;

public class CateVO {
	
	/* 카텍고리 등급*/
	private int tier;
	
	/* 카테고리 이름*/
	private String cateName;
	
	/* 카테고리 넘버*/
	private int cateCode;
	
	/*상위 카테고리*/
	private String cateParent;

	public int getTier() {
		return tier;
	}

	public void setTier(int tier) {
		this.tier = tier;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public int getCateCode() {
		return cateCode;
	}

	public void setCateCode(int cateCode) {
		this.cateCode = cateCode;
	}

	public String getCateParent() {
		return cateParent;
	}

	public void setCateParent(String cateParent) {
		this.cateParent = cateParent;
	}

	@Override
	public String toString() {
		return "CateVO [tier=" + tier + ", cateName=" + cateName + ", cateCode=" + cateCode + ", cateParent="
				+ cateParent + "]";
	}
	
	
}
