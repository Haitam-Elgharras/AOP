package org.example.customIOC;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CustomClassPathXmlApplicationContext implements CustomApplicationContext {
    private final String configXml;
    private final Map<String, Object> beanMap;

    public CustomClassPathXmlApplicationContext(String configXml) {
        this.configXml = configXml;
        this.beanMap = new HashMap<>();
        initializeBeans();
    }

    private void initializeBeans() {
        try {
            File file = new File("src/main/resources/" + configXml);
            JAXBContext jaxbContext = JAXBContext.newInstance(BeanConfig.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            BeanConfig beanConfig = (BeanConfig) unmarshaller.unmarshal(file);

            for (BeanDefinition beanDefinition : beanConfig.getBeanDefinitions()) {
                Class<?> beanClass = Class.forName(beanDefinition.getClassName());
                Object beanInstance = beanClass.getDeclaredConstructor().newInstance();
                beanMap.put(beanDefinition.getId(), beanInstance);
            }
        } catch (JAXBException | ReflectiveOperationException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize beans from XML configuration.");
        }
    }

    @Override
    public <T> T getBean(Class<T> beanClass) {
        // Look for the bean instance based on its class name
        for (Object beanInstance : beanMap.values()) {
            if (beanClass.isInstance(beanInstance)) {
                // Cast the bean to the requested type and return
                return beanClass.cast(beanInstance);
            }
        }
        // If the bean is not found, return null or throw an exception as needed
        return null;
    }
}
