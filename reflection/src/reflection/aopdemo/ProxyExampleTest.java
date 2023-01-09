package reflection.aopdemo;

import java.lang.reflect.InvocationTargetException;

/**
 * @author zhaoqw
 * @date 2023/01/08
 */
public class ProxyExampleTest {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        IOrder proxy = Aspect.getProxy(Order.class, "reflection.aopdemo.TimeUseageAspect");
        proxy.pay();
        proxy.show();
    }
}
