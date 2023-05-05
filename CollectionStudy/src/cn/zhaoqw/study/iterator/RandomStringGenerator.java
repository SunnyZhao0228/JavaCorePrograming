package cn.zhaoqw.study.iterator;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Iterable<T> 实现Iterable接口，实现iterator方法
 *
 *
 * @author zhaoqw
 * @date 2023/01/01
 */
public class RandomStringGenerator<T> implements Iterable<T> {
    private final List<T> list;

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
                return list.get((int)(list.size()*Math.random()));
            }
        };

    }

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("List", "Tree", "Array");
        RandomStringGenerator<String> randomStr = new RandomStringGenerator<>(strings);

//        for (String str : randomStr) {
//            System.out.println(str);
//        }

        Iterator<String> iterator = randomStr.iterator();
        for (int i = 0; i < 100; i++) {
            System.out.println(iterator.next());
        }
    }
}
