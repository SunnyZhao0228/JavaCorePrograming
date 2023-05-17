package top.zhaoqw.encodedecode;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author zhaoqw
 * @date 2023/5/17
 */
public class UrlEncode {
    /**
     * URL编码，是因为出于兼容性考虑，很多服务器只识别ASCII字符。但如果URL中包含中文、日文这些非ASCII字符怎么办？
     * 不要紧，URL编码有一套规则：
     *
     * 如果字符是A~Z，a~z，0~9以及-、_、.、*，则保持不变；
     * 如果是其他字符，先转换为UTF-8编码，然后对每个字节以%XX表示。
     * 例如：字符中的UTF-8编码是0xe4b8ad，因此，它的URL编码是%E4%B8%AD。URL编码总是大写。
     *
     * URL编码便于浏览器和服务器处理
     * @param args
     */
    public static void main(String[] args) {
        String encode = URLEncoder.encode("ABCD中文!", StandardCharsets.UTF_8);
        System.out.println(encode);
        String decoded = URLDecoder.decode("%E4%B8%AD%E6%96%87%21", StandardCharsets.UTF_8);
        System.out.println(decoded);
    }
}
