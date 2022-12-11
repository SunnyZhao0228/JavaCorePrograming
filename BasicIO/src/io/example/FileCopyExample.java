package io.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author zhaoqw
 * @date 2022/10/6
 */
public class FileCopyExample {
    public static void main(String[] args) {
        File source = new File("d:/demo.jpg");
        File target = new File("d:/demo1.jpg");
        InputStream fis = null;
        OutputStream fos = null;
        try {
            fis = new FileInputStream(source);
            fos = new FileOutputStream(target);
            byte[] bytes = new byte[1024 * 10];
            int len;
            while ((len = fis.read(bytes))!= -1) {
                fos.write(bytes, 0 ,len);
                System.out.println(len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fos != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
