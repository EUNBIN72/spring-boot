package com.winter.app.members;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.winter.app.products.ProductVO;

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
	
	// cartAdd
	public int cartAdd(Map<String, Object> map) throws Exception;
	
	//
	public List<ProductVO> cartList(MemberVO memberVO) throws Exception;
	
	// cart delete
	public int cartDelete(Map<String, Object> map) throws Exception;
	
}
