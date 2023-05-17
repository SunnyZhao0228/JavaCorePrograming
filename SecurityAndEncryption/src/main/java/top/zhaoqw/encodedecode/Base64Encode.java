package top.zhaoqw.encodedecode;

import java.util.Arrays;
import java.util.Base64;

/**
 * Base64编码
 *
 * @author zhaoqw
 * @date 2023/5/17
 */
public class Base64Encode {

    /**
     * URL编码是对字符进行编码，表示成%xx的形式，而Base64编码是对二进制数据进行编码，表示成文本格式。
     *
     * Base64编码可以把任意长度的二进制数据变为纯文本，且只包含A~Z、a~z、0~9、+、/、=这些字符。
     * 它的原理是把3字节的二进制数据按6bit一组，用4个int整数表示，
     * 然后查表，把int整数用索引对应到字符，得到编码后的字符串。
     *
     * @param args
     */
    public static void main(String[] args) {

        byte[] input = new byte[] { (byte) 0xe4, (byte) 0xb8, (byte) 0xad };
        String str1 = Base64.getEncoder().encodeToString(input);
        System.out.println(str1);
        byte[] output = Base64.getDecoder().decode(str1);
        System.out.println(Arrays.toString(output));
        /**
         * 如果输入的byte[]数组长度不是3的整数倍肿么办？这种情况下，需要对输入的末尾补一个或两个0x00，
         * 编码后，在结尾加一个=表示补充了1个0x00，
         * 加两个=表示补充了2个0x00，解码的时候，去掉末尾补充的一个或两个0x00即可。
         */

        byte[] input2 = new byte[] { (byte) 0xe4, (byte) 0xb8, (byte) 0xad, 0x21 };
        String b64encoded = Base64.getEncoder().encodeToString(input2);
        String b64encoded2 = Base64.getEncoder().withoutPadding().encodeToString(input2);
        System.out.println(b64encoded);
        System.out.println(b64encoded2);
        byte[] output2 = Base64.getDecoder().decode(b64encoded2);
        System.out.println(Arrays.toString(output2));

        //Base64.getUrlEncoder是一种针对URL的Base64编码可以在URL中使用的Base64编码
        byte[] input3 = new byte[] { 0x01, 0x02, 0x7f, 0x00 };
        String urlEncode = Base64.getUrlEncoder().encodeToString(input3);
        System.out.println(urlEncode);
        byte[] decode = Base64.getUrlDecoder().decode(urlEncode);
        System.out.println(Arrays.toString(decode));
    }
}
