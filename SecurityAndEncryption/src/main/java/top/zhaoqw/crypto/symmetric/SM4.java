package top.zhaoqw.crypto.symmetric;

import java.util.Arrays;

/**
 * SM4分组加密算法实现
 * SM4是中国国家密码管理局于2012年3月21日发布的商用密码算法
 * 算法公开,分组长度和密钥长度均为128bit(16字节)
 *
 * @author zhaoqw
 * @version 1.0
 * @date 2025/12/19
 */
public class SM4 {
    private static final int ENCRYPT = 1;  // 加密标识
    private static final int DECRYPT = 0;  // 解密标识
    public static final int ROUND = 32; // 32轮
    private static final int BLOCK = 16; // 分组长度

    // S盒：用于非线性变换的查找表
    private byte[] Sbox = {
            (byte) 0xd6, (byte) 0x90, (byte) 0xe9, (byte) 0xfe, (byte) 0xcc, (byte) 0xe1, 0x3d, (byte) 0xb7, 0x16, (byte) 0xb6, 0x14, (byte) 0xc2, 0x28, (byte) 0xfb, 0x2c, 0x05,
            0x2b, 0x67, (byte) 0x9a, 0x76, 0x2a, (byte) 0xbe, 0x04, (byte) 0xc3, (byte) 0xaa, 0x44, 0x13, 0x26, 0x49, (byte) 0x86, 0x06, (byte) 0x99,
            (byte) 0x9c, 0x42, 0x50, (byte) 0xf4, (byte) 0x91, (byte) 0xef, (byte) 0x98, 0x7a, 0x33, 0x54, 0x0b, 0x43, (byte) 0xed, (byte) 0xcf, (byte) 0xac, 0x62,
            (byte) 0xe4, (byte) 0xb3, 0x1c, (byte) 0xa9, (byte) 0xc9, 0x08, (byte) 0xe8, (byte) 0x95, (byte) 0x80, (byte) 0xdf, (byte) 0x94, (byte) 0xfa, 0x75, (byte) 0x8f, 0x3f, (byte) 0xa6,
            0x47, 0x07, (byte) 0xa7, (byte) 0xfc, (byte) 0xf3, 0x73, 0x17, (byte) 0xba, (byte) 0x83, 0x59, 0x3c, 0x19, (byte) 0xe6, (byte) 0x85, 0x4f, (byte) 0xa8,
            0x68, 0x6b, (byte) 0x81, (byte) 0xb2, 0x71, 0x64, (byte) 0xda, (byte) 0x8b, (byte) 0xf8, (byte) 0xeb, 0x0f, 0x4b, 0x70, 0x56, (byte) 0x9d, 0x35,
            0x1e, 0x24, 0x0e, 0x5e, 0x63, 0x58, (byte) 0xd1, (byte) 0xa2, 0x25, 0x22, 0x7c, 0x3b, 0x01, 0x21, 0x78, (byte) 0x87,
            (byte) 0xd4, 0x00, 0x46, 0x57, (byte) 0x9f, (byte) 0xd3, 0x27, 0x52, 0x4c, 0x36, 0x02, (byte) 0xe7, (byte) 0xa0, (byte) 0xc4, (byte) 0xc8, (byte) 0x9e,
            (byte) 0xea, (byte) 0xbf, (byte) 0x8a, (byte) 0xd2, 0x40, (byte) 0xc7, 0x38, (byte) 0xb5, (byte) 0xa3, (byte) 0xf7, (byte) 0xf2, (byte) 0xce, (byte) 0xf9, 0x61, 0x15, (byte) 0xa1,
            (byte) 0xe0, (byte) 0xae, 0x5d, (byte) 0xa4, (byte) 0x9b, 0x34, 0x1a, 0x55, (byte) 0xad, (byte) 0x93, 0x32, 0x30, (byte) 0xf5, (byte) 0x8c, (byte) 0xb1, (byte) 0xe3,
            0x1d, (byte) 0xf6, (byte) 0xe2, 0x2e, (byte) 0x82, 0x66, (byte) 0xca, 0x60, (byte) 0xc0, 0x29, 0x23, (byte) 0xab, 0x0d, 0x53, 0x4e, 0x6f,
            (byte) 0xd5, (byte) 0xdb, 0x37, 0x45, (byte) 0xde, (byte) 0xfd, (byte) 0x8e, 0x2f, 0x03, (byte) 0xff, 0x6a, 0x72, 0x6d, 0x6c, 0x5b, 0x51,
            (byte) 0x8d, 0x1b, (byte) 0xaf, (byte) 0x92, (byte) 0xbb, (byte) 0xdd, (byte) 0xbc, 0x7f, 0x11, (byte) 0xd9, 0x5c, 0x41, 0x1f, 0x10, 0x5a, (byte) 0xd8,
            0x0a, (byte) 0xc1, 0x31, (byte) 0x88, (byte) 0xa5, (byte) 0xcd, 0x7b, (byte) 0xbd, 0x2d, 0x74, (byte) 0xd0, 0x12, (byte) 0xb8, (byte) 0xe5, (byte) 0xb4, (byte) 0xb0,
            (byte) 0x89, 0x69, (byte) 0x97, 0x4a, 0x0c, (byte) 0x96, 0x77, 0x7e, 0x65, (byte) 0xb9, (byte) 0xf1, 0x09, (byte) 0xc5, 0x6e, (byte) 0xc6, (byte) 0x84,
            0x18, (byte) 0xf0, 0x7d, (byte) 0xec, 0x3a, (byte) 0xdc, 0x4d, 0x20, 0x79, (byte) 0xee, 0x5f, 0x3e, (byte) 0xd7, (byte) 0xcb, 0x39, 0x48
    };

