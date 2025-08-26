package com.winter.app.configs.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
// 로그아웃이 정상적으로 완료된 직후 실행되는 후처리 핸들러
public class AddLogoutSuccessHandler implements LogoutSuccessHandler {
// LogoutSuccessHandler : 로그아웃이 완료된 후 실행되는 로직을 정의하는 인터페이스

	@Override
	// HttpServletRequest request : 로그아웃 요청 객체
	// HttpServletResponse response : 응답 객체 (리다이렉트, 메시지 전달 가능)
	// Authentication authentication : 로그아웃한 사용자의 인증 정보
		public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
				throws IOException, ServletException {

		log.info("logout Success");
		log.info("{}", authentication);
		
		// 로그아웃에 성공하면 메인페이지로 redirect
		response.sendRedirect("/");
		
		}
}
