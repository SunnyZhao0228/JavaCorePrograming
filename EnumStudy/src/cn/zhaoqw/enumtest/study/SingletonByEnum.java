package cn.zhaoqw.enumtest.study;

public class SingletonByEnum {
    public static void main(String[] args) {
        System.out.println(Singleton.getInstance() == Singleton.getInstance());
    }
}


enum Singleton{
    INSTANCE;
    public static Singleton getInstance() {
        return INSTANCE;
    }

}
