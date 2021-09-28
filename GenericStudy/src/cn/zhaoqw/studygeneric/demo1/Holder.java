package cn.zhaoqw.studygeneric.demo1;

public class Holder<T> {
    private T t;

    public Holder(T t) {
        this.t = t;
    }

    T get() {
        return t;
    }

    public static void main(String[] args) {

    }

}
