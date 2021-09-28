package threadcore.study.startthread;

/**
 *
 * 两次调用start方法，会怎么样
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-09-11 9:29]
 */
public class CantStartTwice {
    /**
     * 启动新线程检查线程状态
     * 加入线程组
     * 调用start0()
     * @param args
     */
    public static void main(String[] args) {
        Thread thread = new Thread();

        thread.start();
        thread.start();
    }
}
