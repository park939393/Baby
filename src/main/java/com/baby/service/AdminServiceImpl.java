package com.baby.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baby.mapper.AdminMapper;
import com.baby.model.CateVO;
import com.baby.model.Criteria;
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
	@Override
	public List<ProductVO> productGetList(Criteria cri) {
		
		log.info("(service)productGetList....");
		
		return adminMapper.productGetList(cri);
	}
	@Override
	public int productGetTotal(Criteria cri) {
		
		log.info("(service)productGetTotal....");
		
		return adminMapper.productGetTotal(cri);
	}
	@Override
	public ProductVO productGetDetail(int productId) {
		
		log.info("(service)productGetDetail....");
		
		return adminMapper.productGetDetail(productId);
	}
	
	/* 상품 정보 수정 */
	@Override
	public int productModify(ProductVO vo) {
		
		log.info("(service)productGetDetail....");
		
		return adminMapper.productModify(vo);
	}
	/* 상품 정보 삭제 */
	@Override
	public int productDelete(int productId) {
		
		log.info("(service)productDelete....");
		
		return adminMapper.productDelete(productId);
	}
	
	/* 브랜드 정보 삭제 */
	@Override
	public int brandDelete(int brandId) {
		
		log.info("brandDelete..........");
		
		return adminMapper.brandDelete(brandId);
	}

}
