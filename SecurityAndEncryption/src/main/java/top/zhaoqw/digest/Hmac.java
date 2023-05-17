package top.zhaoqw.digest;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * @author zhaoqw
 * @date 2023/5/17
 */
public class Hmac {

    /**
     * HmacMD5 ≈ md5(secure_random_key, input)
     * HmacMD5可以看作带有一个安全的key的MD5。使用HmacMD5而不是用MD5加salt，有如下好处：
     *
     * HmacMD5使用的key长度是64字节，更安全；
     * Hmac是标准算法，同样适用于SHA-1等其他哈希算法；
     * Hmac输出和原有的哈希算法长度一致。
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacMD5");
        SecretKey key = keyGen.generateKey();
        // 打印随机生成的key:
        byte[] skey = key.getEncoded();
        System.out.println(new BigInteger(1, skey).toString(16));
        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(key);
        mac.update("HelloWorld".getBytes("UTF-8"));
        byte[] result = mac.doFinal();
        System.out.println(new BigInteger(1, result).toString(16));

        /**
         * 有了Hmac计算的哈希和SecretKey，我们想要验证怎么办？这时，
         * SecretKey不能从KeyGenerator生成，而是从一个byte[]数组恢复：
         */

        byte[] hkey = new byte[] { 106, 70, -110, 125, 39, -20, 52, 56, 85, 9, -19, -72, 52, -53, 52, -45, -6, 119, -63,
                30, 20, -83, -28, 77, 98, 109, -32, -76, 121, -106, 0, -74, -107, -114, -45, 104, -104, -8, 2, 121, 6,
                97, -18, -13, -63, -30, -125, -103, -80, -46, 113, -14, 68, 32, -46, 101, -116, -104, -81, -108, 122,
                89, -106, -109 };

        SecretKey validKey = new SecretKeySpec(hkey, "HmacMD5");
        Mac mac2 = Mac.getInstance("HmacMD5");
        mac2.init(validKey);
        mac2.update("HelloWorld".getBytes("UTF-8"));
        byte[] result2 = mac2.doFinal();
        System.out.println(Arrays.toString(result2));
        // [126, 59, 37, 63, 73, 90, 111, -96, -77, 15, 82, -74, 122, -55, -67, 54]
    }
}
