package com.winter.app.members;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
	
	// 회원가입 join
	public int join(MemberVO memberVO) throws Exception;
	
	// 프로필 profileInsert
	public int profileInsert(ProfileVO profileVO) throws Exception;

}
