package cn.zhaoqw.study.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-12-01 23:12]
 */
public class RandomStringGenerator<T> implements Iterable<T> {
    public final List<T> list;


    public RandomStringGenerator(List<T> list) {
        this.list = list;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public T next() {
                return list.get((int) (list.size() * Math.random()));
            }
        };
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("list","tree","aaavvv");
        RandomStringGenerator<String> strings = new RandomStringGenerator<>(list);
        Iterator<String> iterator = strings.iterator();
        for (int i = 0; i < 100; i++) {
            System.out.println(iterator.next());
        }
    }
}
