package cn.zhaoqw.studygeneric.demo1;

public class Holder3<T> {     //Map(K,V)
	private T a;

	public Holder3(T a) {
		this.a = a;
	}

	T get() {
		return a;
	}
	
	public static void main(String[] args) {
		Holder3<Automobile> holder = new Holder3( new Automobile());   //ʹ�÷���Լ��
		Automobile am = holder.get();
		
		
		
		Holder3<Integer> holder2 = new Holder3(5);   //new Integer(5)
		int i = holder2.get();		
	}
}
/**
 * 	  用泛型代替Object
 */
