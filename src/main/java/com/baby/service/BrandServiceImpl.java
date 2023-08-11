package com.baby.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baby.mapper.BrandMapper;
import com.baby.model.BrandVO;
import com.baby.model.Criteria;


@Service
public class BrandServiceImpl implements BrandService{
	
	private static final Logger Log = LoggerFactory.getLogger(BrandServiceImpl.class);
	
	@Autowired
	BrandMapper brandMapper;

	
	/*브랜드 등록*/
	@Override
	public void brandEnroll(BrandVO brand) throws Exception{
		
		brandMapper.brandEnroll(brand);
		
		
		
	}
	/*브랜드 목록*/
	@Override
	public List<BrandVO> brandGetList(Criteria cri) throws Exception {
		
		Log.info("(service)brandGetList()......" + cri);
		
		
		return brandMapper.brandGetList(cri);
	}
	/*브랜드 총 수*/
	@Override
	public int brandGetTotal(Criteria cri) throws Exception {
		
		Log.info("(service)brandGetTotal()......" + cri);
		
		return brandMapper.brandGetTotal(cri);
	}

}
