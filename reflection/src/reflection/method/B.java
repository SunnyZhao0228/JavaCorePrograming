package reflection.method;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 反射获取方法
 *
 * @author: zhaoqw
 * @date: 2022/3/4 11:11
 */
public class B {
    public void f1() {
        System.out.println("public method f1");
    }

    private String f2(String str) {
        System.out.println("private method f2");
        return "f2:" + str;
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        B obj = new B();
        Class aClass = obj.getClass();

        // 获取public修饰的方法
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            if ("f1".equals(method.getName())) {
                method.invoke(obj, null);
            }
        }
        // 获取所有方法
        Method[] declaredMethods = aClass.getDeclaredMethods();
        Arrays.stream(declaredMethods).forEach(method -> {
            if ("f1".equals(method.getName())) {
                try {
                    method.invoke(obj, null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

            if ("f2".equals(method.getName())) {
                try {
                    String result = (String) method.invoke(obj, "I'm f2");
                    System.out.println(result);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
