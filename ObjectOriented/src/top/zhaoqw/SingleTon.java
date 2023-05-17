package top.zhaoqw;

/**
 * @author zhaoqw
 * @date 2023/05/08
 */
public class SingleTon {
    private static SingleTon INSTANCE = new SingleTon();

    private String content;

    // 私有话构造器
    private SingleTon() {
        this.content = "abc";
    }

    public static SingleTon getInstance() {
        return INSTANCE;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public static void main(String[] args) {
        SingleTon instance = SingleTon.getInstance();
        System.out.println(instance.getContent());

        SingleTon instance2 = SingleTon.getInstance();
        System.out.println(instance2.getContent());

        instance2.setContent("def");
        System.out.println(instance.getContent());
        System.out.println(instance2.getContent());
        System.out.println(instance == instance2);

    }
}
