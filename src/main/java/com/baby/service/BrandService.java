package com.baby.service;

import java.util.List;

import com.baby.model.BrandVO;
import com.baby.model.Criteria;

public interface BrandService {
	
	/*브랜드등록*/
	public void brandEnroll(BrandVO brand) throws Exception;
	
	/*브랜드 목록*/
	public List<BrandVO> brandGetList(Criteria cri) throws Exception; 
	
	/*브랜드 총 수*/
	public int brandGetTotal(Criteria cri) throws Exception;


	
	

}
