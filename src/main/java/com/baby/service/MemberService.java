package com.baby.service;

import com.baby.model.MemberVO;

public interface MemberService {
	
	//회원가입
	public void memberJoin(MemberVO member) throws Exception;
	
	//id중복 검사
		public int idCheck(String memberId) throws Exception;

}
