package ma.enset.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
@EnableAspectJAutoProxy
public class LogAspect {
    // ..* means any depth of subpackages
//    @Before("execution(* ma.enset.service..*(..))")
//    public void log(){
//        logger.info("*********************");
//        logger.info("Log before method");
//        logger.info("*********************");
//    }
    Logger logger = Logger.getLogger(LogAspect.class.getName());

    @Around("@annotation(Log)")
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("*********************");
        logger.info("Log before method " + pjp.getSignature());
        logger.info("*********************");
        long t1 = System.currentTimeMillis();
        Object result = pjp.proceed();
        long t2 = System.currentTimeMillis();
        logger.info("*********************");
        logger.info("Log after method");
        logger.info("*********************");
        logger.info("Method "+pjp.getSignature().getName()+" took "+(t2-t1)+" ms");
        return result;
    }

}
