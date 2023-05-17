package top.zhaoqw.aboutstatic;

/**
 * 静态代码块，匿名代码块，构造器
 *
 * @author zhaoqw
 * @date 2023/05/08
 */
public class StaticBlock {
    //staticl block > anonymous block > constructor function
    static
    {
        System.out.println("22222222222222222222");
    }
    {
        System.out.println("11111111111111111111");
    }
    public StaticBlock()
    {
        System.out.println("33333333333333333333");
    }
    {
        System.out.println("44444444444444444444");
    }

    public static void main(String[] args) {
        StaticBlock staticBlock = new StaticBlock();
    }
}
