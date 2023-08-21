package com.baby.model;

import java.util.List;

public class ReviewPageDTO {
	
	List<ReviewDTO> list;
	
	PageDTO pageInfo;

	public List<ReviewDTO> getList() {
		return list;
	}

	public void setList(List<ReviewDTO> list) {
		this.list = list;
	}

	public PageDTO getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageDTO pageInfo) {
		this.pageInfo = pageInfo;
	}

	@Override
	public String toString() {
		return "ReviewPageDTO [list=" + list + ", pageInfo=" + pageInfo + "]";
	}
	
	
}
