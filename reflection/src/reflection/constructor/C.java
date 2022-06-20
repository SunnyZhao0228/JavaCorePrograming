package reflection.constructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 反射获取构造器
 * @author: zhaoqw
 * @date: 2022/3/4 11:24
 */
public class C {
    private int num;
    public C() {
        this.num = 100;
    }
    public C(int num) {
        this.num = num;
    }

    public void printNum() {
        System.out.println(this.num);
    }

    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        C obj = new C();
        Class aClass = obj.getClass();

        Constructor[] constructors = aClass.getConstructors();
        for (Constructor constructor : constructors) {
            if (constructor.getParameterCount() > 0) {
                // 有参构造函数
                C c =(C) constructor.newInstance(99999);
                c.printNum();
            }else {
                // 无参构造函数
                C c =(C) constructor.newInstance();
                c.printNum();
            }
        }
    }
}
