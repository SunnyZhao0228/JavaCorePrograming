package reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock的基本用法，演示被打断
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-09-26 22:39]
 */
public class LookDemo {
    public static void main(String[] args) {
        new LookDemo().init();
    }
    private void init() {

        final Outputer outputer = new Outputer();
        new Thread(()->{
            while(true) {
                try{
                    Thread.sleep(5);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                outputer.output("牛叠为牛逼");
            }
        }).start();

        new Thread(()->{
            while(true) {
                try{
                    Thread.sleep(5);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                outputer.output("zqw垃圾");
            }
        }).start();
    }



    static class Outputer{
        Lock lock = new ReentrantLock();

        //字符串打印方法，一个一个字符得打印
        public void output(String name) {
            int len = name.length();
            lock.lock();
            try{
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println("");
            }finally {
               lock.unlock();
            }
        }
    }
}
