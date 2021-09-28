package threadcore.study.sixstates;

/**
 *  描述： 展示线程的NEW,RUNNABLE,TERMINATED
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-09-25 11:22]
 */
public class NewRunnableTerminated implements Runnable{
    public static void main(String[] args) {
        Thread thread = new Thread(new NewRunnableTerminated());
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());

        try{
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Runnable
        System.out.println(thread.getState());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Terminated
        System.out.println(thread.getState());
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("i===================" + i);
        }
    }
}
