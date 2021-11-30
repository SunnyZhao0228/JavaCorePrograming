package lambdaexample;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-10-20 10:15]
 */
public class PredicateSample {
    public static void main(String[] args) {
        Predicate<Integer> predicate = n -> n > 4;
        boolean result = predicate.test(10);
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        for (Integer integer : integers) {
            if (integer % 2 == 0 ) {
                System.out.println(integer);
            }
        }

        filter(integers,n-> (n%2)==0); //过滤偶数
        filter(integers,n -> (n%2) == 1);//过滤奇数

        List<String> strings = Arrays.asList("java", "python", "php", "HTML");
        filterString(strings,s -> s.contains("a"));
    }

    public static void filter(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer integer : list) {
            if (predicate.test(integer)) {
                System.out.println(integer + "");
            }
        }
    }

    public static void filterString(List<String> list, Predicate<String> predicate) {
        for (String s : list) {
            if (predicate.test(s)) {
                System.out.println(s);
            }
        }
    }
}
