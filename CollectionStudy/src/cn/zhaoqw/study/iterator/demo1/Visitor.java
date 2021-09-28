package cn.zhaoqw.study.iterator.demo1;

import java.util.*;
public class Visitor{

    public static void print(Collection<? extends Object> c){
        Iterator<? extends Object> it=c.iterator();
        //遍历集合中的所有元素
        while(it.hasNext()){
            Object element=it.next();  //取出集合中的一个元素
            System.out.println(element);
        }
    }

    public static void printWithForEach(Collection<? extends Object> c){
        for(Object element: c) //用foreach语句来遍历集合
            System.out.println(element);
    }

    public static void main(String args[]){
        Set<String> set=new HashSet<String>();
        set.add("Tom");
        set.add("Mary");
        set.add("Jack");

        Iterator<? extends Object> it =set.iterator();

        while(it.hasNext()) {
            System.out.println(it.next());
        }

        while(it.hasNext()) {
            System.out.println(it.next());
            it.remove();
        }

//   for(String s:set) {
//	   System.out.println(s);
//   }
//
//   set.forEach(  (e) -> System.out.println(e));
//
//   set.forEach( System.out::println);
//
//    print(set);
//
//    List<String> list=new ArrayList<String>();
//    list.add("Linda");
//    list.add("Mary");
//    list.add("Rose");
//    print(list);
//
//    Queue<String> queue=new ArrayDeque<String>();
//    queue.add("Tom");
//    queue.add("Mike");
//    queue.add("Jack");
//    print(queue);
//
//    Map<String,String> map=new HashMap<String,String>();
//    map.put("M","男");
//    map.put("F","女");
//    print(map.entrySet());
//
//    printWithForEach(set);
//    printWithForEach(list);
//    printWithForEach(queue);
//    printWithForEach(map.entrySet());

    }
}