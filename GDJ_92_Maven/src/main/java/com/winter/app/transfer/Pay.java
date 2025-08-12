package com.winter.app.transfer;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Pay {
	
	
	@Around("execution (* com.winter.app.transfer.Transfers.take*())")
	public Object cardCheck(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("탑승 전 카드 체크");
		Object obj = joinPoint.proceed();
		System.out.println("하차 시 카드 체크");
		
		return obj;
		
	}
}
