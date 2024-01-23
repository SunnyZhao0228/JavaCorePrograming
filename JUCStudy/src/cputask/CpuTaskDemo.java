package cputask;

/**
 * 具体哪个线程先执行是由操作系统调度
 *
 *
 * @author zhaoqw
 * @date 2023/12/26
 */
public class CpuTaskDemo {
    public void printChar(String word){
        System.out.print(word);
    }

    public static void main(String[] args) {
        CpuTaskDemo c = new CpuTaskDemo();
        Thread taskA = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<100;i++){
                    c.printChar("A");
                }
            }
        });
        taskA.start();

        Thread taskB = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<100;i++){
                    c.printChar("B");
                }
            }
        });
        taskB.start();

        Thread taskC = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<100;i++){
                    c.printChar("C");
                }
            }
        });
        taskC.start();

        Thread taskD = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<100;i++){
                    c.printChar("D");
                }
            }
        });
        taskD.start();
    }
}
