package com.baby.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baby.model.MemberVO;
import com.baby.service.MemberService;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService MemberService;
	
	
	//회원가입 페이지 이동
	@GetMapping("/join")
	public void joinGET() {
		
		logger.info("회원가입 페이지 진입");
		
	}
	
	//회원가입
	@PostMapping("/join")
	public String joinPOST(MemberVO member) throws Exception{
		logger.info("join 진입");
		
		//회원가입 서비스 실행
		MemberService.memberJoin(member);
		
		logger.info("join Service 성공");
		
		return "redirect:/main";
	}
	
	//로그인 페이지 이동
	@GetMapping("/login")
		public void loginGET() {
			
			logger.info("로그인 페이지 진입");
			
		}
	
	//아이디 중복 검사
	@PostMapping("/memberIdChk")
	@ResponseBody
	public String memberIdChkPOST(String memberId) throws Exception{
		
//			logger.info("memberIdChk()진입");
		
		int result = MemberService.idCheck(memberId);
		
		logger.info("결과값 = " + result);
		
		if(result != 0) {
			return "fail";  //아이디 중복
		}else {
		
		return "success"; // 아이디 생성가능
			
	}
			

}
}
