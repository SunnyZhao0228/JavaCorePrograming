package atomic.msb;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-10-15 23:23]
 */
public class AtomicIntegerTest {
    /*volatile*/ //int count1 = 0;
    AtomicInteger count = new AtomicInteger(0);
    /*synchronized*/void m() {
        for (int i = 0; i < 1000; i++) {
            //count++;
            count.incrementAndGet();
        }
    }


    public static void main(String[] args) {
        AtomicIntegerTest t = new AtomicIntegerTest();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m,"thread-"+ i));
        }

        threads.forEach((o) -> o.start());

        threads.forEach((o) ->{
            try {
                o.join();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(t.count);
    }


}
