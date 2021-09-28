package cn.zhaoqw.study.iterator.demo2;

import java.util.*;
public class ConcurrentTester{
  public static void main(String args[]){
     final int size=1000;
     final Set<Integer> set=new HashSet<Integer>();
     for(int i=0;i<size;i++)
       set.add(i);

     Thread reader=new Thread(){  //遍历集合
       public void run(){
         for(Integer i: set){			//for...each语法，通过迭代器进行遍历
           System.out.println(i);  //抛出ConcurrentModificationException   
//           yield();
         }
       }
     };

     Thread remover=new Thread(){  
       public void run(){
          for(int i=0;i<size;i++){
            set.remove(i);  //删除集合中的元素
//            yield();
          }
       }
     };

     reader.start();
     remover.start();
  }
}


