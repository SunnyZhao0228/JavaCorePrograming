package cn.itedu.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhaoqw
 * @date 2022/07/29
 */
public class DateTest {
  public static void main(String[] args) {
    Date date = new Date();
    System.out.println(date);
    System.out.println(date.getTime());

    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
    String format = sdf.format(date);
    System.out.println(format);
  }
}
