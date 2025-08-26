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
// AuthenticationSuccessHandler : 로그인 성공 시 실행할 로직을 구현하는 인터페이스

	// 로그인이 성공했을 때 Spring Security가 자동으로 호출
	// request : 로그인 요청 정보
	// response : 응답 객체(리다이렉트, 쿠키 설정 가능)
	// authentication : 로그인 성공한 사용자 정보(아이디, 권한 등)
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		// 서블릿을 한정적으로 사용
		//로그인 폼에서 "아이디 저장" 체크박스(name="rememberId") 값을 가져옴
		String rememberId = request.getParameter("rememberId");
		
		// 값이 "1"이면 체크된 상태, null이면 체크 해제
		if(rememberId != null && rememberId.equals("1")) {
			// 쿠키 객체 생성(Key/Value 형태)
			Cookie cookie = new Cookie("rememberId", authentication.getName());
			// 쿠키 유지 시간 설정(초 단위)
			cookie.setMaxAge(60 * 60); // 1시간 유지
			// cookie가 사용 가능한 URL
			cookie.setPath("/");
			// 응답에 쿠키 추가(브라우저에 전달)
			response.addCookie(cookie);
		} else {
			Cookie[] cookies =request.getCookies();
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("rememberId" )) {
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
					break;
				}
			}
		}
		
		
		log.info(rememberId);
		
		
		
		response.sendRedirect("/");
		
	}
	
	

}
