package threadcore.study.threadfield;

/**
 * Id 从1开始，JVM运行起来之后，我们自己创建的线程早就不是2了
 */
public class ThreadId {
    public static void main(String[] args) {
        Thread thread = new Thread("aaa");
        System.out.println("主线程id"+Thread.currentThread().getId());
        System.out.println("子线程id"+thread.getId());
        System.out.println("子线程名" + thread.getName());

    }

}
