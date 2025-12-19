package threadcore.study.volatileexample;


/**
 * @author zhaoqw
 * @version 1.0
 * @date 2025/8/12
 */
public class VolatileExample {
    int x = 0;

    boolean v = false;


    public void write()
    {
        x = 1;
        v = true;
    }

    public void read()
    {
        if (v)
        {
            x = 1;
        }
    }
}
