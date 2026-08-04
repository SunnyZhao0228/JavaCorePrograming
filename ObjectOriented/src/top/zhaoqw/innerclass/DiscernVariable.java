package top.zhaoqw.innerclass;

public class DiscernVariable {
    private String prop = "外部类的实例变量";

    private class InClass {
        private String prop = "内部类的实例变量";
        private static int count = 0;
        public void info() {
            var prop = "局部变量";
            // 通过外部类类名．this.varName 访问外部类实例变量
            System.out.println("外部类的实例变量值：" + DiscernVariable.this.prop);
            // 通过this.varName 访问内部类实例的变量
            System.out.println("内部类的实例变量值：" + this.prop);
            // 直接访问局部变量
            System.out.println("局部变量的值：" + prop);
        }
    }

    public void test() {
        System.out.println("count = " + InClass.count);
        var in = new InClass();
        in.info();
    }

    public static void main(String[] args) {
        new DiscernVariable().test();
    }
}