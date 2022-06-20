package reflection.property;

import java.lang.reflect.Field;

/**
 * 反射获取属性
 * @author: zhaoqw
 * @date: 2022/3/3 17:59
 */
public class A {
  public int age;
  private String name;

  public A(int age, String name) {
    this.age = age;
    this.name = name;
  }

  public static void main(String[] args) throws IllegalAccessException {
    A obj = new A(20,"niudiewei");
    // 获取本类的所有public字段
    Class aClass = obj.getClass();

    Field[] fields = aClass.getFields();
    System.out.println(fields[0].getName() + " : " + fields[0].get(obj));

    // 获取本类的所有字段
    Field[] declaredFields = aClass.getDeclaredFields();
    for (Field declaredField : declaredFields) {
      System.out.println(declaredField.getName() + " : " + declaredField.get(obj));
    }
  }
}
