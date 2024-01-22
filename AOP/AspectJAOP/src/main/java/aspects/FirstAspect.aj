package aspects;

// at compile time, the AspectJ compiler will weave this aspect into the code
public aspect FirstAspect {
    // pointcut
    pointcut pc1() : execution(* test.Application.main(..));

    // code advice
//    before() : pc1() {
//        System.out.println("----------------------------------------");
//        System.out.println("FirstAspect: before using AspectJ syntax");
//        System.out.println("----------------------------------------");
//    }
//    after() : pc1() {
//        System.out.println("----------------------------------------");
//        System.out.println("FirstAspect: after using AspectJ syntax");
//        System.out.println("----------------------------------------");
//    }

    // code advice with around (inserted in bytecode)
    void around() : pc1() {
        System.out.println("----------------------------------------");
        System.out.println("FirstAspect: around before using AspectJ syntax");
        System.out.println("----------------------------------------");
        // call the method that was intercepted
        proceed();
        System.out.println("----------------------------------------");
        System.out.println("FirstAspect: around after using AspectJ syntax");
        System.out.println("----------------------------------------");
    }
}
