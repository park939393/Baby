package com.baby.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baby.model.ProductVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AdminMapperTests {
	
	@Autowired
	private AdminMapper mapper;
	
	/*상품 등록*/
	
	@Test
	public void productEnrollTest() throws Exception{
		
		ProductVO product = new ProductVO();
		
		product.setProductName("mapper 테스트");
		product.setBrandId(123);
		product.setReleaseDate("2023-05-31");
		product.setCompany("회사아");
		product.setCateCode("0231");
		product.setProductPrice(20000);
		product.setProductStock(30);
		product.setProductDiscount(0.23);
		product.setProductIntro("상품 소개");
		product.setProductContents("상품 내용");
		
		mapper.productEnroll(product);
		
		
	}

}
