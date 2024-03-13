package completablefuture;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

/**
 * 模拟异步请求多个叫车服务
 *
 * @author zhaoqw
 * @date 2024/3/8
 */
public class CompletableFutureExam1 {
    private static final int CORE_NUM = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = (CORE_NUM << 4) + (CORE_NUM << 2);
    private static final int MAX_POOL_SIZE = CORE_POOL_SIZE << 2;

    /**
     * 具体配置根据项目实际需求设置
     */
    private static final Executor EXECUTOR = new ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAX_POOL_SIZE,
            60,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(MAX_POOL_SIZE),
            Executors.defaultThreadFactory(),
            (r, e) -> e.execute(r)
    );

    private static final ScheduledExecutorService SCHEDULED_FUTURE = new ScheduledThreadPoolExecutor(
            1,
            r -> {
                Thread t = new Thread(r);
                t.setDaemon(true);
                t.setName("Timeout");
                return t;
            },
            (r, e) -> e.execute(r)
    );

    public static void main(String[] args) {
        System.out.println("main start: " + LocalDateTime.now());
        CallingStatus status = new CallingStatus();
        List<OrderCreateIn> orderCreateIns = List.of(
                new OrderCreateIn("DD"),
                new OrderCreateIn("CC"),
                new OrderCreateIn("SQ")
        );
        @SuppressWarnings("unchecked")
        CompletableFuture<OrderCreateOut>[] futureList = orderCreateIns.stream()
            // 过滤超时的供应商，防止再次超时，导致在供应商创建多个订单
            // .filter(out -> !timeoutSupplierCode.contains(out.supplierCode()))
                .map(orderCreateIn -> orderCreate(orderCreateIn))
                .peek(future -> future.thenAccept(orderCreateOut -> {
                    status.setSuccess(true);
                    if (status.isTimeout()) {
                        /*
                         * 记录请求超时的供应商，并对超时订单进行处理
                         * 1. 在未处理完该超时订单，该供应商将不再请求
                         * 2. 取消超时订单，因为未参与策略选择
                         */
                        System.out.printf("保存处理超时供应商（%s）和订单（%s）%n",
                                orderCreateOut.getSupplierCode(), orderCreateOut.getOrderId());
                    }
        })).toArray(CompletableFuture[]::new);
        CompletableFuture<Void> all = CompletableFuture.allOf(futureList);
        CompletableFuture<Void> timeout = timeout(6, TimeUnit.SECONDS, "叫车超时");
        CompletableFuture<Void> done = all.applyToEither(timeout, Function.identity());
        try {
            done.get();
        } catch (Exception e) {
            // 日志工具记录异常信息
            e.printStackTrace();
            status.setTimeout(true);
        }

        System.out.printf("用户创建订单是否成功：%s%n", status.isSuccess());
        System.out.println("main end: " + LocalDateTime.now());
    }

    public static CompletableFuture<OrderCreateOut> orderCreate(OrderCreateIn in) {
        return CompletableFuture.supplyAsync(() -> {
            int spendTime = (int) (Math.random() * 10);
            System.out.printf("订单 - 开始创建。threadId: %s, sleep: %ds, supplierCode: %s%n", Thread.currentThread().getId(), spendTime, in.getSupplierCode());
            try {
                // 模拟业务执行
                Thread.sleep(spendTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.printf("订单 - 结束创建。threadId: %s, sleep: %ds, supplierCode: %s%n", Thread.currentThread().getId(), spendTime, in.getSupplierCode());
            return new OrderCreateOut(in.getSupplierCode(), LocalDateTime.now().toString());
        }, EXECUTOR);
    }

    public static <T> CompletableFuture<T> timeout(long timeout, TimeUnit unit, String message) {
        CompletableFuture<T> ret = new CompletableFuture<>();
        SCHEDULED_FUTURE.schedule(() -> ret.completeExceptionally(new TimeoutException(message)), timeout, unit);
        return ret;
    }

    private static class OrderCreateIn {
        private String supplierCode;

        public OrderCreateIn(String supplierCode) {
            this.supplierCode = supplierCode;
        }

        public OrderCreateIn() {
        }

        public String getSupplierCode() {
            return supplierCode;
        }

        public void setSupplierCode(String supplierCode) {
            this.supplierCode = supplierCode;
        }
    }



    private static class OrderCreateOut {
        private String supplierCode;
        private String orderId;

        public OrderCreateOut() {
        }

        public OrderCreateOut(String supplierCode, String orderId) {
            this.supplierCode = supplierCode;
            this.orderId = orderId;
        }

        public String getSupplierCode() {
            return supplierCode;
        }

        public void setSupplierCode(String supplierCode) {
            this.supplierCode = supplierCode;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }
    }

    private static class CallingStatus {
        /**
         * 只要有一个供应商成功即成功
         */
        private volatile boolean success;
        /**
         * 超时
         */
        private volatile boolean timeout;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public boolean isTimeout() {
            return timeout;
        }

        public void setTimeout(boolean timeout) {
            this.timeout = timeout;
        }
    }
}
