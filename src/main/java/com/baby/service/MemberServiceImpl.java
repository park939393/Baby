package com.baby.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baby.mapper.MemberMapper;
import com.baby.model.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberMapper membermapper;
	
	
	
	
	
	@Override
	public void memberJoin(MemberVO member) throws Exception {
		
		membermapper.memberJoin(member);
		
	}





	@Override
	public int idCheck(String memberId) throws Exception {
		
		
		return membermapper.idCheck(memberId);
	}
	
	
	

}
