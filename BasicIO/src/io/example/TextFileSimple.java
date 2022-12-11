package io.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

/**
 * @author zhaoqw
 * @date 2022/10/7
 */
public class TextFileSimple {
    public static void main(String[] args) throws IOException {
        Writer writer = null;
        File file = new File("d:/demo.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            writer = new FileWriter(file);
            writer.write("这是一个新文件New");
            writer.append("Append Content");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

        readTextFile();
        isrSample();
    }


    public static void readTextFile() {
        Reader reader = null;
        File file = new File("d:/demo.txt");
        try{
            reader = new FileReader(file);
            int ch;
            while ((ch = reader.read()) != -1) {
                System.out.println(ch);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void isrSample() {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        try {
            File file = new File("d:/demo.txt");
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis, "UTF-8");
            StringBuilder builder = new StringBuilder();
            while (isr.ready()) {
               builder.append((char) isr.read());
            }
            System.out.println(builder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (isr != null) {
                try {
                    isr.close();
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
    public static void oswSample() {
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
            File file = new File("d:/demo1.txt");
            fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos, "UTF-8");
            osw.write("这是一个新文件newFile");
            osw.append("这是一个新文件newFile");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