    // 系统参数CK：用于密钥扩展算法
    private int[] CK = {
            0x00070e15, 0x1c232a31, 0x383f464d, 0x545b6269,
            0x70777e85, 0x8c939aa1, 0xa8afb6bd, 0xc4cbd2d9,
            0xe0e7eef5, 0xfc030a11, 0x181f262d, 0x343b4249,
            0x50575e65, 0x6c737a81, 0x888f969d, 0xa4abb2b9,
            0xc0c7ced5, 0xdce3eaf1, 0xf8ff060d, 0x141b2229,
            0x30373e45, 0x4c535a61, 0x686f767d, 0x848b9299,
            0xa0a7aeb5, 0xbcc3cad1, 0xd8dfe6ed, 0xf4fb0209,
            0x10171e25, 0x2c333a41, 0x484f565d, 0x646b7279
    };

    /**
     * 循环左移操作
     * @param x 待移位的值
     * @param y 移位位数
     * @return 移位后的值
     */
    private int Rotl(int x, int y) {
        return x << y | x >>> (32 - y);
    }

    /**
     * 非线性变换τ：使用S盒进行字节替换
     * @param A 输入的32位整数
     * @return S盒变换后的32位整数
     */
    private int ByteSub(int A) {
        return (Sbox[A >>> 24 & 0xFF] & 0xFF) << 24 | (Sbox[A >>> 16 & 0xFF] & 0xFF) << 16 | (Sbox[A >>> 8 & 0xFF] & 0xFF) << 8 | (Sbox[A & 0xFF] & 0xFF);
    }

    /**
     * 线性变换L：用于加密轮函数
     * @param B 输入的32位整数
     * @return 线性变换后的32位整数
     */
    private int L1(int B) {
        return B ^ Rotl(B, 2) ^ Rotl(B, 10) ^ Rotl(B, 18) ^ Rotl(B, 24);
    }

    /**
     * 线性变换L'：用于密钥扩展算法
     * @param B 输入的32位整数
     * @return 线性变换后的32位整数
     */
    private int L2(int B) {
        return B ^ Rotl(B, 13) ^ Rotl(B, 23);
    }

