package cn.zhaoqw.enumtest.demo1;

public class Burrito {
	
	Spiciness degree;
	
	public Burrito(Spiciness degree) {
		super();
		this.degree = degree;
	}

	public void describe() {
		System.out.print("This burrito is ");
		switch(degree) {
		case NOT:
		case MILD:
			System.out.println("not spicy at all");
			break;
		case MEDIUM:
			System.out.println("a litter hot");
		case HOT:
		case FLAMIN:
		System.out.println("maybe too hot.");
		}
	}




	public static void main(String[] args) {
		Burrito plain = new Burrito(Spiciness.NOT);
		Burrito greenChile = new Burrito(Spiciness.MEDIUM);
		Burrito jalapeno = new Burrito(Spiciness.HOT);
		plain.describe();
		greenChile.describe();
		jalapeno.describe();

	}

}
