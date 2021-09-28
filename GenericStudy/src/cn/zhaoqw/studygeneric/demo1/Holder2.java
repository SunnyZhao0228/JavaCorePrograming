package cn.zhaoqw.studygeneric.demo1;

public class Holder2 {
	private Object a;

	public Holder2(Object a) {
		this.a = a;
	}

	Object get() {
		return a;
	}
	
	
	public static void main(String[] args) {
		Holder2 holder = new Holder2( new Automobile());
		Automobile am = (Automobile)(holder.get());
		
		Holder2 holder2 = new Holder2(5);   //new Integer(5)
		int i = (Integer)(holder2.get());
	}
}
/**
 * 	  有通用性，但要经常向下转型
 */

