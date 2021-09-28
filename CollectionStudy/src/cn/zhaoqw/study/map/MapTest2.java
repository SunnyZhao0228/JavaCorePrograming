package cn.zhaoqw.study.map;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class MapTest2 {
    public static void main(String[] args) {
        List<String> aa = List.of("aa", "bb");

        Set<String> aa1 = Set.of("aa", "bb", "cc");

        Map<String, Integer> map1 = Map.ofEntries(Map.entry("张三丰", 60), Map.entry("风清扬", 55));
        Map<String, Integer> map2 = Map.of("张三丰", 60, "风清扬", 66);


        TreeSet set = new TreeSet();
    }
}
