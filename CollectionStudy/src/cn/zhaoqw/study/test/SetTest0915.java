package cn.zhaoqw.study.test;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-09-15 10:30]
 */
public class SetTest0915 {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 20; i++) {
            String next = sc.next();
            set.add(next);
        }

        set.forEach((str) -> System.out.print(str + " "));
    }
}
