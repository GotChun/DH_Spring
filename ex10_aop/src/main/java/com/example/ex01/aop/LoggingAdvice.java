package com.example.ex01.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Aspect
public class LoggingAdvice {

	//포인트컷 표현식
	//"execution(* com.example.ex01.domain.service.simpleServiecImpl.get1())"
	// execution : 메서드 실행
	// * 		 : 모든 리턴 타입
//	com.example.ex01.domain.service.simpleServiecImpl.get1() : 함수명
	
	@Before("execution(* com.example.ex01.domain.service.MemoServiceImpl.memoAddTx(..))")  // 파라미터 .. 은 파라미터가 어떤 것이든 상관없다는 뜻
	public void loggingBefore(JoinPoint joinPoint) {
		log.info("[AOP] BEFORE : " + joinPoint);
	}
	
	@After("execution(* com.example.ex01.domain.service.MemoServiceImpl.getMemos())")
	public void loggingAfter(JoinPoint joinPoint) {
		log.info("AOP BEFORE " + joinPoint);
		log.info("AFTER.." + joinPoint.getTarget());		//
		log.info("AFTER.." + joinPoint.getSignature());		//불러오는 경로명
		log.info("AFTER.." + joinPoint.getSignature().getName());	//함수명
	}
	
	@Around("execution(* com.example.ex01.domain.service.*.*(..))")	
	public Object loggingAround(ProceedingJoinPoint pip) throws Throwable {
		
		//이전시점
		log.info("AOP AROUND BEFORE");
		long startTime = System.currentTimeMillis();
		//MVC 흐름대로 진행
		Object isUpdate =(Object)pip.proceed();		
		//이후시점
		log.info("AOP AROUND AFTER");
		long endTime = System.currentTimeMillis();
		log.info("[AOP] TIME : " + (endTime-startTime)+ "ms");
		
		
		return isUpdate;
		
	}
	
	
	
	
	
	
	
}
