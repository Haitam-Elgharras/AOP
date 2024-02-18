**Custom XML-based IoC Container**

---

**Introduction:**

The Custom XML-based IoC (Inversion of Control) Container is a lightweight framework designed to provide dependency injection capabilities similar to Spring Framework's IoC container. It allows developers to define beans and their dependencies using XML configuration files and retrieve them programmatically based on their interfaces or classes.

**Features:**

1. **XML Configuration:** Developers can define beans and their dependencies in an XML configuration file following a predefined structure.

2. **Dependency Injection:** The container injects dependencies into beans during initialization, reducing the need for manual object creation and management.

3. **Reflection-Based Instantiation:** The framework uses reflection to instantiate beans and resolve dependencies dynamically, enabling loosely coupled application design.

**How to Use:**

1. **Define Beans:**
    - Create an XML configuration file (`beans.xml`) following the provided schema.
    - Define beans using the `<bean>` element, specifying their ID and class name.

2. **Specify Dependencies:**
    - Use constructor-arg or property elements within the `<bean>` definition to specify dependencies for each bean.

3. **Access Beans:**
    - Initialize the `ClassPathXmlCustomApplicationContext` by providing the path to the XML configuration file.
    - Use the `getBean()` method to retrieve beans by their interface or class.

**Example:**

Consider the following XML configuration (`beans.xml`):

```xml
<beans>
    <bean id="productDao" class="org.example.custom_xml_ioc.dao.ProductDaoImpl"/>
    <bean id="productService" class="org.example.custom_xml_ioc.service.ProductService">
        <constructor-arg ref="productDao"/>
    </bean>
</beans>
```

Initialize the container in your application:

```java
import org.example.custom_xml_ioc.customIOC.*;

public class MyApp {
    public static void main(String[] args) {
        CustomApplicationContext context = new ClassPathXmlCustomApplicationContext("beans.xml");
        IProductService productService = context.getBean(IProductService.class);
        // Use productService...
    }
}
```

**Limitations:**

1. **Interface-Based Injection Only:** The framework currently supports dependency injection based on interfaces only. Classes must implement the specified interfaces for injection to work.

2. **No Annotation Support:** Unlike modern frameworks like Spring, this custom IoC container relies solely on XML configuration and does not support annotations for bean management.

**Conclusion:**

The Custom XML-based IoC Container provides a simple yet effective way to manage dependencies in Java applications. The code for the framework resides in the `customIOC` package, while other packages are designated for testing and demonstration purposes.

For more information and usage examples, refer to the provided codebase.

---