    /**
     * SM4加密/解密核心算法
     * @param Input 输入数据(16字节)
     * @param Output 输出数据(16字节)
     * @param rk 轮密钥数组(32个)
     */
    void SMS4Crypt(byte[] Input, byte[] Output, int[] rk) {
        int r, mid, x0, x1, x2, x3;
        int[] x = new int[4];
        int[] tmp = new int[4];
        for (int i = 0; i < 4; i++) {
            tmp[0] = Input[0 + 4 * i] & 0xff;
            tmp[1] = Input[1 + 4 * i] & 0xff;
            tmp[2] = Input[2 + 4 * i] & 0xff;
            tmp[3] = Input[3 + 4 * i] & 0xff;
            x[i] = tmp[0] << 24 | tmp[1] << 16 | tmp[2] << 8 | tmp[3];
        }
        for (r = 0; r < 32; r += 4) {
            mid = x[1] ^ x[2] ^ x[3] ^ rk[r + 0];
            mid = ByteSub(mid);
            x[0] = x[0] ^ L1(mid);   // x4

            mid = x[2] ^ x[3] ^ x[0] ^ rk[r + 1];
            mid = ByteSub(mid);
            x[1] = x[1] ^ L1(mid);    // x5

            mid = x[3] ^ x[0] ^ x[1] ^ rk[r + 2];
            mid = ByteSub(mid);
            x[2] = x[2] ^ L1(mid);    // x6

            mid = x[0] ^ x[1] ^ x[2] ^ rk[r + 3];
            mid = ByteSub(mid);
            x[3] = x[3] ^ L1(mid);    // x7
        }

        // 反序变换：将X32,X33,X34,X35反序变换为Y0,Y1,Y2,Y3
        for (int j = 0; j < 16; j += 4) {
            Output[j] = (byte) (x[3 - j / 4] >>> 24 & 0xFF);
            Output[j + 1] = (byte) (x[3 - j / 4] >>> 16 & 0xFF);
            Output[j + 2] = (byte) (x[3 - j / 4] >>> 8 & 0xFF);
            Output[j + 3] = (byte) (x[3 - j / 4] & 0xFF);
        }
    }

    /**
     * SM4密钥扩展算法
     * @param Key 原始密钥(16字节)
     * @param rk 轮密钥数组(输出32个轮密钥)
     * @param CryptFlag 加密/解密标识(ENCRYPT或DECRYPT)
     */
    private void SMS4KeyExt(byte[] Key, int[] rk, int CryptFlag) {
        int r, mid;
        int[] x = new int[4];
        int[] tmp = new int[4];
        for (int i = 0; i < 4; i++) {
            tmp[0] = Key[0 + 4 * i] & 0xFF;
            tmp[1] = Key[1 + 4 * i] & 0xff;
            tmp[2] = Key[2 + 4 * i] & 0xff;
            tmp[3] = Key[3 + 4 * i] & 0xff;
            x[i] = tmp[0] << 24 | tmp[1] << 16 | tmp[2] << 8 | tmp[3];
        }
        x[0] ^= 0xa3b1bac6;
        x[1] ^= 0x56aa3350;
        x[2] ^= 0x677d9197;
        x[3] ^= 0xb27022dc;
        for (r = 0; r < 32; r += 4) {
            mid = x[1] ^ x[2] ^ x[3] ^ CK[r + 0];
            mid = ByteSub(mid);
            rk[r + 0] = x[0] ^= L2(mid);        // rk0=K4

            mid = x[2] ^ x[3] ^ x[0] ^ CK[r + 1];
            mid = ByteSub(mid);
            rk[r + 1] = x[1] ^= L2(mid);        // rk1=K5

            mid = x[3] ^ x[0] ^ x[1] ^ CK[r + 2];
            mid = ByteSub(mid);
            rk[r + 2] = x[2] ^= L2(mid);        // rk2=K6

            mid = x[0] ^ x[1] ^ x[2] ^ CK[r + 3];
            mid = ByteSub(mid);
            rk[r + 3] = x[3] ^= L2(mid);        // rk3=K7
        }

        // 解密时轮密钥使用顺序rk31,rk30,...,rk0
        if (CryptFlag == DECRYPT) {
            for (r = 0; r < 16; r++) {
                mid = rk[r];
                rk[r] = rk[31 - r];
                rk[31 - r] = mid;
            }
        }
    }

