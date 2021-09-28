package threadcore.study.stopthreads;

/**
 * run 无法抛出checked Exception 只能用try/catch
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-09-19 23:54]
 */
public class RunThrowException {
    public void avoid() throws Exception{

        throw new Exception();

    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
