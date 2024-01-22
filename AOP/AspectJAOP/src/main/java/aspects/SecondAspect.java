package aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class SecondAspect {
    // * test.*.*(..) means any return type, test package, any class, any method, any number of arguments
    @Pointcut("execution(* test.*.main(..))")
    public void mainPointcut() {}

//    @Before("mainPointcut()")
//    public void beforeMain() {
//        System.out.println("----------------------------------------");
//        System.out.println("Second aspect before: Class syntax");
//        System.out.println("----------------------------------------");
//
//    }
//
//    @After("mainPointcut()")
//    public void afterMain() {
//        System.out.println("----------------------------------------");
//        System.out.println("Second aspect after: Class syntax");
//        System.out.println("----------------------------------------");
//    }

    @Around("mainPointcut()")
    public void aroundMain(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("----------------------------------------");
        System.out.println("Second aspect around: Class syntax");
        System.out.println("----------------------------------------");
        // the actual joinPoint that is being intercepted
        joinPoint.proceed();
        System.out.println("----------------------------------------");
        System.out.println("Second aspect around: Class syntax");
        System.out.println("----------------------------------------");

    }

}
