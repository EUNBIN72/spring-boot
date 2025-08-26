package com.winter.app.configs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.util.AntPathMatcher;

import com.winter.app.members.MemberService;

// 이 클래스가 Spring Security 설정 클래스임을 의미

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AddLogoutSuccessHandler addLogoutSuccessHandler;
	
	@Autowired
	private LoginSuccessHandler loginSuccessHandler;
	
	@Autowired
	private LoginFailHandler loginFailHandler;
	
	@Autowired
	private AddLogoutHandler addLogoutHandler;
	
	@Autowired
	private LogoutSuccessHandler logoutSuccessHandler;
	
	@Autowired
	private MemberService memberService;

    SecurityConfig(AddLogoutSuccessHandler addLogoutSuccessHandler) {
        this.addLogoutSuccessHandler = addLogoutSuccessHandler;
    }
	
	// 정적자원들을 Security에서 제외
    // 정적 리소스(css, js, files)는 보안 필터를 거치지 않고 접근 가능하게 설정
    // 로그인 여부와 관계없이 누구나 접근 가능
	@Bean
	WebSecurityCustomizer customizer() {
		
		// web => WebSecurity
		return web -> {
			web
				.ignoring()
					.requestMatchers("/css/**")
					.requestMatchers("/js/**", "/vender/**" )
					.requestMatchers("/files/**");
		};
	}
	
	
	// 인증과 권한 설정
	// SecurityFilterChain 빈을 등록해서, 인증/인가 규칙, 로그인/로그아웃 동작, 세션 관리 등을 직접 커스터마이징
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.cors(cors-> cors.disable())  // CORS 보안 비활성화
			.csrf(csrf -> csrf.disable())  // CSRF 토큰 비활성화
			
			// 권한(Authorization)에 관련된 설정
			.authorizeHttpRequests(auth -> {
				auth
					// 어떤 URL이 왔을 때 어떻게 제어할 것인가
					.requestMatchers("/notice/add", "/notice/update", "/notice/delete").hasRole("ADMIN")  // ADMIN 권한 필요
					.requestMatchers("/products/add", "/products/update", "/products/delete").hasAnyRole("MANAGER", "ADMIN")  // MANAGER 또는 ADMIN 권한 필요
//					.requestMatchers("/member/detail", "/member/lgout", "/member/update", "/member/delete").access("hasRole('ROLE_MEMBER') or hasRole('ROLE_MANAGER')")
					.requestMatchers("/member/detail", "/member/logout", "/member/update", "/member/delete").authenticated()  // 로그인 사용자만 접근 가능
					.anyRequest().permitAll()  // 그 외 모든 요청은 모두 허용
					;
			})
			// form에 관련된 설정
			// 개발자가 로그인 검증을 하지 않음, Security Filter에서 검증
			.formLogin(form->{
				form
					// 로그인관련
					.loginPage("/member/login")
//					.usernameParameter("username")
//					.passwordParameter("password")
//					.defaultSuccessUrl("/")  // 인증에 성공한 후 요청할 URL(개발자가 생성) / redirect
//					.successForwardUrl(null) // foward
					.successHandler(loginSuccessHandler)  // 로그인 성공 시 동작
//					.failureUrl("/member/login") // 인증에 실패한 후 요청할 URL(개발자가 생성)
					.failureHandler(loginFailHandler)  // 로그인 실패 시 동작
					;
			})
			
			// logout 설정
			// (세션, 쿠키까지 제거)
			.logout((logout)-> {
				logout
					.logoutUrl("/member/logout") // 로그아웃 URL 주소 변경 가능(Controller 처리 X)
					.addLogoutHandler(addLogoutHandler)  // 로그아웃 시 추가 동작
					.logoutSuccessHandler(addLogoutSuccessHandler)  // 로그아웃 성공 후 동작
//					.logoutSuccessHandler(null)
//					.logoutRequestMatcher(new AntPathMatcher("/member/logout"))
					.invalidateHttpSession(true)  // 세션 무효화
					.deleteCookies("JSESSIONID")  // 쿠키 삭제
//					.logoutSuccessUrl("/")
					;
					
			})
			
			// 자동 로그인
			// 사용자가 remember-me 체크박스를 선택하면 자동 로그인 유지
			.rememberMe((remember)-> {
				remember
					.rememberMeParameter("remember-me")  // 체크박스 name
					.tokenValiditySeconds(60)  // 초단위
					.key("rememberkey")  // 토큰 암호화 키
					.userDetailsService(memberService)  // 사용자 정보 조회
					.authenticationSuccessHandler(loginSuccessHandler)
					.useSecureCookie(true)
					;
			})
			
			
			// 이중 사용 방지
			.sessionManagement((s)->{
				s
					.invalidSessionUrl("/member/login")
					.maximumSessions(1)  // -1 : 무한대
					.maxSessionsPreventsLogin(true)  // false : 이전 사용자 X // true : 현재 접속 사용자 X
					.expiredUrl("/")  // 세션이 만료되면 /(메인 페이지)로 이동
					;
			})
			;
		
		return httpSecurity.build();
	}

}
