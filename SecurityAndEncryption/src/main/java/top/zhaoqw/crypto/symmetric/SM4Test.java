package top.zhaoqw.crypto.symmetric;


import java.util.Arrays;

/**
 * SM4算法测试类
 * 测试ECB、CBC、CFB、OFB四种工作模式的加密和解密功能
 * 
 * @author zhaoqw
 * @version 1.0
 * @date 2025/12/19
 */
public class SM4Test {

    private static final int SM4_BLOCK_SIZE = 16; // 分组长度(16字节)
    private static final int SM4_ENCRYPT = 1;  // 加密标识
    private static final int SM4_DECRYPT = 0;  // 解密标识

    /**
     * SM4 ECB模式测试
     * 测试电子密码本模式的加密和解密功能
     * @return 0表示测试完成
     */
    public static int sm4ecbcheck() {
        int i, len, ret = 0;
        // 测试密钥(128位)
        byte[] key = {0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef,
                (byte) 0xfe, (byte) 0xdc, (byte) 0xba, (byte) 0x98, 0x76, 0x54, 0x32, 0x10};
        // 测试明文(128位)
        byte[] plain = {0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef,
                (byte) 0xfe, (byte) 0xdc, (byte) 0xba, (byte) 0x98, 0x76, 0x54, 0x32, 0x10};
        // 期望的密文(128位)
        byte[] cipher = {0x68, 0x1e, (byte) 0xdf, 0x34, (byte) 0xd2, 0x06, (byte) 0x96, 0x5e,
                (byte) 0x86, (byte) 0xb3, (byte) 0xe9, 0x4f, 0x53, 0x6e, 0x42, 0x46};
        byte[] En_output = new byte[16];  // 加密输出
        byte[] De_output = new byte[16];  // 解密输出
        byte[] in = new byte[4096], out = new byte[4096], chk = new byte[4096];  // 批量测试缓冲区

        SM4 sm4 = new SM4();
        // 测试单个分组(16字节)的加密
        sm4.sm4ecb(plain, En_output, 16, key, SM4_ENCRYPT);
        if (Arrays.equals(En_output, cipher) == false)
            System.out.println("ecb enc(len=16) memcmp failed");
        else
            System.out.println("ecb enc(len=16) memcmp ok");

        // 测试单个分组(16字节)的解密
        sm4.sm4ecb(cipher, De_output, SM4_BLOCK_SIZE, key, SM4_DECRYPT);
        if (Arrays.equals(De_output, plain) == false)
            System.out.println("ecb dec(len=16) memcmp failed");
        else
            System.out.println("ecb dec(len=16) memcmp ok");

        // 测试不同长度的数据加密和解密(32, 64, 128, 256, 512, 1024, 2048, 4096字节)
        len = 32;
        for (i = 0; i < 8; i++) {
            Arrays.fill(in, 0, len, (byte) i);
            sm4.sm4ecb(in, out, len, key, SM4_ENCRYPT);
            sm4.sm4ecb(out, chk, len, key, SM4_DECRYPT);
            if (Arrays.equals(in, chk) == false)
                System.out.println("ecb enc/dec(len=" + len + ") memcmp failed");
            else
                System.out.println("ecb enc/dec(len=" + len + ") memcmp ok");
            len = 2 * len;
        }
        return 0;
    }


