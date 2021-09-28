package cn.zhaoqw.studygeneric.aboveall;

/**
 *
 * @author Administrator
 *
 * @param <T>
 *
 * 		泛型类，定义一个参数或者多个参数的泛型类。参数的名称一般用T，E，K，V等来表示
 * 		泛型接口，Interface Map<K,V>
 * 			Interface Collection<E>

 */

public class Bag<T>{
    private T content;

    public Bag(T content) {
        this.content = content;
    }

    public T get() {
        return this.content;
    }

    public void set(T content) {
        this.content = content;
    }

    public static void main(String[] args){
        Bag<String> bag=new Bag<>("mybook");

        Object content= bag.get();
    }
}
