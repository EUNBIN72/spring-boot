package com.winter.app.configs.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
// 로그아웃 처리 과정에서 추가 작업을 실행하기 위한 커스텀 핸들러
public class AddLogoutHandler implements LogoutHandler {
// LogoutHandler : Spring Security 가 제공하는 로그아웃 처리용 인터페이스

	@Override
	// HttpServletRequest request : 로그아웃 요청 정보
	// HttpServletResponse response : 응답 객체. (쿠키 삭제, 헤더 추가 가능)
	// Authentication authentication : 로그아웃하는 사용자의 인증 정보 (로그인 아이디, 권한 등)
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		log.info("logout handler");
		log.info("{}", authentication);

	}
	
	// 사용자가 /member/logout 같은 로그아웃 URL을 호출하면 Spring Security의 LogoutFilter -> SecurityContextLogoutHandler가 동작
	

}
