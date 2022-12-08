package cn.zhaoqw.study.propertiestest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author zhaoqw
 * @date 2022/08/03
 */
public class PropertiesTest {
  public static void main(String[] args) throws IOException {
    System.out.println("写入Test.properties================");
    WriteProperties("Test.properties","name", "12345");

    System.out.println("加载Test.properties================");
    GetAllProperties("Test.properties");

    System.out.println("从Test.properties加载================");
    String value = GetValueByKey("Test.properties", "name");
    System.out.println("name is " + value);
  }

  private static String GetValueByKey(String path, String key) throws FileNotFoundException {
    Properties pps = new Properties();
    try {
      InputStream in = new BufferedInputStream (new FileInputStream(path));
      pps.load(in); //所有的K-V对都加载了
      String value = pps.getProperty(key);
      //System.out.println(key + " = " + value);
      return value;

    }catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  private static void GetAllProperties(String filePath) throws IOException {
    Properties pps = new Properties();
    InputStream in = new BufferedInputStream(new FileInputStream(filePath));
    pps.load(in); //所有的K-V对都加载了
    Enumeration en = pps.propertyNames(); //得到配置文件的名字

    while(en.hasMoreElements()) {
      String strKey = (String) en.nextElement();
      String strValue = pps.getProperty(strKey);
      //System.out.println(strKey + "=" + strValue);
    }
  }

  private static void WriteProperties(String filePath, String name, String value) throws IOException {
    File file = new File(filePath);
    if (! file.exists()) {
      file.createNewFile();
    }
    Properties properties = new Properties();
    InputStream inputStream = new FileInputStream(filePath);
    // 从流中读取属性列表
    properties.load(inputStream);
    OutputStream outputStream = new FileOutputStream(filePath);
    properties.setProperty(name, value);
    properties.store(outputStream, "Update" + name + "name");
    outputStream.close();
  }
}
