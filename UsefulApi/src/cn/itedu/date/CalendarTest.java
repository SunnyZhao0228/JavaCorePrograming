package cn.itedu.date;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Calendar Api测试
 *
 * @author zhaoqw
 * @date 2022/07/29
 */
public class CalendarTest {
  Calendar cal = Calendar.getInstance();

  public static void main(String[] args) {
    Calendar calendar = Calendar.getInstance();
    System.out.println(calendar.getClass().getName());
    GregorianCalendar gregorianCalendar = new GregorianCalendar();
    System.out.println(gregorianCalendar.getClass().getName());

    new CalendarTest().test1();
  }


  public void test1() {
    // 获取年
    int year = cal.get(Calendar.YEAR);
    System.out.println(year);
    // 获取月
    int month = cal.get(Calendar.MONTH) + 1;
    System.out.println(month);
    // 获取日
    int day = cal.get(Calendar.DAY_OF_MONTH);
    System.out.println(day);
    // 获取时
    int hour = cal.get(Calendar.HOUR);
    System.out.println(hour);

    // 获取分
    int minute = cal.get(Calendar.MINUTE);
    // 获取秒
    int second = cal.get(Calendar.SECOND);

    // 获取星期
    int weekend = cal.get(Calendar.DAY_OF_WEEK);

    System.out.println(year + "-" + month + "-" + day + "-" + hour + "-" + minute + "-" + second + "-" + weekend);
  }
}
