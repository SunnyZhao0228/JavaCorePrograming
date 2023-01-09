package reflection.aopdemo;

import reflection.aopdemo.basic.Try;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhaoqw
 * @date 2023/01/08
 */
public interface Aspect {
    void before();
    void after();

    static <T> T getProxy(Class<T> tClass, String... aspects) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<Try<Aspect>> aspectInstans = Arrays.stream(aspects).map(name -> Try.ofFailable(() -> {
                    Class<?> aClass = Class.forName(name);
                    return (Aspect) aClass.getConstructor().newInstance();
                })).filter(aspect -> aspect.isSuccess())
                .collect(Collectors.toList());
        T instance = tClass.getConstructor().newInstance();
        return (T) Proxy.newProxyInstance(
                tClass.getClassLoader(),
                tClass.getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        for (Try<Aspect> aspectInstan : aspectInstans) {
                            aspectInstan.get().before();
                        }
                        Object result = method.invoke(instance);
                        for (Try<Aspect> aspectInstan : aspectInstans) {
                            aspectInstan.get().after();
                        }
                        return result;
                    }
                }
        );
    }
}
