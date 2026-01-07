package threadcore.study.threadfield;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * Id 从1开始，JVM运行起来之后，我们自己创建的线程早就不是2了
 */
public class ThreadId {


    public static void main(String[] args) {
        Thread thread = new Thread("aaa");
        System.out.println("主线程id"+Thread.currentThread().getId());
        System.out.println("子线程id"+thread.getId());
        System.out.println("子线程名" + thread.getName());

        // Java 9 之前的实现
        getProcessIdBeforeJava9();
        getProcessIdInJava9();
        getProcessIdInJava10();
    }

    private static void getProcessIdInJava10() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println("[Java 10 + 的方法] 当前进程 ID : " + runtimeMXBean.getPid());
    }

    private static void getProcessIdInJava9() {
        long pid = ProcessHandle.current().pid();
        System.out.println("[Java 9 + 的方法] 当前进程 ID : " + pid);
    }

    private static void getProcessIdBeforeJava9() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        String name = runtimeMXBean.getName();
        String pid = name.substring(0, name.indexOf("@"));
        System.out.println("[Java 9 之前的方法] 当前进程 ID : " + pid);
    }
}
