package cn.zhaoqw.study.set.treeset;

import java.util.*;
public class CustomerTester{
  public static void main(String args[]){
    Set<Customer> set=new TreeSet<Customer>(new Comparator<Customer>() {

		@Override
		public int compare(Customer o1, Customer o2) {
			
			return o1.getName().compareTo(o2.getName()) == 0 ? o1.getAge()-o2.getAge() : o1.getName().compareTo(o2.getName()) ;
		}
    });			//Comparator匿名内部类对象，指定比较器
    
//    Customer c1 = new Customer("Tom",15);
    	

    set.add(new Customer("Tom",15));
    set.add(new Customer("Tom",20));
    set.add(new Customer("Tom",15));  	//加入了相同对象  通过equals方法判断
    set.add(new Customer("Mike",15));
    
    System.out.println(set.size());
  
    for(Customer customer: set)
      System.out.println(customer.getName()+" "+customer.getAge());
  }
}

