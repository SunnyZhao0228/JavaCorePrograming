package cn.zhaoqw.study.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author zhaoqw
 * @date 2022/08/03
 */
public class LoopLinkListTest {
  public static void main(String[] args) {
    System.out.println("===========遍历方法=============");
    LinkedList<Integer> as = new LinkedList<Integer>();
    for (int i=0; i<100000; i++)
    {
      as.add(i);
    }
    traverseByIterator(as);
    traverseByIndex(as);
    traverseByFor(as);
  }
  public static void traverseByIterator(LinkedList<Integer> al)
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

  public static void traverseByIndex(LinkedList<Integer> al) {
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

  public static void traverseByFor(LinkedList<Integer> al) {
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
