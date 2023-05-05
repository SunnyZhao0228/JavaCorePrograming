package com.annotation.marker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zhaoqw
 * @date 2023/04/26
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        int passed = 0, failed=0;
        Class<?> aClass = Class.forName("com.annotation.marker.Foo");
        for (Method method : aClass.getMethods()) {
            if (method.isAnnotationPresent(Test.class)){
                try {
                    Object invoke = method.invoke(null);
                    passed ++;
                } catch (IllegalAccessException e) {
                    failed++;
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    failed++;
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
