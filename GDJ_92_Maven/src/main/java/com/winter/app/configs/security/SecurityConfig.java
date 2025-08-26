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

    SecurityConfig(AddLogoutSuccessHandler addLogoutSuccessHandler) {
        this.addLogoutSuccessHandler = addLogoutSuccessHandler;
    }
	
	// 정적자원들을 Security에서 제외
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
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.cors(cors-> cors.disable())
			.csrf(csrf -> csrf.disable())
			
			// 권한에 관련된 설정
			.authorizeHttpRequests(auth -> {
				auth
					// 어떤 URL이 왔을 때 어떻게 제어할 것인가
					.requestMatchers("/notice/add", "/notice/update", "/notice/delete").hasRole("ADMIN")  // ROLE
					.requestMatchers("/products/add", "/products/update", "/products/delete").hasAnyRole("MANAGER", "ADMIN")
//					.requestMatchers("/member/detail", "/member/lgout", "/member/update", "/member/delete").access("hasRole('ROLE_MEMBER') or hasRole('ROLE_MANAGER')")
					.requestMatchers("/member/detail", "/member/logout", "/member/update", "/member/delete").authenticated()
					.anyRequest().permitAll()
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
					.successHandler(loginSuccessHandler)
//					.failureUrl("/member/login") // 인증에 실패한 후 요청할 URL(개발자가 생성)
					.failureHandler(loginFailHandler)
					;
			})
			
			// logout 설정
			.logout((logout)-> {
				logout
					.logoutUrl("/member/logout") // 로그아웃 URL 주소 변경 가능(Controller 처리 X)
					.addLogoutHandler(addLogoutHandler)
					.logoutSuccessHandler(addLogoutSuccessHandler)
//					.logoutSuccessHandler(null)
//					.logoutRequestMatcher(new AntPathMatcher("/member/logout"))
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID")
//					.logoutSuccessUrl("/")
					;
					
			})
			;
		
		return httpSecurity.build();
	}

}
