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
	// 매개변수가 Map :  여러 파라미터를 전달할 때 사용(한 번에 객체 형태로 전달)
	// 어떤 회원의 장바구니인지(username), 어떤 상품을 삭제할지(productName)
	public int cartDelete(Map<String, Object> map) throws Exception;
	
	// account insert
	public int insertAccount() throws Exception;
	
}
