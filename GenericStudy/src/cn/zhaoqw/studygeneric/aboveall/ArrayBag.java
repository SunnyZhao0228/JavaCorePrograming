package cn.zhaoqw.studygeneric.aboveall;

/**
 *
 * @author Administrator
 *
 * @param <T>
 *   	定义数组泛型
 *   T[] content=new T[10]; 非法操作
 */

public class ArrayBag<T>{
    //private T[] content=new T[10]; //编译出错，不能用泛型来创建数组实例
//    private T[] content = (T[]) (new Object[10]);

    private T[] content;

    public ArrayBag(T[] content) {
        this.content = content;
    }

    public T[] get() {
        return this.content;
    }

    public void set(T[] content) {
        this.content = content;
    }

    public static void main(String[] args){

        String[] content={"book1","book2","book3"};
        ArrayBag<String> bag=new ArrayBag<String>(content);   //数组作为构造器参数

        for(String c:bag.get())
            System.out.println(c);
    }
}
