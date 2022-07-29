package cn.itedu.number;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * BigDecimal 的基本使用
 *
 * @author zhaoqw
 * @date 2022/07/29
 */
public class BigDecimalTest {
  public static void main(String[] args) {
    BigDecimal bigDecimal1 = new BigDecimal("12345.1234");
    BigDecimal bigDecimal2 = new BigDecimal("22345.56432");
    System.out.println("b1:" + bigDecimal1 + ", b2:" + bigDecimal2);
    System.out.println("加法：" + bigDecimal1.add(bigDecimal2)); // 加法
    System.out.println("减法：" + bigDecimal2.subtract(bigDecimal2)); //减法
    System.out.println("乘法：" + bigDecimal2.multiply(bigDecimal1)); //乘法
    // 需要指定位数，防止无限循环
    System.out.println("除法：" + bigDecimal2.divide(bigDecimal1, 10, RoundingMode.FLOOR));
    System.out.println("最大数：" + bigDecimal1.max(bigDecimal2));
    System.out.println("最小数：" + bigDecimal1.min(bigDecimal2));
    BigDecimal[] bigDecimals = bigDecimal2.divideAndRemainder(bigDecimal1); // 求出余数的除法
    System.out.println("商：" + bigDecimals[0] + ", 余数：" + bigDecimals[1]);
    System.out.println("等价性：" + bigDecimal1.equals(bigDecimal2));
    int flag = bigDecimal1.compareTo(bigDecimal2);
    if (flag == -1){
      System.out.println("比较操作：b1 < b2");
    } else if (flag == 0) {
      System.out.println("比较操作：b1 == b2");
    } else {
      System.out.println("比较操作： b1 > b2");
    }
  }
}
