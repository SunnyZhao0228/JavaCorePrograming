package cn.itedu.array;

import java.util.Arrays;

/**
 * @author zhaoqw
 * @date 2025/7/8
 */
public class Num2Rmb {
    private String[] hanArr = {"零", "壹", "贰", "叁", "肆",
            "伍", "陆", "柒", "捌", "玖"};
    private String[] unitArr = {"十", "百", "千"};

    /**
     * 将一个浮点数分解成整数部分和小数部分
     *
     * @param num 需要被分解的浮点数
     * @return 分解出来的整数部分和小数部分。第一个数组元素是整数部分，第二个数组元素是小数部分
     */
    private String[] divide(double num) {
        // 将一个浮点数强制类型转换为long类型，即得到它的整数部分
        var zheng = (long) num;
        // 浮点数减去整数部分，得到小数部分，小数部分乘以100后再取整得到2位小数
        var xiao = Math.round((num - zheng) * 100);
        // 下面用了两种方法把整数转换为字符串
        return new String[]{zheng + "", String.valueOf(xiao)};
    }

    /**
     * 将一个4位的数字字符串变成汉字字符串
     *
     * @param numStr 需要被转换的4位的数字字符串
     * @return 4位的数字字符串被转换成汉字字符串
     */
    private String toHanStr(String numStr) {
        var result = "";
        int numLen = numStr.length();
        // 依次遍历数字字符串的每一位数字
        for (var i = 0; i < numLen; i++) {
            // 把char类型的数字转换成int类型的数字。由于它们的ASCII码值恰好相差48
            // 因此把char类型的数字减去48得到int类型的数字，例如，'4’被转换成4
            var num = numStr.charAt(i) - 48;
            // 如果不是最后一位数字，而且数字不是0，则需要添加单位（千、百、十）
            if (i != numLen - 1 && num != 0) {
                result += hanArr[num] + unitArr[numLen - 2 - i];
            }
            // 否则不要添加单位
            else {
                result += hanArr[num];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        var nr = new Num2Rmb();
        // 测试把一个浮点数分解成整数部分和小数部分
        System.out.println(Arrays.toString(nr.divide(236711125.123)));
        // 测试把一个4位的数字字符串变成汉字字符串
        System.out.println(nr.toHanStr("6109"));
    }
}
