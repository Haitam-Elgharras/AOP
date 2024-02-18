package org.example.presentation;


import org.example.customIOC.CustomApplicationContext;
import org.example.service.IProductService;
import org.example.customIOC.ClassPathXmlCustomApplicationContext;

public class PresentationSpringIOC {
     public static void main(String[] args) {

        CustomApplicationContext springContext = new ClassPathXmlCustomApplicationContext("config.xml");
        IProductService productService =  springContext.getBean(IProductService.class);
        System.out.println(productService.getAllProducts());
    }


}
