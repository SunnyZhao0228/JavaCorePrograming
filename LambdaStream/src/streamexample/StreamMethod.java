package streamexample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-10-20 16:47]
 */
public class StreamMethod {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("1","2","3","4","5","6","7","8");
        int sum = list.stream().mapToInt(s -> Integer.parseInt(s))
                .filter(n -> n % 2 == 0)
                .sum();
        System.out.println(sum);


    }

    /**
     * 所有名字首字母大写
     */
    public static  void example2() {
        List<String> list = Arrays.asList("lily","smith","jackon");
        list.stream().map(name -> name.substring(0,1).toUpperCase() + name.substring(1))
                .forEach(System.out::println);
    }



}
