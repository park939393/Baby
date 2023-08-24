package com.baby.service;

import java.util.List;

import com.baby.model.Criteria;
import com.baby.model.MemberVO;
import com.baby.model.OrderDTO;

public interface MemberService {

	/* 회원가입 */
	public void memberJoin(MemberVO member) throws Exception;

	/* id중복 검사 */
	public int idCheck(String memberId) throws Exception;

	/* 로그인 */
	public MemberVO memberLogin(MemberVO member);

	/* 주문자 정보 */
	public MemberVO getMemberInfo(String memberId);
	
	/* 주문 상품 리스트 */
	public List<OrderDTO> getMyOrderList(String memberId);
	

	

}
