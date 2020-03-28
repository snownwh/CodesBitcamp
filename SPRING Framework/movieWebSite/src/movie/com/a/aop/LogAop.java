package movie.com.a.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LogAop {
	
	@Around("within(movie.com.a.controller.*)")
	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable{
		
		String signatureStr = joinpoint.getSignature().toShortString();
		try {
			
			System.out.println("loggerAOP:" + signatureStr + " 메소드가 실행되었습니다.");
			
			Object obj = joinpoint.proceed(); // Cat.info()를 호출. 지정한 함수를 호출했을 때 기능 실행
			return obj;
		} finally {}
	}
}
