package com.baby.mapper;

import java.util.List;

import com.baby.model.Criteria;
import com.baby.model.MemberVO;
import com.baby.model.OrderDTO;

public interface MemberMapper {

	//회원가입
	public void memberJoin(MemberVO member);
	
	//id중복 검사
	public int idCheck(String memberId);
	
	//로그인
	public MemberVO memberLogin(MemberVO member);
	
	// 주문자 주소 정보
	public MemberVO getMemberInfo(String memberId);
	
	/* 주문 상품 리스트 */
	public List<OrderDTO> getMyOrderList(String memberId);	
	
	
	
}
