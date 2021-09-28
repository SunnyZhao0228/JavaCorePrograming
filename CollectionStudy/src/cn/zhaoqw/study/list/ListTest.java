package cn.zhaoqw.study.list;

import java.util.Arrays;
import java.util.List;

public class ListTest {

    public static void main(String[] args) {
        String[] strs = {"a","b","c","d"};

        List<String> list = Arrays.asList(strs); 		//不可变列表


        list.forEach(System.out::println);
        String s = list.get(1);
        System.out.println(s);

        list.set(1,"s");
        System.out.println(list.get(1));

        //用Arrays创建的List不能增加元素,删除元素
        list.add("e");

        list.forEach(System.out::println);

    }

}
