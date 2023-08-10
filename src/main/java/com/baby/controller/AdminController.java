package com.baby.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;





@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	// 관리자 메인 페이지 이동
	@GetMapping("main")
	public void adminMainGET() throws Exception{
		
		logger.info("관리자 페이지 이동");
	}
	
	// 상품 관리 페이지 접속
	@GetMapping("goodsManage")
	public void goodsManageGET() throws Exception{
		logger.info("상품 등록 페이지 접속");
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
	public void brandManageGET()throws Exception{
		logger.info("브랜드 등록 페이지 접속");
	}

}
