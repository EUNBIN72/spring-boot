package com.winter.app.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.Filter;


// filterconfig는 url 지정과 순서 지정이 모두 가능함
@Configuration
public class FilterConfig implements WebMvcConfigurer {
	
	@Bean
	FilterRegistrationBean<Filter> filterRegistrationBean() {
		// 객체 생성
		FilterRegistrationBean<Filter> fr = new FilterRegistrationBean<>();
		
		fr.setFilter(new TestFilter());
		fr.addUrlPatterns("");
		fr.setOrder(2);
		return fr;
	}
	
	@Bean
	FilterRegistrationBean<Filter> adminCheckFilter() {
		// 객체 생성
		FilterRegistrationBean<Filter> fr = new FilterRegistrationBean<>();
		
		fr.setFilter(new AdminCheckFilter());
		fr.addUrlPatterns("/notice/add", "/notice/update", "/notice/delete");
		fr.setOrder(1);
		return fr;
	}

}
