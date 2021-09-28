package cn.zhaoqw.study.map;

import java.util.*;

public class MapTest {
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>();
        map.put("孟鹤",20000);
        map.put("牛叠为",30000);

        map.forEach((key,value) -> {
            System.out.println(key + "==" + value);
        });


        Set<Map.Entry<String, Integer>> entries = map.entrySet();


        Set<String> strings = map.keySet();

        System.out.println("===============");
        Map<String, Integer> map1 = Collections.unmodifiableMap(map);
        map1.forEach((k,v) -> System.out.println(k + "==" + v));

        //map1.put("郑重",30000);

        System.out.println("===============");
        map.put("任唱",20000);
        //有点像数据库中的视图
        map1.forEach((k,v) -> System.out.println(k + "==" + v));

        HashSet<String> set = new HashSet<>();
        set.add("aaa");
        set.add("bbb");
        set.add("ccc");
        set.add("ddd");
        Set<String> set1 = Collections.unmodifiableSet(set);
        set1.forEach(System.out::println);


    }
}
