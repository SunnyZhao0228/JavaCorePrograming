package cn.zhaoqw.studygeneric.extendsdemo;


/**
 *  泛型参数 T extends 类型，extends 约束了泛型的上界
 *  表示T 必须是制定的类型，或者其子类
 *  类型也可以是接口，表示T要实现这个接口
 */
public class LimitBag<T extends Number> {
    private T content;

    public LimitBag(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public static void main(String[] args) {
        LimitBag<Number> bag1 = new LimitBag<>(5);
        LimitBag<Integer> bag2 = new LimitBag<>(5);
        System.out.println(bag1);
    }
}
