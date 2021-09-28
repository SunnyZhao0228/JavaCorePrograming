package threadcore.study.stopthreads;

/**
 * 最佳实践： catch了InterruptedException之后的优先选择：在方法签名中抛出异常
 * 那么在run()方法中会强制try/catch
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-09-19 23:42]
 *
 */
public class RightWayStopThreadProd implements Runnable{
    @Override
    public void run() {
        while(true && ! Thread.currentThread().isInterrupted()) {
            System.out.println("go");
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                //如若线程被中断，记录日志、停止程序
                System.out.println("保存日志");
                e.printStackTrace();
            }
        }
    }

    private void throwInMethod() throws InterruptedException {
        Thread.sleep(2000);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadProd());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
