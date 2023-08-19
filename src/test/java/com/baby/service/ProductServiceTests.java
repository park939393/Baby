package com.baby.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baby.model.Criteria;
import com.baby.model.ProductVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ProductServiceTests {
	
	@Autowired
	ProductService service;

//	@Test
//	public void getCateInfoListTest1() {
//		Criteria cri = new Criteria();
//	
//		String type = "TC";
//		//String keyword = "테스트";
//		String keyword = "현";	
//		String cateCode="205001";
//
//		cri.setType(type);
//		cri.setKeyword(keyword);
//		cri.setCateCode(cateCode);
//		
//		System.out.println("List<CateFilterDTO> : " + service.getCateInfoList(cri));
//		
//	}
	
	/*상품 상세 정보*/
	@Test
	public void getProductInfoTest() {
		
		int productId = 17;
		
		ProductVO pI = service.getProductInfo(productId);
		
		System.out.println("==결과==");
		System.out.println("전체 : " + pI);
		System.out.println("productId : " + pI.getProductId() );
		System.out.println("이미지 정보 : " + pI.getImageList().isEmpty());
		
		
	}
}