    /**
     * SM4 CBC模式测试
     * 测试密码分组链接模式的加密和解密功能
     * @return 0表示测试完成
     */
    public static int sm4cbccheck() {
        int i, len, ret = 0;
        // 密钥(128位)
        byte[] key = {0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef,
                (byte) 0xfe, (byte) 0xdc, (byte) 0xba, (byte) 0x98, 0x76, 0x54, 0x32, 0x10};
        // 初始化向量IV(128位)
        byte[] iv = {(byte) 0xeb, (byte) 0xee, (byte) 0xc5, 0x68, 0x58, (byte) 0xe6, (byte) 0x04, (byte) 0xd8,
                0x32, 0x7b, (byte) 0x9b, 0x3c, 0x10, (byte) 0xc9, 0x0c, (byte) 0xa7};
        // 明文(256位,两个分组)
        byte[] plain = {0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef,
                (byte) 0xfe, (byte) 0xdc, (byte) 0xba, (byte) 0x98, 0x76, 0x54, 0x32, 0x10,
                0x29, (byte) 0xbe, (byte) 0xe1, (byte) 0xd6, 0x52, 0x49, (byte) 0xf1, (byte) 0xe9,
                (byte) 0xb3, (byte) 0xdb, (byte) 0x87, 0x3e, 0x24, 0x0d, 0x06, 0x47};
        // 期望的密文(256位)
        byte[] cipher = {0x3f, 0x1e, 0x73, (byte) 0xc3, (byte) 0xdf, (byte) 0xd5, (byte) 0xa1, 0x32,
                (byte) 0x88, 0x2f, (byte) 0xe6, (byte) 0x9d, (byte) 0x99, 0x6c, (byte) 0xde, (byte) 0x93,
                0x54, (byte) 0x99, 0x09, 0x5d, (byte) 0xde, 0x68, (byte) 0x99, 0x5b,
                0x4d, 0x70, (byte) 0xf2, 0x30, (byte) 0x9f, 0x2e, (byte) 0xf1, (byte) 0xb7};

        byte[] En_output = new byte[32];  // 加密输出
        byte[] De_output = new byte[32];  // 解密输出
        byte[] in = new byte[4096], out = new byte[4096], chk = new byte[4096];  // 批量测试缓冲区

        SM4 sm4 = new SM4();
        // 测试32字节数据的CBC加密
        sm4.sm4cbc(plain, En_output, plain.length, key, iv, SM4_ENCRYPT);
        if (Arrays.equals(En_output, cipher) == false)
            System.out.println("cbc enc(len=32) memcmp failed");
        else
            System.out.println("cbc enc(len=32) memcmp ok");

        // 测试32字节数据的CBC解密
        sm4.sm4cbc(cipher, De_output, plain.length, key, iv, SM4_DECRYPT);
        if (Arrays.equals(De_output, plain) == false)
            System.out.println("cbc dec(len=32) memcmp failed");
        else
            System.out.println("cbc dec(len=32) memcmp ok");


        // 测试不同长度的数据加密和解密(32, 64, 128, 256, 512, 1024, 2048, 4096字节)
        len = 32;
        for (i = 0; i < 8; i++) {
            Arrays.fill(in, 0, len, (byte) i);
            sm4.sm4cbc(in, out, len, key, iv, SM4_ENCRYPT);
            sm4.sm4cbc(out, chk, len, key, iv, SM4_DECRYPT);
            if (Arrays.equals(in, chk) == false)
                System.out.println("cbc enc/dec(len=" + len + ") memcmp failed");
            else
                System.out.println("cbc enc/dec(len=" + len + ") memcmp ok");
            len = 2 * len;
        }
        return 0;
    }

    /**
     * SM4 CFB模式测试
     * 测试密码反馈模式的加密和解密功能
     * @return 0表示测试完成
     */
    public static int sm4cfbcheck() {
        int i, len, ret = 0;
        byte[] key =   { 0x01,0x23,0x45,0x67,(byte) 0x89,(byte) 0xab,(byte) 0xcd,(byte) 0xef,(byte) 0xfe,(byte) 0xdc,(byte) 0xba,(byte) 0x98,0x76,0x54,0x32,0x10 };//��Կ
        byte[] iv = { (byte)0xeb,(byte)0xee,(byte)0xc5,0x68,0x58,(byte)0xe6,(byte)0x04,(byte)0xd8,0x32,0x7b,(byte)0x9b,0x3c,0x10,(byte)0xc9,0x0c,(byte)0xa7 }; //��ʼ������
        byte[]  in=new byte[4096], out=new byte[4096], chk=new byte[4096];

        SM4 sm4 = new SM4();
        // 测试不同长度的数据加密和解密(16, 32, 64, 128, 256, 512, 1024, 2048, 4096字节)
        len = 16;
        for (i = 0; i < 9; i++) {
            Arrays.fill(in, 0, len, (byte) i);
            sm4.sm4cfb(in, out, len, key, iv, SM4_ENCRYPT);
            sm4.sm4cfb(out, chk, len, key, iv, SM4_DECRYPT);
            if (Arrays.equals(in, chk) == false)
                System.out.println("cfb enc/dec(len=" + len + ") memcmp failed");
            else
                System.out.println("cfb enc/dec(len=" + len + ") memcmp ok");
            len = 2 * len;
        }
        return 0;
    }

