package com.baby.model;

import java.util.Date;

public class BrandVO {
	
	/*브랜드 아이디*/
	private int brandId;
	
	/*브랜드 이름*/
	private String brandName;
	
	/*국가 id*/
	private String nationId;
	
	/*브랜드 국가*/
	private String nationName;
	
	/*브랜드 소개*/
	private String brandIntro;
	
	/*등록 날짜*/
	private Date regDate;
	
	/*수정날짜*/
	private Date updateDate;

	

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getNationId() {
		return nationId;
	}

	public void setNationId(String nationId) {
		this.nationId = nationId;
		if(nationId.equals("01")) {
			this.nationName = "국내";
		}else {
			this.nationName = "국외";
		}
	}

	public String getNationName() {
		return nationName;
	}

	public void setNationName(String nationName) {
		this.nationName = nationName;
	}

	public String getBrandIntro() {
		return brandIntro;
	}

	public void setBrandIntro(String brandIntro) {
		this.brandIntro = brandIntro;
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

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	@Override
	public String toString() {
		return "BrandVO [brandId=" + brandId + ", brandName=" + brandName + ", nationId=" + nationId + ", nationName="
				+ nationName + ", brandIntro=" + brandIntro + ", regDate=" + regDate + ", updateDate=" + updateDate
				+ "]";
	}

	
	
	
	
	
	
}
