package com.annotation.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 一、为什么要学习注解？学习注解有什么好处？学完能做什么？
 * 1、能够读懂别人写的代码，别别是框架相关的代码
 * 2、让编程更加简洁，代码更加清晰。 JDK自带注解(JDK1.5以后版本能用)
     *  1、 @Override //覆盖
     *  2、 @Deprecated //已经过时
     *  3、 @SuppressWarnings("deprecation") //压制警告（过时） 注解分类 按运行机制分
 *  1、源码注解
 *  2、编译时注解
 *  3、运行时注解 按照来源划分
     *  1、来自JDK的注解
     *  2、来自第三方的注解
     *  3、自定义注解 元注解 自定义注解 成员类型是受限的，
 *  合法的类型包括原始类型及String,Calss,Anootation,Enumreation
 *  如果注解已有一个成员，则成员名必须取名为Vaue(),在使用的时可以忽略成员名和赋值号（=）
 *  注解类可以没有成员，没有成员的注解成为表示注解
 *  public @interface javen{
        String name();
        String author();
        int age() default 19;
 *  }
 *  元注解
 *  @Target({ElementType.CONSTRUCTOR,ElementType.FIELD,ElementType.METHOD})
 *  // Target 注解的作用域 CONSTRUCTOR 构造方法声明，FIELD 字段声明，LOCAL_VARIABLE 局部变量声明 ，METHOD 方法声明，PACKAGE 包声明，PARAMETER 参数声明，TYPE 类接口。
 *  @Retention(RetentionPolicy.RUNTIME)
 *  //Retention 生命周期 SOURCE 只在源码显示，编译时会丢弃，CLASS 编译时会记录到class中，运行时忽略，RUNTIME 运行时存在，可以通过反射读取。
 *  @Inherited //Inherited 允许子类继承
 *  @Documented 生成javadoc的时候包含注解
 */
public class ParseAnnotation {
    public static void main(String[] args) {
        try {
            //使用类加载器加载类
            Class c = Class.forName("com.annotation.test.Dog");
            boolean isExist = c.isAnnotationPresent(Description.class);
            if (isExist) {
                Description d =(Description) c.getAnnotation(Description.class);
                System.out.println(d.value());
            }


            Method getName = c.getMethod("getName");
            boolean exist = getName.isAnnotationPresent(Description.class);
            if (exist) {
                Description d1 =(Description)getName.getAnnotation(Description.class);
                System.out.println(d1.value());
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
