/**
 * 
 */
package ga.newspbn.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author KEN
 *	Park Jong-hyun
 *	 2017. 12. 15.오후 4:10:02
 */
@Component
@Aspect
public class AopAdvice {
	private static final Logger logger = LoggerFactory.getLogger(AopAdvice.class);
	
//	@Before("execution(* ga.newspbn.service.ReplyService*.*(..))")
	public void startLog(JoinPoint jp) {
		logger.info("-----------------------------------------");
		logger.info("-----------------------------------------");
		logger.info(Arrays.toString(jp.getArgs()));
		//Object[] getArgs() : 전달되는 모든 파라미터들을 Object의 배열로 가져온다. 
		//[mno=null, targetid=user01, sender=user03, message=피빨기 시전, opendate=null, senddate=null]]
		logger.info("getKind() : "+jp.getKind());
		//getKind : Advice type : method-execution
		logger.info("getSinature() : "+jp.getSignature().toString());
		//getSignature : 실행하는 대상 객체의 메소드의 대한 정보		
		// void ga.newspbn.service.MessageService.addMessage(MessageVO)
		logger.info("getTarget() : " +jp.getTarget().toString());
		//getTarget : target 객체를 알아낼 때 사용
		//ga.newspbn.service.MessageServiceImpl@3557c8bc
		logger.info("getThis() : " + jp.getThis().toString());
		//Advice를 행하는 객체를 알아낼 때 사용
		//ga.newspbn.service.MessageServiceImpl@3557c8bc
	}
	
//	@Around("execution(* ga.newspbn.service.ReplyService*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable{
		long startTime = System.currentTimeMillis();
		logger.info(Arrays.toString(pjp.getArgs()));
		Object result = pjp.proceed();
		
		long endTime = System.currentTimeMillis();
		logger.info(pjp.getSignature().getName() + "(실행의 소요된 시간ms) : " + (endTime - startTime));
		logger.info("=======================================================");
		
		
		return result;
		
	}
	
//##################################정신사나우니까 어노테이션은 주석처리######################################
}
