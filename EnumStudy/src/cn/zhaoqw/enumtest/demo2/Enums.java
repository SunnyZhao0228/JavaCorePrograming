package cn.zhaoqw.enumtest.demo2;

import cn.zhaoqw.enumtest.demo1.Spiciness;
import cn.zhaoqw.enumtest.study.MonthTest;

import java.util.Random;

public class Enums {
    private static Random rand = new Random(47);
    public static <T extends Enum<T>> T random(Class<T> ec) {
        return random(ec.getEnumConstants());
    }
    public static <T> T random(T[] values) {
        return values[rand.nextInt(values.length)];
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Spiciness random = Enums.random(Spiciness.class);
            System.out.println(random);
        }

    }
}
