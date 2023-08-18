package com.baby.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baby.model.AttachImageVO;
import com.baby.model.ProductVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AdminServiceTests {

	@Autowired
	private AdminService service;

	/* 상품 등록 & 상품 이미지 등록 테스트 */
	@Test
	public void productEnrollTEsts() {

		ProductVO product = new ProductVO();

		// 상품 정보
		product.setProductName("service test");
		product.setBrandId(5);
		product.setReleaseDate("2023-08-18");
		product.setCompany("하하사");
		product.setCateCode("201001");
		product.setProductPrice(20000);
		product.setProductStock(300);
		product.setProductDiscount(0.30);
		product.setProductIntro("소개소개");
		product.setProductContents("내용");

		// 이미지 정보
		List<AttachImageVO> imageList = new ArrayList<AttachImageVO>();

		AttachImageVO image1 = new AttachImageVO();
		AttachImageVO image2 = new AttachImageVO();

		image1.setFileName("test Image 1");
		image1.setUploadPath("test image 1");
		image1.setUuid("test1111");

		image2.setFileName("test Image 2");
		image2.setUploadPath("test image 2");
		image2.setUuid("test2222");

		imageList.add(image1);
		imageList.add(image2);

		product.setImageList(imageList);

		// productEnroll() 메서드 호출
		service.productEnroll(product);

		System.out.println("등록된 VO : " + product);
	}

}
