package streamexample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-10-20 16:07]
 */
public class StreamGenerator {

    /**
     * 1.Stream.of 创建stream
     */
    public static void generator() {
        String[] arr ={"Lily","Andy","Jackson","Smith"};
        Stream<String> stream = Stream.of(arr);
        stream.forEach(s-> System.out.println(s));
    }

    /**
     * 2.用集合自带的stream()方法创建
     */
    public static void generator2() {
        List<String> list  = new ArrayList<>();
        list.add("Lily");
        list.add("Andy");
        list.add("Jackson");
        list.add("Smith");
        Stream<String> stream = list.stream();
        stream.forEach(s-> System.out.println(s));
    }

    /**
     * 3.利用generate方法创建无限长度的数据流
     */
    public static void generator3() {
        Stream<Integer> generate = Stream.generate(() -> new Random().nextInt(100000));
        generate.limit(10).forEach(System.out::println);
    }

    /**
     * 4.基于迭代器创建流
     */
    public static void generator4() {
        Stream<Integer> iterate = Stream.iterate(1, n -> n + 1);
        iterate.forEach(System.out::println);
    }

    /**
     * 基于字符序列创建流
     *
     */
    public static void generator5() {
        String str = "abcde";
        IntStream stream = str.chars();
        stream.forEach(System.out::println);
    }


    public static void main(String[] args) {
        generator5();
    }
}
