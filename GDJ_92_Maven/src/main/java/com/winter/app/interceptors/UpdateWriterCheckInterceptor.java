package com.winter.app.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelExtensionsKt;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.winter.app.board.BoardVO;
import com.winter.app.board.notice.NoticeDAO;
import com.winter.app.members.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 관리자라도 작성자가 아니면 수정과 삭제가 불가능하게 필터
public class UpdateWriterCheckInterceptor implements HandlerInterceptor {

	@Autowired
	private NoticeDAO noticeDAO;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	// Controller 종료 후
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		if(request.getMethod().toUpperCase().equals("POST")) {
			return;
		}
		
		// 로그인 사용자 정보
		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("member");
		
		
		// 작성자
		BoardVO boardVO = (BoardVO)modelAndView.getModel().get("vo");
		
		if(!memberVO.getUsername().equals(boardVO.getBoardWriter())) {
			modelAndView.setViewName("commons/result");
			modelAndView.addObject("msg", "작성자만 가능합니다.");
			modelAndView.addObject("url", "./list");
			modelAndView.addObject("vo", null);
		}
	}
}
