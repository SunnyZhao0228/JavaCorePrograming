package cn.zhaoqw.studygeneric.demo2;

public class Test {

	public static  TwoTuple<String ,Integer> f(){
		return new TwoTuple<String,Integer>("aaa",67);
	}
	
	public static ThreeTuple<Integer,String,String> f2() {
		return new ThreeTuple<>(1001,"username","password");
	}
	
	
	public static void main(String[] args) {
		System.out.println(f());

	}

}
