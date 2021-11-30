package lambdaexample;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-10-20 15:06]
 */
public class FunctionSample {
    public static void main(String[] args) {
        Function<Integer,String> randomStringFunction = l -> {
            String chars ="abcdefghijklmnopqrstuvwxyz";
            StringBuffer stringBuffer = new StringBuffer();
            Random random = new Random();
            for (Integer i = 0; i < l; i++) {
                int position = random.nextInt(chars.length());
                char c = chars.charAt(position);
                stringBuffer.append(c);
            }

            return stringBuffer.toString();
        };

        String apply = randomStringFunction.apply(10);
        System.out.println(apply);

        List<Integer> integers = Arrays.asList(12, 2, 34, 5, 6, 7, 67, 88);
        Integer integer1 = get(list -> {
            Integer max = 0;
            for (Integer integer : list) {
                if (integer > max) max = integer;
            }
            return max;
        }, integers);
        System.out.println(integer1);
    }



    public static Integer get(Function<List<Integer>,Integer> function,List<Integer> list) {
        Integer apply = function.apply(list);
        return apply;
    }
}
