package threadcore.study.threadSingleTom;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-11-18 20:34]
 */
public class Mgr01 {
    private static final Mgr01 INSTANCE = new Mgr01();

    private Mgr01() {}

    public static Mgr01 getInstance() {
        return INSTANCE;
    }

    public void m() {

    }
}

