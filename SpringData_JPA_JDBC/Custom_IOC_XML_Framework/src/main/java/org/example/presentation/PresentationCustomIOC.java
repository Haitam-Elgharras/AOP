package org.example.presentation;


import org.example.customIOC.CustomApplicationContext;
import org.example.service.IProductService;
import org.example.customIOC.CustomClassPathXmlApplicationContext;

public class PresentationCustomIOC {
     public static void main(String[] args) {

        CustomApplicationContext springContext = new CustomClassPathXmlApplicationContext("config.xml");
        IProductService productService =  springContext.getBean(IProductService.class);
        System.out.println(productService.getAllProducts());
    }


}
