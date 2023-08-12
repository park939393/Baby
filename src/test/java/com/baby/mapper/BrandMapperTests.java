package com.baby.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baby.model.BrandVO;
import com.baby.model.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BrandMapperTests {
	
	@Autowired
	private BrandMapper mapper;
	
//	/*브랜드 등록 테스트*/
//	@Test
//	public void brandEnroll() throws Exception{
//		
//		BrandVO brand = new BrandVO();
//		
//		brand.setNationId("01");
//		brand.setBrandName("테스트");
//		brand.setBrandIntro("테스트 소개");
//		
//		mapper.brandEnroll(brand);
//	}
	
	/*브랜드 목록 테스트*/
//	@Test
//	public void brandGetListTest() throws Exception{
//		
//		Criteria cri = new Criteria(3,10);   //3페이지 & 10개 행 표시
//		cri.setKeyword("근두");
//		
//		List<BrandVO> list = mapper.brandGetList(cri);
//		
//		for(int i = 0; i < list.size(); i++) {
//			System.out.println("list" + i + "............" + list.get(i));
//		}
//	}
	
	/*브랜드 총 수*/
//	@Test
//	public void brandGetTotal() throws Exception{
//		
//		Criteria cri = new Criteria();
//		cri.setKeyword("근두");
//		
//		int total = mapper.brandGetTotal(cri);
//		
//		System.out.println("total......." + total);
//	}
	/*작가 상세 페이지*/
//	@Test
//	public void brandGetDetailTest() {
//		
//		int brandId = 30;
//		
//		BrandVO brand = mapper.brandGetDetail(brandId);
//		
//		System.out.println("brand......." + brand);
//	}
	
	/*브랜드 수정*/
	@Test
	public void brandModifyTest() {
		
		BrandVO brand = new BrandVO();
		
		brand.setBrandId(1);
		System.out.println("수정 전 ........." + mapper.brandGetDetail(brand.getBrandId()));
		
		brand.setBrandName("수정");
		brand.setNationId("01");
		brand.setBrandIntro("소개 수정완료");
		
		mapper.brandModify(brand);
		System.out.println("수정 후 ........." + mapper.brandGetDetail(brand.getBrandId()));
		
		
		
	}

}
