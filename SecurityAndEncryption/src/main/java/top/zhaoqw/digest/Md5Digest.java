package top.zhaoqw.digest;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 哈希算法（Hash）又称摘要算法（Digest）
 *  MD5	 输出长度  128 bits	16 bytes
 * @author zhaoqw
 * @date 2023/5/17
 */
public class Md5Digest {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // 创建一个MessageDigest实例:
        MessageDigest md = MessageDigest.getInstance("MD5");
        // 反复调用update输入数据:
        md.update("Hello".getBytes("UTF-8"));
        md.update("World".getBytes("UTF-8"));
        byte[] result = md.digest(); // 16 bytes: 68e109f0f40ca72a15e05cc22786f8e6
        System.out.println(new BigInteger(1, result).toString(16));
    }
}
