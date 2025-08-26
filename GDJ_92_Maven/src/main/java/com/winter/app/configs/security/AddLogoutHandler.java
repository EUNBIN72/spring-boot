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
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		log.info("logout handler");
		log.info("{}", authentication);

	}

}
