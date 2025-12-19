package cn.itedu.array;

/**
 * @author zhaoqw
 * @date 2025/7/9
 */
public class CacheImmutable {

    private static int MAX_SIZE = 10;

    private static CacheImmutable[] cacheImmutable = new CacheImmutable[MAX_SIZE];

    // 记录缓存的实例在缓存池中的位置，cache[pos-1]是最新缓存的实例
    private static int pos = 0;

    private final String name;

    public CacheImmutable(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public static CacheImmutable valueOf(String name) {
        for (int i = 0; i < pos; i++) {
            if (cacheImmutable[i].getName().equals(name)) {
                return cacheImmutable[i];
            }
        }
        if (pos == MAX_SIZE) {
            cacheImmutable[0] = new CacheImmutable(name);
            pos = 1;
        } else {
            cacheImmutable[pos] = new CacheImmutable(name);
            pos++;
        }
        return cacheImmutable[pos - 1];
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() == obj.getClass()) {
            CacheImmutable other = (CacheImmutable) obj;
            return name.equals(other.name);
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        var c1 = CacheImmutable.valueOf("hello");
        var c2 = CacheImmutable.valueOf("hello");
        // 下面的代码将输出true
        System.out.println(c1 == c2);

    }
}
