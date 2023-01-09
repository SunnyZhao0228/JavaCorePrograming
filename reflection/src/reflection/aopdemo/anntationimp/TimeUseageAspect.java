package reflection.aopdemo.anntationimp;

/**
 * @author zhaoqw
 * @date 2023/01/08
 */
public class TimeUseageAspect implements IAspect{
    long start;

    public void before() {
        System.out.println("before.....................");
        start = System.currentTimeMillis();
    }

    public void after() {
        long usage = System.currentTimeMillis() - start;
        System.out.println("after.....................");
        System.out.format("Time useage: %dms\n", usage);
    }
}
