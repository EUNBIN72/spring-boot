package com.winter.app.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Spring MVC에서 정적 리소스(파일) 매핑을 설정하는 설정 클래스


@Configuration
public class FileConfig implements WebMvcConfigurer {
	
	@Value("${app.upload}")
	private String path;  // D:/upload/
	
	@Value("${app.url}")
	private String url;  // /files/
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler(url)  // 브라우저에서 접근할 가상 URL 패턴 지정
			.addResourceLocations("file:\\" + path);  // 실제 파일이 있는 로컬 경로 지정
	}
	
	
}
