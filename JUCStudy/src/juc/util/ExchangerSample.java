package juc.util;

import java.util.concurrent.Exchanger;

/**
 * @author zhaoqw
 * @date 2023/02/04
 */
public class ExchangerSample {
    public static void main(String[] args) {
        Exchanger<String> stringExchanger = new Exchanger<>();

        String str1 = "1000001";
        String str2 = "1000002";

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "初始值:" + str1);
            try {
                String exchange = stringExchanger.exchange(str1);
                System.out.println(Thread.currentThread().getName() + "交换后:" + exchange);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }, "Thread001").start();


        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "初始值:" + str2);
            try {
                Thread.sleep(2000);
                String exchange = stringExchanger.exchange(str2);
                System.out.println(Thread.currentThread().getName() + "交换后:" + exchange);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }, "Thread002").start();
    }
}
