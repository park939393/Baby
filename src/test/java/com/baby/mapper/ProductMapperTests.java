package com.baby.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baby.model.Criteria;
import com.baby.model.ProductVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ProductMapperTests {
	
	@Autowired
	private ProductMapper mapper;

	
//	@Test
//	public void getProductListTest() {
//		
//		Criteria cri = new Criteria();
//		// 테스트 키워드
//		//cri.setKeyword("test");
//		System.out.println("cri : " + cri);
//		
//		List<ProductVO> list = mapper.getProductList(cri);
//		System.out.println("list : " + list);
//		
//		System.out.println("==========");
//		int productTotal = mapper.productGetTotal(cri);
//		System.out.println("total : " + productTotal);
//		
//	}
	
/* 브랜드 id 리스트 요청 */
	
	@Test
	public void getBrandId() {
		
		String keyword = "수정";
		
		String[] list = mapper.getBrandIdList(keyword);
		
		System.out.println("결과 : " + list.toString());
		
		for(String id : list) {
			System.out.println("개별 결과 : " + id);
		}
		
		
	}
}
