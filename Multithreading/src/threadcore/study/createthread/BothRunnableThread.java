package threadcore.study.createthread;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-09-11 8:34]
 */
public class BothRunnableThread {
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable");
            }
        }) {
            // 重写run方法所以打印thread
            @Override
            public void run() {
                System.out.println("thread");
            }
        }.start();
    }
}
