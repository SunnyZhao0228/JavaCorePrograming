package cn.zhaoqw.enumtest.demo1;

public class Test {

	public static void main(String[] args) {

		Spiciness s = Spiciness.FLAMIN;

		System.out.println(s);
		for(Spiciness s1:Spiciness.values()) {
			System.out.println(s1.toString() + ",ordinal " + s1.ordinal());
		}
	}

}
