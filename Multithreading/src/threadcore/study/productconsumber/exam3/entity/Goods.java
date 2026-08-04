package threadcore.study.productconsumber.exam3.entity;

import java.util.Random;

public class Goods implements IGoods{
    private String id;
    private String name;

    public Goods(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public static Goods produceOne() {
        Random random = new Random();
        String id = String.valueOf(random.nextInt(100000000));
        Goods goods = new Goods(id, "商品" + id);
        return goods;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Goods{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
