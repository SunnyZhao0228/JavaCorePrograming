package cn.itedu.string;

/**
 * String类常用的方法有这些
 * charAt, concat, contains, endsWith,equals,
 * equalsIgnoreCase, hashCode, indexOf, length, matches, replace,
 * replaceAll, split, startsWith, subString, trim, valueOf
 * @author zhaoqw
 * @date 2022/07/29
 */
public class StringAppendTest {
  public static void main(String[] args) {
    String str = "123;435;675;888;abc";
    System.out.println(str.charAt(0)); // 返回第一个元素
    System.out.println(str.indexOf(";")); // 返回第一个;的位置
    System.out.println(str.concat("abcdefg")); // 连接 abcdefg
    System.out.println(str.contains("abc")); // 是否包含abc
    System.out.println(str.endsWith("888")); // 是否以888结尾
    System.out.println(str.startsWith("123")); // 是否以123开始
    System.out.println(str.equals("000")); // 是否是000
    System.out.println(str.equalsIgnoreCase("123;435;675;888;ABC"));
    System.out.println(str.length());
    System.out.println(str.substring(3,10));
    System.out.println(str.replace('a','A'));
    System.out.println(str.replaceAll("1","A"));

    String s1 = "123456?123456";
    String s2 = s1.replace("?", "a");
    String s3 = s1.replaceAll("[?]", "a");
    System.out.println(s1);
    System.out.println(s2);
    System.out.println(s3);

  }
}