    /**
     * SM4加密接口
     * @param key 密钥(16字节)
     * @param in 明文输入(16字节)
     * @param out 密文输出(16字节)
     * @return 0表示成功
     */
    public int SM4_Encrypt(byte[] key, byte[] in, byte[] out) {
        int[] round_key = new int[ROUND];
        SMS4KeyExt(key, round_key, 1);
        SMS4Crypt(in, out, round_key);
        return 0;
    }

    /**
     * SM4解密接口
     * @param key 密钥(16字节)
     * @param in 密文输入(16字节)
     * @param out 明文输出(16字节)
     * @return 0表示成功
     */
    public int SM4_Decrypt(byte[] key, byte[] in, byte[] out) {
        int[] round_key = new int[ROUND];
        SMS4KeyExt(key, round_key, 0);
        SMS4Crypt(in, out, round_key);
        return 0;
    }


    /**
     * SM4 ECB模式加密/解密
     * ECB(Electronic Codebook)：电子密码本模式，每个分组独立加密
     * @param in 输入数据
     * @param out 输出数据
     * @param length 数据长度(必须是16的倍数)
     * @param key 密钥(16字节)
     * @param enc 加密/解密标识(ENCRYPT或DECRYPT)
     * @return 0表示成功，-1表示参数错误
     */
    int sm4ecb(byte[] in, byte[] out, int length, byte[] key, int enc) {
        int n, len = length, point = 0;
        byte[] input = new byte[16];
        byte[] output = new byte[16];

        // 判断参数是否为空，以及长度是否为16的倍数
        if ((in == null) || (out == null) || (key == null) || (length % BLOCK != 0))
            return -1;

        if ((ENCRYPT != enc) && (DECRYPT != enc))  // 判断需要进行加密还是解密
            return -1;

        // 判断数据长度是否大于分组大小16字节，满足条件就一组一组处理
        while (len >= BLOCK) {
            input = Arrays.copyOfRange(in, point, point + 16);
            if (ENCRYPT == enc)
                SM4_Encrypt(key, in, output);
            else
                SM4_Decrypt(key, in, output);
            System.arraycopy(output, 0, out, point, BLOCK);
            len -= BLOCK;   // 每处理一个分组，长度就要减去16
            point += BLOCK;
        }
        return 0;
    }


    /**
     * SM4 CBC模式加密/解密
     * CBC(Cipher Block Chaining)：密码分组链接模式，每个分组与前一个密文分组进行异或后再加密
     * @param in 输入数据
     * @param out 输出数据
     * @param length 数据长度(必须是16的倍数)
     * @param key 密钥(16字节)
     * @param ivec 初始化向量IV(16字节)
     * @param enc 加密/解密标识(ENCRYPT或DECRYPT)
     */
    void sm4cbc(byte[] in, byte[] out, int length, byte[] key, byte[] ivec, int enc) {
        int n, point = 0;
        byte[] input = new byte[16];
        byte[] output = new byte[16];
        int len = length;
        byte[] tmp = new byte[BLOCK];
        byte[] iv = ivec;
        byte[] iv_tmp = new byte[BLOCK];

        // 判断参数是否为空以及长度是否为16的倍数
        if ((in == null) || (out == null) || (key == null) || (ivec == null) || (length % BLOCK != 0))
            return;

        if ((ENCRYPT != enc) && (DECRYPT != enc)) // 判断需要进行加密还是解密
            return;

        if (ENCRYPT == enc) // 如果是加密
        {
            while (len >= BLOCK) // 对大于16字节的数据要进行循环分组处理
            {
                // 加密时，第一个分组块与初始化向量IV进行异或运算，然后用key进行加密。
                // 以后每个分组块都与前一个密文分组进行异或，然后再用key进行加密。
                // 前一个密文分组进行异或，即当作了一个iv
                input = Arrays.copyOfRange(in, point, point + 16);
                for (n = 0; n < BLOCK; ++n)
                    output[n] = (byte) (input[n] ^ iv[n]);
                SM4_Encrypt(key, output, output); // 用key进行加密
                iv = output; // 保存当前密文块，供下一个循环中和明文进行异或运算
                System.arraycopy(output, 0, out, point, BLOCK); // 偏移量数组指针，以便下次的结果
                len -= BLOCK; // 减去已经完成的字节数
                point += BLOCK; // 偏移量数组指针，指向未加密的数据块头
            }
        } else if (in != out) // in和out指向同一个数组
        {
            while (len >= BLOCK) // 开始循环分组处理
            {
                input = Arrays.copyOfRange(in, point, point + BLOCK);
                SM4_Decrypt(key, input, output);
                for (n = 0; n < BLOCK; ++n)
                    output[n] ^= iv[n];
                iv = Arrays.copyOfRange(input, 0, BLOCK);

                System.arraycopy(output, 0, out, point, BLOCK);
                len -= BLOCK; // 减去已经完成的字节数
                point += BLOCK; // 偏移量数组指针，指向未解密的数据块头
            }
        } else // 如in和out指向同一个数组
        {
            iv_tmp = Arrays.copyOfRange(ivec, point, point + BLOCK);
            while (len >= BLOCK) {
                tmp = Arrays.copyOfRange(in, point, point + BLOCK); // 暂存本轮分组的密文，因为in要被解密覆盖了。
                input = Arrays.copyOfRange(in, point, point + BLOCK);
                SM4_Decrypt(key, input, output);
                for (n = 0; n < BLOCK; ++n)
                    output[n] ^= iv_tmp[n];
                iv_tmp = Arrays.copyOfRange(tmp, point, point + BLOCK);
                System.arraycopy(output, 0, out, point, BLOCK);
                len -= BLOCK;
                point += BLOCK; // 偏移量数组指针，指向未解密的数据块头
            }
        }
    }


