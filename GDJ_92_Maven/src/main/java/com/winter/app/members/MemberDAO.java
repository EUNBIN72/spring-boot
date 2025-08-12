package com.winter.app.members;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
	
	// 회원가입 join
	public int join(MemberVO memberVO) throws Exception;
	
	// 프로필 profileInsert
	public int profileInsert(ProfileVO profileVO) throws Exception;

	//
	public int addRole(Map<String, Object> map) throws Exception;
	
	// login
	public MemberVO login(MemberVO memberVO) throws Exception;
	
	// logout
	public MemberVO logout(MemberVO memberVO) throws Exception;
	
	 
}
