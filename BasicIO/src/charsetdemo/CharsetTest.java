package charsetdemo;

import java.nio.charset.Charset;
import java.util.Set;
import java.util.SortedMap;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-10-06 11:59]
 */
public class CharsetTest {
    public static void main(String[] args) {
        Charset charset = Charset.defaultCharset();
        System.out.println(charset);

        //输出所有字符集
        SortedMap<String,Charset> sm = Charset.availableCharsets();
        Set<String> keySet = sm.keySet();
        System.out.println("Java支持的所有字符集");
        for (String s : keySet) {
            System.out.println(sm.get(s));
        }
    }
}
