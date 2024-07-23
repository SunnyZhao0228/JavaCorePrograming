package reflection.fatherclass;

/**
 * 反射获取父类，父接口
 *
 * @author: zhaoqw
 * @date: 2022/3/4 11:33
 */
public class Son extends Father implements Cloneable, Comparable {
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public static void main(String[] args) {
        Son son = new Son();
        Class aClass = son.getClass();
        // 获取父类
        Class superclass = aClass.getSuperclass();
        System.out.println(superclass.getName());

        // 获取接口
        Class[] interfaces = aClass.getInterfaces();
        for (Class anInterface : interfaces) {
            System.out.println(anInterface.getName());
        }
    }
}
