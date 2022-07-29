package cn.itedu.number;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * 随机相关API
 *
 * @author zhaoqw
 * @date 2022/07/29
 */
public class RandomTest {
  public static void main(String[] args) {
    Random random = new Random();
    // Random 生成一个范围的随机树
    System.out.println(random.nextInt());
    System.out.println(random.nextInt(100)); // 0-100 随机
    System.out.println(random.nextLong());
    System.out.println(random.nextDouble());
    System.out.println("========================");

    // Math.random()  生成[0,1) 之间的随机数
    System.out.println(Math.round(Math.random()* 10));
    System.out.println("========================");

    // random.ins()  返回无限个int范围里面的数字 Java8新增
    int[] ints = random.ints(10).toArray();
    for (int anInt : ints) {
      System.out.print(anInt + " ");
    }

    System.out.println();
    // 随机生成5个 10-100的整数
    int[] arr = random.ints(5, 10, 100).toArray();
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }

    System.out.println("==================");
    arr = random.ints(10).limit(5).toArray();
    for (int i = 0; i < arr.length; i++) {
      System.out.println(arr[i]);
    }

  }
}
