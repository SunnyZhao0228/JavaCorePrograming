package cn.zhaoqw.studygeneric.demo3;


/**
 * 泛型类:
 * 泛型接口：Interface Map<K,V>
 *          interface Collection<E></>
 * @param <T>
 */
public class OldBag2<T> {
    private T content;

    public OldBag2(T content) {
        this.content = content;
    }

    public T get() {
        return this.content;
    }

    public void set(T content) {
        this.content = content;
    }

    public static void main(String[] args){
        OldBag2<String> bag=new OldBag2("mybook");
        String content=bag.get(); //运行时抛出ClassCastException
        System.out.println(content);
    }
}
