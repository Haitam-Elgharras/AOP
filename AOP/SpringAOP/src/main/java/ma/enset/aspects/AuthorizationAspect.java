package ma.enset.aspects;

import ma.enset.service.SecurityContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@EnableAspectJAutoProxy
public class AuthorizationAspect {

    @Around(value = "@annotation(securedByAspect)",argNames = "pjp,securedByAspect")
    public Object secure(ProceedingJoinPoint pjp, SecuredByAspect securedByAspect) throws Throwable {
        // the roles specified in the annotation
        String[] roles = securedByAspect.roles();

        if(SecurityContext.isAuthenticated()){
            for (String role : roles) {
                if(SecurityContext.hasRole(role)){
                    return pjp.proceed();
                }
            }
            throw new RuntimeException("403 : Access Denied to " + pjp.getSignature());
        }
        throw new RuntimeException("You must be authenticated");

    }

}
