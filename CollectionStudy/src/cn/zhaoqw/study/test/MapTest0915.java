package cn.zhaoqw.study.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-09-15 10:54]
 */
public class MapTest0915 {
    public static void main(String[] args) {
        String words = load("englishText.txt");
        Map<String, Integer> wordCount = getWordCount(words);
        wordCount.forEach((k,v) -> System.out.println(k + ": " + v));
    }


    /**
     * 获取文章中的单词，使用Map的key保存单词，value保存单词数量
     * @param str
     * @return
     */
    public static Map<String,Integer> getWordCount(String str) {
        Map<String,Integer> map = new HashMap<>();
        str = replaceSymbol(str);
        String[] words = str.strip().toLowerCase().split(" ");
        for (int i = 0; i < words.length; i++) {
            if (!map.containsKey(words[i])) {
                map.put(words[i],1);
            }else {
                map.put(words[i],map.get(words[i]) + 1);
            }
        }

        return map;
    }

    /**
     * 去除文章中所有的标点符号
     * @param str
     * @return
     */
    public static String replaceSymbol(String str) {
        str = str.replaceAll("[^A-Za-z]", " ");
        str = str.replace("  "," ");
        str = str.replace("  "," ");
        str = str.replace("  "," ");
        return str;
    }


    /**
     * 在给定的字符串中，用新的字符替换所有旧的字符
     *
     * @param string 给定的字符串
     * @param oldchar 旧的字符
     * @param newchar 新的字符
     * @return 替换后的字符串
     */
    public static String replace(String string, char oldchar, char newchar) {
        char chars[] = string.toCharArray();
        for (int w = 0; w < chars.length; w++) {
            if (chars[w] == oldchar) {
                chars[w] = newchar;
                break;
            }
        }
        return new String(chars);
    }

    /**
     * 读取文件内容，转换成字符串
     * @param path 文件路径
     * @return
     */
    public static String load(String path) {
        BufferedReader reader = null;
        StringBuilder builder = null;
        try {
            reader = new BufferedReader(new FileReader(path));
            builder = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                builder.append(line + " ");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return builder.toString();
    }
}
