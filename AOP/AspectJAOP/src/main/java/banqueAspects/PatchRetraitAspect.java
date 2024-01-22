package banqueAspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import service.Compte;
import service.ServiceBanqueImpl;

@Aspect
public class PatchRetraitAspect {
    @Pointcut("execution(* service.ServiceBanqueImpl.retirer(..))")
    public void retirerPointcut() {}

    @Before("retirerPointcut()")
    public void beforeRetrait(JoinPoint joinPoint){
        double montant = (double) joinPoint.getArgs()[1];
        long code = (long) joinPoint.getArgs()[0];
        // the solde of the object where the method is called
        // get target returns the object where the method is called
        ServiceBanqueImpl serviceBanque = (ServiceBanqueImpl) joinPoint.getTarget();
        Compte compte = serviceBanque.consulter(code);

        if (compte.getSolde() < montant) {
            throw new RuntimeException("Solde insuffisant");
        }
    }
// or we can use args
//    @Before("retirerPointcut() && args(code, montant)")
//    public void beforeRetrait(JoinPoint joinPoint, long code, double montant){
//        // the solde of the object where the method is called
//        // get target returns the object where the method is called
//        ServiceBanqueImpl serviceBanque = (ServiceBanqueImpl) joinPoint.getTarget();
//        Compte compte = serviceBanque.consulter(code);
//
//        if (compte.getSolde() < montant) {
//            throw new RuntimeException("Solde insuffisant");
//        }
//    }
}
