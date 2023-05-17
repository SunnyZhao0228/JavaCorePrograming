package top.zhaoqw.exam1;

/**
 * @author zhaoqw
 * @date 2023/05/08
 */
public class Dog implements Animal {
    @Override
    public void eat() {
        System.out.println("Dog: I can eat");
    }

    @Override
    public void move() {
        System.out.println("Dog: I can move");
    }
}
