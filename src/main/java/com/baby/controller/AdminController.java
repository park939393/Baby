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
import com.baby.model.ProductVO;
import com.baby.service.AdminService;
import com.baby.service.BrandService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private BrandService brandService;

	@Autowired
	private AdminService adminService;

	// 관리자 메인 페이지 이동
	@GetMapping("main")
	public void adminMainGET() throws Exception {

		logger.info("관리자 페이지 이동");
	}

	// 상품 관리 페이지 접속
	@GetMapping("productManage")
	public void productManageGET(Criteria cri, Model model) throws Exception {

		logger.info("상품 관리 페이지 접속");

		/* 상품 리스트 데이터 */
		List list = adminService.productGetList(cri);

		if (!list.isEmpty()) {
			model.addAttribute("list", list);
		} else {
			model.addAttribute("listCheck", "empty");
			return;
		}

		/* 페이지 인터페이스 데이터 */
		model.addAttribute("pageMaker", new PageDTO(cri, adminService.productGetTotal(cri)));

	}

	// 상품 등록 페이지 접속
	@GetMapping("productEnroll")
	public void productEnrollGET(Model model) throws Exception {

		logger.info("상품 등록 페이지 접속");

		ObjectMapper objm = new ObjectMapper();

		List list = adminService.cateList();

		String cateList = objm.writeValueAsString(list);

		model.addAttribute("cateList", cateList);

		logger.info("변경 전.........." + list);
		logger.info("변경 후.........." + cateList);

	}

	// 브랜드 등록 페이지 접속
	@GetMapping("brandEnroll")
	public void brandEnrollGET() throws Exception {
		logger.info("브랜드 등록 페이지 접속");
	}

	// 브랜드 관리 페이지 접속
	@GetMapping("brandManage")
	public void brandManageGET(Criteria cri, Model model) throws Exception {
		logger.info("브랜드 관리 페이지 접속" + cri);

		/* 브랜드 목록 출력 데이터 */
		List list = brandService.brandGetList(cri);

		if (!list.isEmpty()) {
			model.addAttribute("list", list); // 브랜드 존재 경우
		} else {
			model.addAttribute("listCheck", "empty"); // 브랜드 존재하지 않을 경우
		}

		/* 페이지 이동 인터페이스 데이터 */
		int total = brandService.brandGetTotal(cri);

		PageDTO pageMaker = new PageDTO(cri, total);

		model.addAttribute("pageMaker", pageMaker);
	}

	/* 브랜드 등록 */
	@PostMapping("brandEnroll.do")
	public String brandEnrollPOST(BrandVO brand, RedirectAttributes rttr) throws Exception {

		logger.info("brandEnroll :" + brand);

		brandService.brandEnroll(brand); // 브랜드 등록 쿼리 수행

		rttr.addFlashAttribute("enroll_result", brand.getBrandName()); // 등록 성공 메시지(브랜드 이름)

		return "redirect:/admin/brandManage";

	}

	/* 브랜드 상세 페이지 */
	@GetMapping({ "/brandDetail", "/brandModify" })
	public void brandGetInfoGET(int brandId, Criteria cri, Model model) throws Exception {

		logger.info("brandGetInfo :" + brandId);

		brandService.brandGetDetail(brandId);

		/* 브랜드 관리 페이지 정보 */
		model.addAttribute("cri", cri);

		/* 선택 브랜드 정보 */
		model.addAttribute("brandInfo", brandService.brandGetDetail(brandId));

	}

	/* 브랜드 정보 수정 */
	@PostMapping("/brandModify")
	public String brandModifyPOST(BrandVO brand, RedirectAttributes rttr) throws Exception {

		logger.info("brandModifyPOST :" + brand);

		int result = brandService.brandModify(brand);

		rttr.addFlashAttribute("modify_result", result);

		return "redirect:/admin/brandManage";

	}

	/* 상품 등록 */
	@PostMapping("/productEnroll")
	public String productEnrollPOST(ProductVO product, RedirectAttributes rttr) {

		logger.info("productEnrollPOST :" + product);

		adminService.productEnroll(product);

		rttr.addFlashAttribute("enroll_result", product.getProductName());

		return "redirect:/admin/productManage";

	}

	/* 브랜드 검색 팝업창 */
	@GetMapping("/brandPop")
	public void brandPopGET(Criteria cri, Model model) throws Exception {

		logger.info("brandPopGET.....");

		cri.setAmount(5);

		/* 게시물 출력 데이터 */
		List list = brandService.brandGetList(cri);

		if (!list.isEmpty()) {
			model.addAttribute("list", list); // 브랜드 존재 경우
		} else {
			model.addAttribute("listCheck", "empty"); // 브랜드 존재하지 않을 경우
		}

		/* 페이지 이동 인터페이스 데이터 */
		model.addAttribute("pageMaker", new PageDTO(cri, brandService.brandGetTotal(cri)));

		logger.info("brandPopGET.....");

	}

	/* 상품 조회 페이지 */
	@GetMapping("/productDetail")
	public void productGetInfoGET(int productId, Criteria cri, Model model) throws JsonProcessingException {

		logger.info("productDetail() :" + productId);

		ObjectMapper mapper = new ObjectMapper();

		/* 카테고리 리스트 데이터 */
		model.addAttribute("cateList", mapper.writeValueAsString(adminService.cateList()));

		/* 목록 페이지 조건 정보 */
		model.addAttribute("cri", cri);

		/* 조회 페이지 정보 */
		model.addAttribute("productInfo", adminService.productGetDetail(productId));

	}

}
