package com.baby.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baby.mapper.AdminMapper;
import com.baby.model.CateVO;
import com.baby.model.ProductVO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminMapper adminMapper;

	/*상품 등록*/
	@Override
	public void productEnroll(ProductVO product) {
		
		log.info("(service)productEnroll....");
		
		adminMapper.productEnroll(product);
		
		
	}
	/*카테고리 리스트*/
	@Override
	public List<CateVO> cateList() {
		
		log.info("(service)cateList....");
		
		return adminMapper.cateList();
	}

}
