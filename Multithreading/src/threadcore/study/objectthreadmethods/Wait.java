package threadcore.study.objectthreadmethods;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-09-27 22:15]
 */
public class Wait implements Runnable{
    public static Object object = new Object();

    @Override
    public void run() {
        synchronized (object) {
            System.out.println(Thread.currentThread().getName() + "开始执行了...");

            try{
                object.wait();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
