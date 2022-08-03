package cn.zhaoqw.study.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-09-15 10:46]
 */
public class ListTest0915 {
    public static void main(String[] args) {
        List<Double> list = new ArrayList<>();
        Random random = new Random(47);

        for (int i = 0; i < 10; i++) {
            double v = random.nextDouble()*10;
            list.add(v);
        }
        System.out.println(list);
        Double aDouble1 = list.get(4);
        System.out.println(aDouble1);

        System.out.println("======================");
        Double value1 = list.get(random.nextInt(10));
        Double value2 = list.get(random.nextInt(10));
        int index1 = list.indexOf(value1);
        int index2 = list.indexOf(value2);
        System.out.println(value1 + "------" + index1);
        System.out.println(value2 + "------" + index2);


        list.remove(2);
        System.out.println(list);
        ArrayList<Integer> al = new ArrayList<Integer>();
        al.add(3);
        al.add(2);
        al.add(1);
        al.add(4);
        al.add(5);
        al.add(6);
        al.add(new Integer(6));

        System.out.print("The third element is  ");
        System.out.println(al.get(3));
        al.remove(3);  //删除第四个元素，后面元素往前挪动
        al.add(3, 9);  //将9插入到第4个元素，后面元素往后挪动

        System.out.println("======遍历方法=============");

        ArrayList<Integer> as = new ArrayList<Integer>(100000);
        for (int i=0; i<100000; i++)
        {
            as.add(i);
        }
        traverseByIterator(as);
        traverseByIndex(as);
        traverseByFor(as);
    }
    public static void traverseByIterator(ArrayList<Integer> al)
    {
        long startTime = System.nanoTime();
        System.out.println("============迭代器遍历==============");
        Iterator<Integer> iter1 = al.iterator();
        while(iter1.hasNext()){
            iter1.next();
        }
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println(duration + "纳秒");
    }
    public static void traverseByIndex(ArrayList<Integer> al)
    {
        long startTime = System.nanoTime();
        System.out.println("============随机索引值遍历==============");
        for(int i=0;i<al.size();i++)
        {
            al.get(i);
        }
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println(duration + "纳秒");
    }
    public static void traverseByFor(ArrayList<Integer> al)
    {
        long startTime = System.nanoTime();
        System.out.println("============for循环遍历==============");
        for(Integer item : al)
        {
            ;
        }
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println(duration + "纳秒");
    }
}