    /**
     * SM4 CFB模式加密/解密
     * CFB(Cipher FeedBack)：密码反馈模式，将前一个密文分组作为加密算法的输入
     * @param in 输入数据
     * @param out 输出数据
     * @param length 数据长度
     * @param key 密钥(16字节)
     * @param ivec 初始化向量IV(16字节)
     * @param enc 加密/解密标识(ENCRYPT或DECRYPT)
     */
    void sm4cfb(byte[] in, byte[] out, int length, byte[] key, byte[] ivec, int enc) {
        int i = 0, n = 0;
        int l = length;
        byte c;
        byte[] iv = new byte[BLOCK];

        if ((in == null) || (out == null) || (key == null) || (ivec == null))
            return;

        if ((ENCRYPT != enc) && (DECRYPT != enc))
            return;


        iv = Arrays.copyOfRange(ivec, 0, BLOCK);


        if (enc == ENCRYPT) {
            while (l > 0) {
                l--;
                if (n == 0) {
                    SM4_Encrypt(key, iv, iv);
                }
                out[i] = (byte) (in[i] ^ iv[n]);
                iv[n] = out[i];
                i++;

                n = (n + 1) % BLOCK;
            }
        } else // 解密
        {
            while (l > 0) {
                l--;
                if (n == 0) {
                    SM4_Encrypt(key, iv, iv);
                }
                c = in[i];
                out[i] = (byte) (in[i] ^ iv[n]);
                i++;
                iv[n] = c;
                n = (n + 1) % BLOCK;
            }
        }
    }


    /**
     * SM4 OFB模式加密/解密
     * OFB(Output FeedBack)：输出反馈模式，将加密算法的输出作为下一次加密的输入
     * OFB模式加密和解密使用相同的过程
     * @param in 输入数据
     * @param out 输出数据
     * @param length 数据长度
     * @param key 密钥(16字节)
     * @param ivec 初始化向量IV(16字节)
     */
    void sm4ofb(byte[] in, byte[] out, int length, byte[] key, byte[] ivec) {
        int i = 0, n = 0;
        int l = length;
        byte[] iv = new byte[BLOCK];

        if ((in == null) || (out == null) || (key == null) || (ivec == null))
            return;

        iv = Arrays.copyOfRange(ivec, 0, BLOCK);

        while (l > 0) {
            l--;
            if (n == 0) {
                SM4_Encrypt(key, iv, iv);
            }
            out[i] = (byte) (in[i] ^ iv[n]);
            i++;
            n = (n + 1) % BLOCK;
        }
    }
}