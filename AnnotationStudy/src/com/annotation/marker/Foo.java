package com.annotation.marker;

/**
 * @author zhaoqw
 * @date 2023/04/26
 */
public class Foo {

    @Test
    public static void m1() {
        System.out.println("m1.....................");
    }

    public static void m2() {

    }

    @Test
    public static void m3() {
        throw new RuntimeException("Boom");
    }

    public static void m4() {

    }

    @Test
    public static void m5() {

    }

    public static void m6() {

    }

    @Test
    public static void m7() {
        throw new RuntimeException("Boom");
    }
}
