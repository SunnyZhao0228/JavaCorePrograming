package cn.zhaoqw.studygeneric.demo1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Holder4<K,V> {
    private K key;
    private V value;

    public Holder4() {
    }

    public Holder4(K key, V value) {
        this.key = key;
        this.value = value;
    }


    public Map get() {
        Map map = new HashMap();
        map.put(key,value);
        return map;
    }


    public static void main(String[] args) {
        Holder4<Integer,String> h = new Holder4(1001,"zqw");
        Map map = h.get();
        Set set = map.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            System.out.println(next + "=" + map.get(next));
        }

        Set set1 = map.entrySet();
        for (Object o : set1) {
            System.out.println(o);
        }


        HashMap<Integer,String> map2 = new HashMap<>();
        map2.put(111,"aaa");
        map2.put(222,"aaa");
        map2.put(333,"aaa");
        map2.put(444,"aaa");
        Set entrySet = map.entrySet();
        map2.forEach((integer, s) -> {
            System.out.println(integer + "= " + s);
        });

    }
}
