package ma.enset;

import ma.enset.service.IService;
import ma.enset.service.SecurityContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

// without specifying the basePackages, Spring will scan the current package and its subpackages
// which is ma.enset
@ComponentScan(basePackages = {"ma.enset"})
public class Application {
    // TODO: try to implement a encryption/decryption aspect for a User entity
    public static void main(String[] args) {
        SecurityContext.authenticate("admin", "1234", new String[]{"ADMIN", "USER"});

        ApplicationContext context =
                new AnnotationConfigApplicationContext(Application.class);

        IService service = context.getBean(IService.class);

        System.out.println("*********************");
        System.out.println(service.getClass().getName());
        System.out.println("*********************");

        service.process();
        System.out.println(service.compute());

    }
}
