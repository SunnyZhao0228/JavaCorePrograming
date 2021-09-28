package threadcore.study.createthread;

public class RunnableStyle implements Runnable{
    @Override
    public void run() {
        System.out.println("实现Runnable 重写run方法实现线程");
    }

    public static void main(String[] args) {
        RunnableStyle runnableStyle = new RunnableStyle();
        Thread thread = new Thread(runnableStyle);
        thread.start();
    }
}
