package threadcore.study.uncaoughtexception;

/**
 * 单线程情况下，异常抛出，处理，有异常堆栈
 * 多线程情况下，子线程出现异常，会有什么不同？
 */
public class ExceptionInChildThread implements  Runnable{
    public static void main(String[] args) {
        new Thread(new ExceptionInChildThread()).start();
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
