package com.baby.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baby.mapper.AdminMapper;
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

}
