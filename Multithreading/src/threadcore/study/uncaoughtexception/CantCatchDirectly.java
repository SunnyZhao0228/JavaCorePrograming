package threadcore.study.uncaoughtexception;

/**
 * 描述
 * 1.不加try catch 抛出4个异常，带线程名字
 * 2.加try catch, 期望捕获到第一个县城的异常，线程234不应该运行，希望看到打印出Caought Exception
 * 3. 执行发现，没有Caought Exception 线程234依然运行，并且抛出异常
 */
public class CantCatchDirectly implements Runnable{

    public static void main(String[] args) throws InterruptedException {
        try{

            new Thread(new CantCatchDirectly(),"MyThread-1").start();
            Thread.sleep(200);
            new Thread(new CantCatchDirectly(),"MyThread-2").start();
            Thread.sleep(200);
            new Thread(new CantCatchDirectly(),"MyThread-3").start();
            Thread.sleep(200);
            new Thread(new CantCatchDirectly(),"MyThread-4").start();
            Thread.sleep(200);
        }catch (RuntimeException e) {
            System.out.println("Caought Exception");
            e.printStackTrace();
        }

    }
    @Override
    public void run() {
        throw new RuntimeException();
    }
}
