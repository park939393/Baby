package com.baby.service;

import java.util.List;

import com.baby.model.CateVO;
import com.baby.model.ProductVO;

public interface AdminService {
	
	/*상품 등록*/
	public void productEnroll(ProductVO product);

	/* 카테고리 리스트 */
	public List<CateVO> cateList();
}
