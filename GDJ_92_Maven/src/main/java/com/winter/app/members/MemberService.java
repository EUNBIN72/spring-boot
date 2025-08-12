package com.winter.app.members;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.commons.FileManager;
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
	
	

	
}
