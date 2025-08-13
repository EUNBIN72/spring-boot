package com.winter.app.filters;

import java.io.IOException;

import com.winter.app.members.MemberVO;
import com.winter.app.members.RoleVO;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class AdminCheckFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// user가 Admin 권한의 유무
		HttpServletRequest req = (HttpServletRequest)request;
		MemberVO memberVO = (MemberVO)req.getSession().getAttribute("member");
		boolean flag = false;
		
		for(RoleVO roleVO : memberVO.getRoleVOs()) {
			if(roleVO.getRoleName().equals("ROLE_ADMIN")) {
				flag = !flag;
				break;
			}
		}
		
		if(flag) {
			chain.doFilter(request, response);
		}else {
			req.setAttribute("msg", "권한이 없습니다.");
			req.setAttribute("url", "/");
			req.getRequestDispatcher("/WEB-INF/views/commons/result.jsp").forward(request, response);
		}
		
		
	}

}
