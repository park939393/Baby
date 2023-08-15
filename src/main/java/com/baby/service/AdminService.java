package com.baby.service;

import java.util.List;

import com.baby.model.CateVO;
import com.baby.model.Criteria;
import com.baby.model.ProductVO;

public interface AdminService {
	
	/*상품 등록*/
	public void productEnroll(ProductVO product);

	/* 카테고리 리스트 */
	public List<CateVO> cateList();
	
	/*상품 리스트*/
	public List<ProductVO> productGetList(Criteria cri);
	
	/*상품 총 개수*/
	public int productGetTotal(Criteria cri);
	
	/*상품 조회 페이지*/
	public ProductVO productGetDetail(int productId);
	
}