package com.winter.app.transfer;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class NoticeServiceLog {
	
	@Around("excution(* com.winter.app.board.notice.noticeService.*(..))")
	public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
		
		Object obj = joinPoint.proceed();
		
		return obj;
				
		
	}

}
