package com.baby.service;

import java.util.List;

import com.baby.model.CateFilterDTO;
import com.baby.model.CateVO;
import com.baby.model.Criteria;
import com.baby.model.ProductVO;
import com.baby.model.SelectDTO;


public interface ProductService {
	
	/* 상품 검색*/
	public List<ProductVO> getProductList(Criteria cri);
	
	
	/* 상품 총 갯수*/
	public int productGetTotal(Criteria cri);
	
	/* 국내 카테고리 리스트 */
	public List<CateVO> getCateCode1();
	
	/* 외국 카테고리 리스트 */
	public List<CateVO> getCateCode2();
	
	/* 검색결과 카테고리 필터 정보 */
	public List<CateFilterDTO> getCateInfoList(Criteria cri);
	
	/* 상품 정보 */
	public ProductVO getProductInfo(int productId);
	
	/* 상품 id 이름 */
	public ProductVO getProductIdName(int productId);
	
	/* 평점순 상품 정보 */
	public List<SelectDTO> likeSelect();

}
