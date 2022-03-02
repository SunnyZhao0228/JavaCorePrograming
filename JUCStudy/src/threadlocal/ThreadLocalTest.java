package threadlocal;

/**
 * @author: zhaoqw
 * @date: 2022/3/2 13:21
 */
public class ThreadLocalTest {
  static ThreadLocal<String> localVariable = new ThreadLocal<>();
  static void print(String str) {
    //
    System.out.println(str + ":" + localVariable.get());

    // 移除threadloal里的值
    //localVariable.remove();
  }


  public static void main(String[] args) {
    Thread threadOne = new Thread(() -> {
      localVariable.set("thread one localthread variable");
      print("threadOne");
      System.out.println("after remove: " + localVariable.get());
    });

    Thread threadTwo = new Thread(() -> {
      localVariable.set("thread two localthread variable");
      print("threadTwo");
      System.out.println("after remove: " + localVariable.get());
    });

    threadOne.start();
    threadTwo.start();
  }
}
