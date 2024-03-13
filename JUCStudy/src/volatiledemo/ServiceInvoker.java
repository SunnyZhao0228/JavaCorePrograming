package volatiledemo;

/**
 * @author zhaoqw
 * @date 2024/3/13
 */
public class ServiceInvoker {

    // 保存当前类的唯一实例
    private static final ServiceInvoker INVOKER = new ServiceInvoker();

    // 负载均衡器实例，使用volatile变量保障可见性
    private volatile LoadBanlancer loadBanlancer;

    public ServiceInvoker() {
    }

    public static ServiceInvoker getInstance() {
        return INVOKER;
    }

    public void dispatchRequest() {

    }
}
