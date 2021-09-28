package collections.predecessor;

import java.util.Vector;

/**
 * vector 的方法都用synchronized修饰，
 * 在并发量大的时候，性能不怎么好
 * 他和ArrayList
 */
public class VectorDemo {
    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();

        new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                vector.add(i);
            }
        }).start();

        new Thread(() -> {
            for (int i = 1; i < 70; i++) {
                vector.remove(i);

            }
        }).start();


        System.out.println(vector);
    }
}
