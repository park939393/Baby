package com.baby.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baby.mapper.MemberMapper;
import com.baby.model.Criteria;
import com.baby.model.MemberVO;
import com.baby.model.OrderDTO;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberMapper membermapper;
	
	
	
	
	// 회원가입
	@Override
	public void memberJoin(MemberVO member) throws Exception {
		
		membermapper.memberJoin(member);
		
	}




	// 아이디 중복검사
	@Override
	public int idCheck(String memberId) throws Exception {
		
		
		return membermapper.idCheck(memberId);
	}




	//로그인
	@Override
	public MemberVO memberLogin(MemberVO member) {
		
		return membermapper.memberLogin(member);
	}



	
	/* 주문자 정보 */
	@Override
	public MemberVO getMemberInfo(String memberId) {
		
		return membermapper.getMemberInfo(memberId);
		
	}




	@Override
	public List<OrderDTO> getMyOrderList(String memberId) {
		
		return membermapper.getMyOrderList(memberId);
	}




	



	
	
	

}
