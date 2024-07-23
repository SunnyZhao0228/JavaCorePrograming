package reflection.methodinovke;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zhaoqw
 * @date 2024/7/16
 */
public class InvokeTest {
    public static void main(String[] args) {
        try {
            UserA userA = new UserA();
            Class aClass = userA.getClass();
            Method method = aClass.getMethod("setUsername", String.class);
            method.invoke(userA, "zhaoqw");
            System.out.println(userA.toString());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
