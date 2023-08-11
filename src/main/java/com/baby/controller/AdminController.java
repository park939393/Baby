package com.baby.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.baby.model.BrandVO;
import com.baby.model.Criteria;
import com.baby.model.PageDTO;
import com.baby.service.BrandService;





@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private BrandService brandService;
	
	// 관리자 메인 페이지 이동
	@GetMapping("main")
	public void adminMainGET() throws Exception{
		
		logger.info("관리자 페이지 이동");
	}
	
	// 상품 관리 페이지 접속
	@GetMapping("goodsManage")
	public void goodsManageGET() throws Exception{
		logger.info("상품 관리 페이지 접속");
	}
	
	//상품 등록 페이지 접속
	@GetMapping("goodsEnroll")
	public void goodsEnrollGET()throws Exception{
		logger.info("상품 등록 페이지 접속");
	}
	
	//브랜드 등록 페이지 접속
	@GetMapping("brandEnroll")
	public void brandEnrollGET()throws Exception{
		logger.info("브랜드 등록 페이지 접속");
	}
	
	//브랜드 관리 페이지 접속
	@GetMapping("brandManage")
	public void brandManageGET(Criteria cri, Model model)throws Exception{
		logger.info("브랜드 관리 페이지 접속" + cri);
		
		/*브랜드 목록 출력 데이터*/
		List list = brandService.brandGetList(cri);
		
		if(!list.isEmpty()) {
			model.addAttribute("list",list);	// 브랜드 존재 경우
		} else {
			model.addAttribute("listCheck", "empty");	// 브랜드 존재하지 않을 경우
		}
		
		/* 페이지 이동 인터페이스 데이터 */
		int total = brandService.brandGetTotal(cri);
		
		PageDTO pageMaker = new PageDTO(cri, total);
		
		model.addAttribute("pageMaker", pageMaker);
	}
	
	/*작가 등록*/
	@PostMapping("brandEnroll.do")
	public String brandEnrollPOST(BrandVO brand, RedirectAttributes rttr) throws Exception{
		
		logger.info("brandEnroll :" + brand);
		
		brandService.brandEnroll(brand);  //브랜드 등록 쿼리 수행
		
		rttr.addFlashAttribute("enroll_result", brand.getBrandName());  // 등록 성공 메시지(브랜드 이름)
		
		return "redirect:/admin/brandManage";
		
	}
	
	

}
