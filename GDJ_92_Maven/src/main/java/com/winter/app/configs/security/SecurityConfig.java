package com.winter.app.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.AntPathMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
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
					.requestMatchers("/notice/add", "/notice/update", "/notice/delete").hasRole("ADMIN")
					.requestMatchers("/products/add", "/products/update", "/products/delete").hasAnyRole("MANAGER", "ADMIN")
//					.requestMatchers("/member/detail", "/member/lgout", "/member/update", "/member/delete").access("hasRole('ROLE_MEMBER') or hasRole('ROLE_MANAGER')")
					.requestMatchers("/member/detail", "/member/logout", "/member/update", "/member/delete").authenticated()
					.anyRequest().permitAll()
					;
			})
			// form에 관련된 설정
			.formLogin(form->{
				form
					// 로그인관련
					.loginPage("/member/login")
//					.usernameParameter("username")
//					.passwordParameter("password")
					.defaultSuccessUrl("/")
					.failureUrl("/member/login")
					;
			})
			
			// logout 설정
			.logout((logout)-> {
				logout
					.logoutUrl("/member/logout")
//					.logoutRequestMatcher(new AntPathMatcher("/member/logout"))
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID")
					.logoutSuccessUrl("/");
					
			})
			;
		
		return httpSecurity.build();
	}

}
