package cn.zhaoqw.studygeneric.extendsdemo;

public class A implements Runnable {

 public static void main(String argv[]) {

  A a = new A();

  Thread t = new Thread(a);

  t.start();

 }



 public void run() {

  while(true) {

   try{

    Thread.sleep(1000);

   }catch(InterruptedException e){

    System.out.println(e.toString());

   }  

   System.out.println("looping while");

  }

 }

}