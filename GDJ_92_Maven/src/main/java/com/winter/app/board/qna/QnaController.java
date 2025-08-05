package com.winter.app.board.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.board.BoardVO;

@Controller
@RequestMapping("/qna/*")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@GetMapping("list")
	public String list (Model model) throws Exception {
		model.addAttribute("list", qnaService.list());
		
		return "board/list";
	}
	
	@GetMapping("detail")
	public String detail(QnaVO qnaVO, Model model) throws Exception {
		BoardVO boardVO = qnaService.detail(qnaVO);
		model.addAttribute("vo", boardVO);
		return "board/detail";
		
	}
	
	@GetMapping("add")
	public String insert() throws Exception{
		return "board/add";
	}
	
	@PostMapping("add")
	public String insesrt(QnaVO qnaVO) throws Exception {
		int result = qnaService.insert(qnaVO);
		
		return "redirect:./list"; 
			
	}
  
}
