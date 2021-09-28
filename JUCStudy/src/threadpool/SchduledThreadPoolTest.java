package threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-09-14 14:46]
 */
public class SchduledThreadPoolTest {
    public static void main(String[] args) {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(5);

        //延迟3s执行任务
        //ses.schedule(new MyTask(),3, TimeUnit.SECONDS);

        //开始延迟1s，然后每隔3s执行一次任
        ses.scheduleAtFixedRate(new MyTask(),1,3,TimeUnit.SECONDS);
    }
}