    /**
     * SM4 OFB模式测试
     * 测试输出反馈模式的加密和解密功能
     * @return 0表示测试完成
     */
    public static int sm4ofbcheck() {
        int i, len, ret = 0;
        byte[] key =   { 0x01,0x23,0x45,0x67,(byte) 0x89,(byte) 0xab,(byte) 0xcd,(byte) 0xef,(byte) 0xfe,(byte) 0xdc,(byte) 0xba,(byte) 0x98,0x76,0x54,0x32,0x10 };//��Կ
        byte[] iv = { (byte)0xeb,(byte)0xee,(byte)0xc5,0x68,0x58,(byte)0xe6,(byte)0x04,(byte)0xd8,0x32,0x7b,(byte)0x9b,0x3c,0x10,(byte)0xc9,0x0c,(byte)0xa7 }; //��ʼ������
        byte[]  in=new byte[4096], out=new byte[4096], chk=new byte[4096];
        len = 16;
        SM4 sm4 = new SM4();
        // 测试不同长度的数据加密和解密(16, 32, 64, 128, 256, 512, 1024, 2048, 4096字节)
        for (i = 0; i < 9; i++) {
            Arrays.fill(in, 0, len, (byte) i);
            sm4.sm4ofb(in, out, len, key, iv);
            sm4.sm4ofb(out, chk, len, key, iv);
            if (Arrays.equals(in, chk) == false)
                System.out.println("ofb enc/dec(len=" + len + ") memcmp failed");
            else
                System.out.println("ofb enc/dec(len=" + len + ") memcmp ok");
            len = 2 * len;
        }
        return 0;
    }



    /**
     * 主测试方法
     * 依次测试ECB、CBC、CFB、OFB四种模式，并进行性能测试
     * @param args 命令行参数
     */
    public static void main(String[] args) {

        System.out.println("----------ecb begin--------------");
        sm4ecbcheck();
        System.out.println("----------cbc begin--------------");
        sm4cbccheck();
        System.out.println("----------cfb begin--------------");
        sm4cfbcheck();
        System.out.println("----------ofb begin--------------");
        sm4ofbcheck();

        System.out.print("\n----------性能测试开始------------------");

        // 性能测试数据
        byte[] in = {0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef,
                (byte) 0xfe, (byte) 0xdc, (byte) 0xba, (byte) 0x98, 0x76, 0x54, 0x32, 0x10};
        byte[] key = {0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef,
                (byte) 0xfe, (byte) 0xdc, (byte) 0xba, (byte) 0x98, 0x76, 0x54, 0x32, 0x10};
        SM4 sm4 = new SM4();

        byte[] out = new byte[16];

        long starttime;



        // 执行100万次加密测试性能
        starttime = System.currentTimeMillis();
        for (int i = 1; i < 1000000; i++) {
            sm4.SM4_Encrypt(in, key, out);
            in = out;
        }
        sm4.SM4_Encrypt(in, key, out);
        System.out.println("\r100万次加密执行时间： " + (System.currentTimeMillis() - starttime) + "ms");
        System.out.println("加密结果：");
        for (int i = 0; i < 16; i++)
            System.out.print(Integer.toHexString(out[i] & 0xff));
    }
}

