package threadcore.study.threadSingleTom;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-11-18 20:37]
 */
public class Mgr03 {
    private static  Mgr03 INSTANCE;
    private Mgr03() {}
    public static Mgr03 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Mgr03();
        }
        return INSTANCE;
    }
}
