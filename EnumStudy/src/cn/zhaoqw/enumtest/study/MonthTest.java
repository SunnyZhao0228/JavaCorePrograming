package cn.zhaoqw.enumtest.study;

public class MonthTest {
    public static void main(String[] args) {
        Month jan = Month.JAN;
        System.out.println(jan.toString());
        for (Month m: Month.values()) {
            System.out.println(m.toString() + "  ordinal:" + m.ordinal());
        }
    }
}
