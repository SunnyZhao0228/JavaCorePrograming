package threadcore.study.stopthreads;

/**
 * run() 方法内没有sleep或者wait方法时，停止线程
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-09-11 10:22]
 */
public class RightWayStopThreadWithoutSleep implements Runnable{

    @Override
    public void run() {
        int num = 0;
        while(!Thread.currentThread().isInterrupted() && num < Integer.MAX_VALUE >> 1 ) {
            if (num % 10000 == 0) {
                System.out.println(num + "是10000的倍数！");
            }
            num++;
        }
        System.out.println("任务结束");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RightWayStopThreadWithoutSleep());
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
    }
}
