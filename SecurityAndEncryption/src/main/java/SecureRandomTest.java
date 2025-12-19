import java.security.SecureRandom;
/**
 * @author zhaoqw
 * @date 2024/6/20
 */
/**
 * SecureRandom安全随机数生成器测试
 * 演示SecureRandom的基本使用方法
 */
public class SecureRandomTest {

    /**
     * 将字节数组转换为十六进制字符串
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int i;
        String str = "hello";  // 种子字符串
        byte[] salt = new byte[128];  // 生成128字节的随机数

        // 生成3组随机数，每组128字节
        for (i = 0; i < 3; i++) {
            // 使用指定种子创建SecureRandom实例
            // 注意：相同的种子会生成相同的随机数序列
            SecureRandom secureRandom = new SecureRandom(str.getBytes());
            secureRandom.nextBytes(salt);  // 生成随机字节填充到salt数组

            // 将字节数组转换为十六进制字符串显示
            String strHexBytes = bytesToHex(salt);
            System.out.println("第" + (i + 1) + "组随机数： " + strHexBytes);
            // System.out.print(Arrays.toString(salt));  // 可以打印原始字节数组
        }

        System.out.println("\n注意：由于使用了相同的种子，上面三组随机数是相同的！");
        System.out.println("建议：在实际应用中使用 new SecureRandom() 而不指定种子，以获得真正的随机性。");
    }


    /**
     * 测试方法1：先不设置种子，后设置种子
     * 演示setSeed()方法在nextBytes()之后调用的效果
     * 结论：后续调用setSeed()会补充随机源，但不会重置随机数生成器
     */
    public static void func1() {
        int i;
        String strHexBytes;
        String str = "hh";
        byte[] salt = new byte[32];  // 用于存储生成的随机字节
        byte[] seed = new byte[16];  // 自定义种子

        // 初始化种子数组：0, 1, 2, ..., 15
        for (i = 0; i < 16; i++)
            seed[i] = (byte) i;

        SecureRandom secureRandom = new SecureRandom();
        System.out.println("使用的算法：" + secureRandom.getAlgorithm());

        // 前面没有使用setSeed，直接生成随机数
        secureRandom.nextBytes(salt);  // 获得随机数
        strHexBytes = bytesToHex(salt);  // 字节数组转为16进制字符串
        System.out.println("第1次（未设置种子）：" + strHexBytes);  // 输出16进制的随机数

        // 后续两次都设置相同的种子
        for (i = 0; i < 2; i++) {
            secureRandom.setSeed(seed);  // 补充种子到现有的随机源
            secureRandom.nextBytes(salt);  // 获得随机数
            strHexBytes = bytesToHex(salt);  // 字节数组转为16进制字符串
            System.out.println("第" + (i + 2) + "次（设置种子后）：" + strHexBytes);  // 输出16进制的随机数
        }
        System.out.println("注意：即使设置了相同的种子，后两次生成的随机数也不同！\n");
    }

    /**
     * 测试方法2：先设置种子，再生成随机数
     * 演示setSeed()方法在nextBytes()之前调用的效果
     * 结论：先调用setSeed()再生成随机数，后续重复setSeed()相同种子不会产生相同的随机数
     */
    public static void func2() {
        int i;
        String strHexBytes;
        String str = "hh";
        byte[] salt = new byte[32];  // 用于存储生成的随机字节
        byte[] seed = new byte[16];  // 自定义种子

        // 初始化种子数组：0, 1, 2, ..., 15
        for (i = 0; i < 16; i++)
            seed[i] = (byte) i;

        SecureRandom secureRandom = new SecureRandom();
        System.out.println("使用的算法：" + secureRandom.getAlgorithm());

        // 在生成随机数之前先设置种子
        secureRandom.setSeed(seed);

        // 前面使用了setSeed
        secureRandom.nextBytes(salt);  // 获得随机数
        strHexBytes = bytesToHex(salt);  // 字节数组转为16进制字符串
        System.out.println("第1次（提前设置种子）：" + strHexBytes);  // 输出16进制的随机数

        // 后续两次都设置相同的种子
        for (i = 0; i < 2; i++) {
            secureRandom.setSeed(seed);  // 再次设置相同的种子
            secureRandom.nextBytes(salt);  // 获得随机数
            strHexBytes = bytesToHex(salt);  // 字节数组转为16进制字符串
            System.out.println("第" + (i + 2) + "次（再次设置种子）：" + strHexBytes);  // 输出16进制的随机数
        }
        System.out.println("注意：setSeed()只是补充种子，不会重置生成器状态！\n");
    }
}
