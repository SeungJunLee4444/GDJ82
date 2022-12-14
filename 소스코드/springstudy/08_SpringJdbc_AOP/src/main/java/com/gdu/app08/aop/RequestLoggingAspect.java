package com.gdu.app08.aop;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Component  
// # aop를 사용하려면 아래 두개의 어노테이션이 필요하다
@Aspect   
@EnableAspectJAutoProxy
public class RequestLoggingAspect {
	
	
	// # 로거
	private static final Logger LOG = LoggerFactory.getLogger(RequestLoggingAspect.class);

	
	// # 포인트컷 설정 : 컨트롤러의 모든 메서드를 포인트컷으로 지정
	@Pointcut("within(com.gdu.app08.controller..*)")  // 컨트롤러의 모든 메소드를 포인트컷으로 지정하겠다. 모든 메서드에서 어드바이스(콘솔)이 출력된다
	                                                
	public void setPointCut() { }  // 오직 포인트컷 대상을 결정하기 위한 메소드(이름 : 아무거나, 본문 : 없음)
	
	
	// # 어드바이스 설정
	// - 실행시점에 따른 어드바이스 :  @Before, @After, @AfterReturning, @AfterThrowing, @Around
	@Around("com.gdu.app08.aop.RequestLoggingAspect.setPointCut()")  
	// - 내용물 : 위에 포인트 컷 대상
	
	public Object executeLogging(ProceedingJoinPoint joinPoint) throws Throwable {  
		// * @Around는 반드시 ProceedingJoinPoint joinPoint 선언해야 함
		// * @Around : 메서드 실행 전후의 로그를 콘솔에 보여준다
		
		
		// HttpServletRequest를 사용하는 방법
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		
		// HttpServletRequest를 Map으로 바꾸기
		// 파라미터는 Map의 key가 되고, 값은 Map의 value가 된다.
		Map<String, String[]> map = request.getParameterMap();
		String params = "";
		if(map.isEmpty()) {
			params += "[No Parameter]";
		} else {
			for(Map.Entry<String, String[]> entry : map.entrySet()) {				
				params += "[" + entry.getKey() + "=" + String.format("%s", (Object[])entry.getValue()) + "]";
			}
		}
		
		// 어드바이스는 proceed() 메소드 실행 결과를 반환
		Object result = null;
		try {
			result = joinPoint.proceed(joinPoint.getArgs());
		} catch (Exception e) {
			throw e;
		} finally {
			// 무조건 실행되는 영역(여기서 로그를 찍는다.)
			// 치환문자 : {}
			LOG.info("{} {} {} > {}", request.getMethod(), request.getRequestURI(), params, request.getRemoteHost());
		}
		
		return result;
		
	}
	
}