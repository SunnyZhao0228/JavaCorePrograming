package com.annotation.test;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-09-25 14:33]
 */
@Description("I'm class annotation")
public class Animal {
    @Description("I'm method annotation")
    public String getName() {
        return "name";
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
