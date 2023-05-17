package top.zhaoqw.exam1;

/**
 * @author zhaoqw
 * @date 2023/05/08
 */
public class AnimalTest {
    public static void main(String[] args) {
        Animal[] animals = new Animal[4];
        animals[0] = new Cat();
        animals[1] = new Dog();
        animals[2] = new Cat();
        animals[3] = new Dog();
        for (Animal animal : animals) {
            animal.eat();
            animal.move();
        }

        // 作用2： 对象之间的解耦
        // 调用类：
        haveLunch(new Dog());
        haveLunch(new Cat());
        haveLunch(new Animal() {
            @Override
            public void eat() {
                System.out.println("Innner Class: eat");
            }

            @Override
            public void move() {
                System.out.println("Innner Class: move");
            }
        });
    }


    // 被调用方法haveLunch只和Animal有关系
    public static void haveLunch(Animal aAnimal) {
        aAnimal.eat();
    }
}
