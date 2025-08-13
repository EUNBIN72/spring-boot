package com.winter.app.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// 먼저 main 메서드가 있는 클래스 선언부에 @ServletComponentScan 선언 해야 함
// @WebFilter 사용시 @Component 는 삭제 해야 함
// @Component // 객체를 만드는 어노테이션
// @WebFilter(urlPatterns = {"/notice/*", "/qna/*"})
// @Component// url 지정은 안되지만 @order로 순서 지정은 가능함
// @Order(1)

//@WebFilter
//@Component
//@Order(1)
public class TestFilter implements Filter {
       
	// 클래스(대문자로 시작) , 메소드(호출) 
    public TestFilter() {
        super();
    }

    // 소멸
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		System.out.println("Test Filter IN");
		chain.doFilter(request, response);
		System.out.println("Test Filter OUT");
	}

	// 필터가 생성될 때
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
