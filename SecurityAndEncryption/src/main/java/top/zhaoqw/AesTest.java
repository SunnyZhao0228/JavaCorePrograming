package top.zhaoqw;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author zhaoqw
 * @date 2024/6/18
 */
public class AesTest {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String key = "adfafa";
/*        String s = aesEncode(key, "aaaabbbbccccddddeeeeffff");
        String s1 = aesDecode(key, s);
        System.out.println(s);
        System.out.println(s1);*/
    }


  /*  public static String aesEncode(String encodeRules, String content) {
        try{
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");

            // 初始化密钥生成器
            keyGenerator.init(128, new SecureRandom(encodeRules.getBytes(StandardCharsets.UTF_8)));

            // 1. 产生原始对称密钥
            SecretKey secretKey = keyGenerator.generateKey();

            byte[] encoded = secretKey.getEncoded();

            // 2.更具字节数组生成AES密钥
            SecretKeySpec aesKey = new SecretKeySpec(encoded, "AES");

            // 3.更具指定算法AES生成密码器
            Cipher cipher = Cipher.getInstance("AES");

            // 4. 初始化密码器
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);

            byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
            byte[] encodeBytes = cipher.doFinal(bytes);

            // 加密后的数据转成字符串
            String aesEncode = new String(new BASE64Encoder().encode(encodeBytes));
            System.out.println(aesEncode);
            return  aesEncode;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String aesDecode(String seed, String content) throws NoSuchAlgorithmException {
        try {
            // 1. 构造秘钥生成器
            KeyGenerator kegen = KeyGenerator.getInstance("AES");
            // 2. 更具seed生成安全随机数
            kegen.init(128, new SecureRandom(seed.getBytes()));
            // 3. 生成原始对称密钥
            SecretKey originKey = kegen.generateKey();
            // 4. 获得原始对称密钥的字节数组
            byte[] raw = originKey.getEncoded();
            // 5. 获取字节数组生成AES专用秘钥
            SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
            // 6. 更具AES生成专用秘钥
            Cipher cipher = Cipher.getInstance("AES");

            // 7. 初始化密码器
            cipher.init(Cipher.DECRYPT_MODE, keySpec);

            byte[] bytes = new BASE64Decoder().decodeBuffer(content);
            byte[] bytesDecode = cipher.doFinal(bytes);
            String result = new String(bytesDecode, StandardCharsets.UTF_8);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/
}
