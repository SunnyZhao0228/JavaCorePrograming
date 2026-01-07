package cn.itedu.clone;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author zhaoqw
 * @version 1.0
 * @date 2026/1/7
 */
public class DeepCloneDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<String> values = new ArrayList<>();
        values.add("one");
        values.add("two");
        values.add("three");

        System.out.println("Shallow Clone:");
        List<String> shallowClone = (List<String>) values.clone();
        displayDiff(values, shallowClone);

        System.out.println("Deep Clone : ");
        // deep clone
        List<String> deepClone = deepClone(values);
        displayDiff(values, deepClone);

        // deep clone in java serialization
        System.out.println("Deep Clone in Java Serialization : ");
        List<String> deepClone2 = deepCloneInJavaSerialization(values);
        displayDiff(values, deepClone2);
    }


    public static void displayDiff(List<String> values, List<String> clone) {
        for (int i = 0; i < values.size(); i++) {
            System.out.printf("Objects.equals : %s\n", Objects.equals(values.get(i), clone.get(i))); // true
            System.out.printf("Object == :  %s\n", values.get(i) == clone.get(i)); // false
        }
    }

    private static List<String> deepClone(List<String> source) {
        List<String> clone = new ArrayList<>(source.size());
        for (String value : source) {
            clone.add(new String(value));
        }
        return clone;
    }

    private static List<String> deepCloneInJavaSerialization(List<String> source) throws IOException, ClassNotFoundException {
        ArrayList<String> copy = new ArrayList<>(source);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        // Copy 对象序列化
        objectOutputStream.writeObject(copy);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        List<String> clone = (List<String>) objectInputStream.readObject();

        return clone;
    }
}
