package banqueAspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {
    Long t1, t2;
    @Pointcut("execution(* service.ServiceBanqueImpl.*(..))")
    public void serviceBanquePointcut() {}


    @Before("serviceBanquePointcut()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("--------------------------------------------------\n");
        t1 = System.currentTimeMillis();
        System.out.println("log before " + joinPoint.getSignature().getName());
    }

    @After("serviceBanquePointcut()")
    public void logAfter(JoinPoint joinPoint) {
        t2 = System.currentTimeMillis();
        System.out.println("log after "+joinPoint.getSignature().getName());

        System.out.println("time taken by "+joinPoint.getSignature().getName()+" is "+(t2-t1)+" ms");
        System.out.println("--------------------------------------------------\n");
    }


}
