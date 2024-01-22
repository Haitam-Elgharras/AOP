package banqueAspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Scanner;

@Aspect
public class SecurityAspect {
    @Pointcut("execution(* testBanque.Application.start(..))")
    public void startPointcut() {}

    @Around("startPointcut()")
    public void aroundStart(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the username");
        String username = scanner.nextLine();
        System.out.println("Enter the password");
        String password = scanner.nextLine();
        if (!username.equals("admin") || !password.equals("admin")) {
            System.out.println("Wrong username or password");
            System.exit(0);
        }
        proceedingJoinPoint.proceed();

    }

}
