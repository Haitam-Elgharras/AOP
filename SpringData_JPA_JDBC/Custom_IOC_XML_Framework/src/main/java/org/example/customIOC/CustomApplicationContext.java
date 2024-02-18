package org.example.customIOC;

public interface CustomApplicationContext {
    <T> T getBean(Class<T> aClass);
}
