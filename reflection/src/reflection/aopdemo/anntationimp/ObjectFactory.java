package reflection.aopdemo.anntationimp;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedList;

/**
 * @author zhaoqw
 * @date 2023/01/09
 */
public class ObjectFactory {
    public static <T> T newInstance(Class<T> tClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 获取注解
        Annotation[] annotations = tClass.getAnnotations();
        LinkedList<IAspect> aspects = new LinkedList<>();
        for (Annotation annotation : annotations) {
            if (annotation instanceof Aspect) {
                Class type = ((Aspect) annotation).type();
                IAspect aspect = (IAspect) (type.getConstructor().newInstance());
                aspects.push(aspect);
            }
        }
        T instance = tClass.getConstructor().newInstance();
        return (T) Proxy.newProxyInstance(
                tClass.getClassLoader(),
                tClass.getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        aspects.forEach(aspect -> aspect.before());
                        Object result = method.invoke(instance);
                        aspects.forEach(aspect -> aspect.after());
                        return result;
                    }
                }
        );
    }
}
