package collections.predecessor;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class VectorTest {

	public static void main(String[] args) {
//		Vector<String> v = new Vector<>();		//区别于ArrayList，它是线程安全的
//		
//		v.add("aasdk");
//		v.add("hello");
//		
//		System.out.println(v);
		
		Set<String> oldset = new HashSet<>();	//不同步的，线程不安全的
		
		Set<String> newset =  Collections.synchronizedSet(oldset);
		
		
		new Thread() {

			@Override
			public void run() {
				for(int i =0 ;i< 1000;i++)
					newset.add("字符串" + i);
			}
			
		}.start();
		
		new Thread() {

			@Override
			public void run() {
				for(int i=0;i<1000;i+=5) {
					newset.remove("字符串" + i);
					System.out.println(newset.size());
				}
			}
		}.start();

	}

}
