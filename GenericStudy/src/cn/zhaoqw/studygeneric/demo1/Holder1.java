package cn.zhaoqw.studygeneric.demo1;

class Automobile {
}

public class Holder1 {
	private Automobile a;

	public Holder1(Automobile a) {
		this.a = a;
	}

	Automobile get() {
		return a;
	}
}
/**
 * 	  通用性不强
 */

