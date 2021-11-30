package threadcore.study.threadSingleTom;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-11-18 20:35]
 */
public class Mgr02 {
    private static final Mgr02 INSTANCE;
    static {
        INSTANCE = new Mgr02();
    }

    private  Mgr02() {

    }

    public static Mgr02 getInstance() {
        return INSTANCE;
    }
}
