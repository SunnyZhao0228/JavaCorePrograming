package StreamApiTest;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
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
        Integer reduce = Stream.of(1, 2, 3, 4, 5, 6)
                .map((item) -> item + 1).reduce(0, Math::max);
        System.out.println(reduce);


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

        start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool(2);
        ForkJoinTask<Optional<Integer>> submit = forkJoinPool.submit(() -> collect.parallelStream().max((x, y) -> x.compareTo(y)));
        try {
            System.out.println(submit.get().get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);

    }
}
