package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.board.BoardVO;

@Controller
@RequestMapping(value="/notice/*")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("list")
	public String list(Model model) throws Exception {
		// Model 객체 : spring 컨트롤러에서 JSP(뷰)로 데이터를 전달할 때 사용
		List<BoardVO> list = noticeService.list();
		
		model.addAttribute("list", list);
		
		return "notice/list";
	}
	
	@GetMapping("detail")
	public String detail(NoticeVO noticeVO, Model model) throws Exception {
		BoardVO detail = noticeService.detail(noticeVO);
		model.addAttribute("detail", detail);
		return "notice/detail";
	}
	
//	@GetMapping("add")
//	public void insert() throws Exception {
//		NoticeVO noticeVO = new NoticeVO();
//		noticeVO.setBoardTitle("title");
//		noticeVO.setBoardContents("contents");
//		noticeVO.setBoardWriter("writer");
//		int result = noticeDAO.insert(noticeVO);
//	}
//	
//	@GetMapping("update")
//	public void update() throws Exception {
//		NoticeVO noticeVO = new NoticeVO();
//		noticeVO.setBoardTitle("title");
//		noticeVO.setBoardContents("contents");
//		noticeVO.setBoardWriter("writer");
//		int result = noticeDAO.update(noticeVO);
//	}
//	
//	@GetMapping("delete")
//	public void delete() throws Exception {
//
//		int result = noticeDAO.delete();
//	}
	
	

}
