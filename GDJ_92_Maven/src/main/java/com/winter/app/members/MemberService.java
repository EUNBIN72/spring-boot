package com.winter.app.members;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.commons.FileManager;
import com.winter.app.products.ProductVO;
import com.winter.app.transaction.Transaction;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload}")
	private String upload;
	
	@Value("${board.member}")
	private String board;
	
	// 검증 메서드
	// 입력한 비번 두개가 같은지 검사
	public boolean hasMemberError(MemberVO memberVO, BindingResult bindingResult) throws Exception {
		
		boolean check = false;
		// check 값이 true이면 검증실패
		// check 값이 false이면 검증통과
		
		// 1. Annotation 검증
		check = bindingResult.hasErrors();
		
		// 2. 사용자 정의로 패스워드가 일치하는지 검사
		// 입력한 비밀번호가 같지 않으면 ture를
		if(!memberVO.getPassword().equals(memberVO.getPasswordCheck())) {
			check = true;
			bindingResult.rejectValue("passwordCheck", "member.password.notEqual");
		}
		
		// 3. ID 중복 검사
		MemberVO result = memberDAO.login(memberVO);
		
		// result가 null이 아니라면 ID가 중복임
		if (result != null) {
			check = true;
			bindingResult.rejectValue("username", "member.id.equal");
		}
		
		// 같으면 check를 리턴
		return check;
	}
	
	@Autowired
	private Transaction transaction;
	
	public int join(MemberVO memberVO, MultipartFile profile) throws Exception {
		int result = memberDAO.join(memberVO);
		
		ProfileVO profileVO = new ProfileVO();
		profileVO.setUsername(memberVO.getUsername());
		profileVO.setSaveName("default.jpg");
		profileVO.setOriName("default.jpg");
		
		if(profile != null && !profile.isEmpty()) {
			profileVO.setSaveName(fileManager.fileSave(upload+board, profile));
			profileVO.setOriName(profile.getOriginalFilename());
		
		}
		result =  memberDAO.profileInsert(profileVO);
		
		Map<String, Object> map = new HashMap<>();
		map.put("username", memberVO.getUsername());
		// 기본적으로 일반 회원인 3이 들어감
		map.put("roleNum", 3);
		
		result = memberDAO.addRole(map);
		return result;
		
	}
	
	public MemberVO login(MemberVO memberVO) throws Exception {
		MemberVO checkVO = memberDAO.login(memberVO);

		if(checkVO != null && memberVO.getPassword().equals(checkVO.getPassword())) {
			return checkVO;
		}
		
		return null;
		
	}
	
	public int update(MemberVO memberVO) throws Exception {
		return memberDAO.update(memberVO);
	}
	
	
	public int cartAdd(Map<String, Object> map) throws Exception {
		return memberDAO.cartAdd(map);
	}

	
	public List<ProductVO> cartList(MemberVO memberVO) throws Exception{
		// 페이징 처리 해야 함
		return memberDAO.cartList(memberVO);
	}
	
	// cart delete
	public int cartDelete(Long [] productNum, MemberVO memberVO)throws Exception{
		Map<String, Object> map = new HashMap<>();
		map.put("username", memberVO.getUsername());
		map.put("list", Arrays.asList(productNum));
	
	return memberDAO.cartDelete(map);
	
	}

	
	
	
}
