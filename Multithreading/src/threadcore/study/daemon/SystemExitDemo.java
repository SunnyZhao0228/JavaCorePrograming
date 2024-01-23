package threadcore.study.daemon;

public class SystemExitDemo {

    public static void main(String[] args) {
        //坚听系统退出信号
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("close1");
        }));

        //坚听系统退出信号
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("close2");
        }));

        Thread daemonThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("this is daemon thread start");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("this is daemon thread end");
            }
        });
        daemonThread.setDaemon(true);
        daemonThread.start();
        System.exit(1);
        System.out.println("这里不会执行到");
    }
}