package reflection.aopdemo;

import reflection.aopdemo.Aspect;

/**
 * @author zhaoqw
 * @date 2023/01/08
 */
public class TimeUseageAspect implements Aspect {
    long start;
    @Override
    public void before() {
        start = System.currentTimeMillis();
    }

    @Override
    public void after() {
        long usage = System.currentTimeMillis() - start;
        System.out.format("Time useage: %dms\n", usage);
    }
}
