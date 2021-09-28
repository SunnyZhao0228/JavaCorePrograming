package cn.zhaoqw.study.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-09-15 10:46]
 */
public class ListTest0915 {
    public static void main(String[] args) {
        List<Double> list = new ArrayList<>();
        Random random = new Random(47);

        for (int i = 0; i < 10; i++) {
            double v = random.nextDouble()*10;
            list.add(v);
        }
        System.out.println(list);
        Double aDouble1 = list.get(4);
        System.out.println(aDouble1);

        System.out.println("======================");
        Double value1 = list.get(random.nextInt(10));
        Double value2 = list.get(random.nextInt(10));
        int index1 = list.indexOf(value1);
        int index2 = list.indexOf(value2);
        System.out.println(value1 + "------" + index1);
        System.out.println(value2 + "------" + index2);


        list.remove(2);
        System.out.println(list);
    }
}
