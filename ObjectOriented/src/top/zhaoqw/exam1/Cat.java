package top.zhaoqw.exam1;

/**
 * @author zhaoqw
 * @date 2023/05/08
 */
public class Cat implements Animal {
    @Override
    public void eat() {
        System.out.println("Cat: I can eat");
    }

    @Override
    public void move() {
        System.out.println("Cat: I can move");
    }
}
