package threadcore.study;

public class ConcurrencyTest {
    private static final long count = 1000000000;

    public static void concurrency() throws InterruptedException {
        long begin = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < count; i++) {
                }
            }
        });
        thread.start();
        long end = System.currentTimeMillis();
        thread.join();
        System.out.println("并发消耗时间为：" + (end - begin));
    }

    public static void serial() {
        long begin = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
        }
        long end = System.currentTimeMillis();
        System.out.println("串行消耗时间为：" + (end - begin));
    }

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }

}
