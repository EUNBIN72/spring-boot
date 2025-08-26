package com.winter.app.configs.security;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginFailHandler implements AuthenticationFailureHandler {
// AuthenticationFailureHandler : 로그인 실패 시 동작을 정의하는 인터페이스

	
	// request : 로그인 요청 객체
	// response : 응답 객체 (리다이렉트, 메시지 전달 가능)
	// exception : 어떤 이유로 로그인 실패했는지 담고 있는 예외 객체
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		// 로그인이 실패하면 Security에서 여러 종류의 AuthenticationException을 던짐
		log.info("{}", exception);
		
		// ----MemberVO에서 
		// 주요 예외 타입과 의미
		// InternalAuthenticationServiceException 		: 아이디가 존재하지 안흠
		// BadCredentialsException 				  		: 비밀번호가 틀림
		// DisabledException					    	: 계정이 비활성화됨
		// AccountExpiredException				  		: 계정 유효 기간 만료
		// LockedException						  		: 사용자 계정이 잠김
		// CredentialsExpiredException			  		: 비밀번호 유효 기간 만료
		// AuthenticationCredentialsNotFoundException   : 인증 정보 자체가 없음
		
		// 기본 메세지
		String message = "관리자에게 문의하세요.";
		
		// Password가 틀린 경우
		if(exception instanceof BadCredentialsException) {
			message="비밀번호가 틀렸습니다.";
		} 
		if(exception instanceof DisabledException) {
			message="유효하지 않은 사용자입니다.";
		}
		if(exception instanceof AccountExpiredException) {
			message="사용자 계정의 유효 기간이 만료 되었습니다.";
		}
		if(exception instanceof LockedException) {
			message="사용자 계정이 잠겨 있습니다.";
		}
		if(exception instanceof CredentialsExpiredException) {
			message="자격 증명 유효 기간이 만료되었습니다.";
		}
		if(exception instanceof InternalAuthenticationServiceException) {
			message="아이디가 틀렸습니다.";
		}
		// 다른 모른 오류, 기타 등등...
		if(exception instanceof AuthenticationCredentialsNotFoundException) {
			message="관리자에게 문의하세요.";
		}
		
		
		// 메세지를 인코딩 처리
		message = URLEncoder.encode(message, "UTF-8");
		
		response.sendRedirect("./login?failMessage="+message);
		
	}

}
