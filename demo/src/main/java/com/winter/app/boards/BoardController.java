package com.winter.app.boards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/board/*")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	// /board/list => 목록
	public String list() {
		System.out.println("list");
		boardService.list();
		return "board/list";
	}
	
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	// /board/detail => 
	public void detail(BoardVO boardVO) {
		// String num = request.getParameter("num");
		// int n = Integer.parseInt(num);
//		BoardVO boardVO = new BoardVO();
//		boardVO.setNum(num);
//		boardVO.setName(kind);
		
		System.out.println("detail : " + boardVO);
	}

}
