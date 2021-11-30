package lambdaexample;

import java.util.function.Consumer;

/**
 * Consumer的使用
 */
public class ConsumerSample {
    public static void output(Consumer<String> consumer) {
        String text = "天将降大任于斯人也,不限苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为";
        consumer.accept(text);
    }

    public static void main(String[] args) {
        output(s -> System.out.println("向控制台打印:\n" + s));

        output(s -> System.out.println("向某网站发送数据包:\n" + s));
    }
}
