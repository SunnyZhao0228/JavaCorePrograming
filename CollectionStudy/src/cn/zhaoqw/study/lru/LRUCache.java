package cn.zhaoqw.study.lru;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Least Recently Used的缩写，即最近最少使用
 * 存在时间较长的数据的一直没有用，那这个数据用到的概率就比较低
 * @author zhaoqw
 * @date 2023/01/02
 */
public class LRUCache<K,V> implements Iterable<K> {
    private int MAX = 3;
    LinkedHashMap<K, V> cache = new LinkedHashMap<>();

    public void cache(K key, V value) {
        if(cache.containsKey(key)) {
            cache.remove(key);
        } else if (cache.size() >= MAX){
            Iterator<K> iterator = cache.keySet().iterator();
            K first = iterator.next();
            cache.remove(first);
        }
        cache.put(key, value);
    }
    @Override
    public Iterator<K> iterator() {
        Iterator<K> it = cache.keySet().iterator();
        return new Iterator<K>() {
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public K next() {
                return it.next();
            }
        };
    }

    public static void main(String[] args) {
        LRUCache<String, Integer> lruCache = new LRUCache<>();
        lruCache.cache("A", 1);
        lruCache.cache("B", 2);
        lruCache.cache("C", 3);
        lruCache.cache("D", 4);
        lruCache.cache("C", 10);
        System.out.println("Leave <-" + StreamSupport.stream(lruCache.spliterator(), false)
                .map(x -> x.toString()).collect(Collectors.joining("<-")));
    }
}
