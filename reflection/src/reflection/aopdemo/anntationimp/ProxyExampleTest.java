package reflection.aopdemo.anntationimp;

import java.lang.reflect.InvocationTargetException;

/**
 * @author zhaoqw
 * @date 2023/01/08
 */
public class ProxyExampleTest {
    public static void main(String[] args) throws InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {

        IOrder order = ObjectFactory.newInstance(Order.class);
        order.pay();
        order.show();
    }
}
