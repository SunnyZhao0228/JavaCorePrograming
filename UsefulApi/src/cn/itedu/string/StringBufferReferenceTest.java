package cn.itedu.string;

import java.util.Calendar;

/**
 * @author zhaoqw
 * @date 2022/07/29
 */
public class StringBufferReferenceTest {
  public static void main(String[] args) {
    StringBuffer sb1 = new StringBuffer();
    StringBuffer sb2 = sb1;
    sb1.append("12334567788978");
    System.out.println(sb2); // sb1 和 sb2 指向的同一个内存

    stringAppendTest();
  }


  public static void stringAppendTest() {
    int n = 60000;
    Calendar t1 = Calendar.getInstance();
    String str = new String();
    for (int i = 0; i < n; i++) {
      str = str + i + ",";
    }
    System.out.println(Calendar.getInstance().getTimeInMillis() - t1.getTimeInMillis());


    Calendar t2 = Calendar.getInstance();
    StringBuffer sb1 = new StringBuffer();
    for (int i = 0; i < n; i++) {
      sb1.append(i).append(",");
    }
    System.out.println(Calendar.getInstance().getTimeInMillis() - t2.getTimeInMillis());

    Calendar t3 = Calendar.getInstance();
    StringBuilder sb2 = new StringBuilder();
    for (int i = 0; i < n; i++) {
      sb2.append(i).append(",");
    }
    System.out.println(Calendar.getInstance().getTimeInMillis() - t3.getTimeInMillis());

  }
}
