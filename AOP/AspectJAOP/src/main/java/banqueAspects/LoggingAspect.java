package banqueAspects;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;


@Aspect
public class LoggingAspect {
    // using a logger.info instead of logger.info
     private static final Logger logger = Logger.getLogger(LoggingAspect.class.getName());

     public LoggingAspect() throws IOException {
         // log in a file
         logger.addHandler(new FileHandler("log.xml"));

         // cancel writing in the console
         logger.setUseParentHandlers(false);
     }

    @Pointcut("execution(* service.ServiceBanqueImpl.*(..))")
    public void serviceBanquePointcut() {}


//    @Before("serviceBanquePointcut()")
//    public void logBefore(JoinPoint joinPoint) {
//        logger.info("--------------------------------------------------\n");
//        t1 = System.currentTimeMillis();
//        logger.info("log before " + joinPoint.getSignature().getName());
//    }
//
//    @After("serviceBanquePointcut()")
//    public void logAfter(JoinPoint joinPoint) {
//        t2 = System.currentTimeMillis();
//        logger.info("log after "+joinPoint.getSignature().getName());
//
//        logger.info("time taken by "+joinPoint.getSignature().getName()+" is "+(t2-t1)+" ms");
//        logger.info("--------------------------------------------------\n");
//    }

    @Around("serviceBanquePointcut()")
    public Object autour(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("--------------------------------------------------\n");
        logger.info("log around before "+proceedingJoinPoint.getSignature().getName());
        long t1 = System.currentTimeMillis();
        Object result= proceedingJoinPoint.proceed();
        long t2 = System.currentTimeMillis();
        logger.info("log around after "+proceedingJoinPoint.getSignature().getName());
        logger.info("time taken by "+proceedingJoinPoint.getSignature().getName()+" is "+(t2-t1)+" ms");
        logger.info("--------------------------------------------------\n");

        return result;
     }


}
