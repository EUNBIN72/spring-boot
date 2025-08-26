package com.winter.app.configs.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	// 로그인이 성공했을 때 실행
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		// 서블릿을 한정적으로 사용
		String rememberId = request.getParameter("rememberId");
		
		if(rememberId != null && rememberId.equals("1")) {
			// 쿠키 객체 생성(Key/Value 형태)
			Cookie cookie = new Cookie("", "");
			// 쿠키 유지 시간 설정(초 단위)
			cookie.setMaxAge(30 * 60); // 30분 유지
			// 응답에 쿠키 추가(브라우저에 전달)
			response.addCookie(cookie);
			
		}
		
		
		log.info(rememberId);
		
		
		
		response.sendRedirect("/");
		
	}
	
	

}
