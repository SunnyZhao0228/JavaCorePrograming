package cn.zhaoqw.studygeneric.aboveall;

import java.util.ArrayList;
public class TestGenerics {
	public void go(){
//		Animal[] animals = {new Dog(),new Cat(), new Dog()};
//		Dog[] dogs = {new Dog(), new Dog(), new Dog()};
//		takeAnimals(animals);
//		takeAnimals(dogs);

		ArrayList<Dog> dogs = new ArrayList<Dog>();
		dogs.add(new Dog());
		dogs.add(new Dog());	
		dogs.add(new Dog());

		takeAnimals(dogs);
		takeAnimals2(dogs);
	}

	public void takeAnimals(ArrayList<? extends Animal> animals){
		for(Animal a:animals){
			a.eat();
		}
	}

	public <T extends Animal> void takeAnimals2(ArrayList<T> animals){
		for(Animal a:animals){
			a.eat();
		}
	}
	
	public static void main(String[] args){
		new TestGenerics().go();
	}
}

class Animal{
	public void eat() {}
}

class Dog extends Animal{
	
}
