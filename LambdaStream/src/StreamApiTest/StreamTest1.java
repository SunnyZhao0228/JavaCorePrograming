package StreamApiTest;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-12-02 9:14]
 */
public class StreamTest1 {
    public static void main(String[] args) {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6)
                .map((item) -> item + 1);
        integerStream.forEach(System.out::println);


        Random random = new Random();
        List<Integer> collect = IntStream.range(1, 10_000_000)
                .map(x -> random.nextInt(10_000_000))
                .boxed()
                .collect(Collectors.toList());
        long start = System.currentTimeMillis();
        System.out.println(collect.stream().max((x,y) -> x.compareTo(y)));
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        start = System.currentTimeMillis();
        System.out.println(collect.parallelStream().max((x,y) -> x.compareTo(y)));
        end = System.currentTimeMillis();
        System.out.println(end - start);

    }
}
