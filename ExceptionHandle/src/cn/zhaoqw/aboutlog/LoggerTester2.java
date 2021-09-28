package cn.zhaoqw.aboutlog;

import java.util.logging.*;
import java.io.IOException;
public class LoggerTester2{
  public  static void main(String[] args) throws IOException {  
    Logger myLogger = Logger.getLogger("mylogger"); //得到一个日志记录器对象
    
    //创建一个FileHandler对象，它向指定的文件中写日志
    FileHandler fileHandler = new FileHandler("test.log"); 
    fileHandler.setLevel(Level.INFO); //设定向文件中写日志的级别 
    myLogger.addHandler(fileHandler); //把FileHandler与Logger对象关联
    
    myLogger.info("这是一条普通提示信息");   //生成INFO级别的日志
    myLogger.warning("这是一条警告信息");   //生成WARNING级别的日志
    myLogger.severe("这是一条严重错误信息");  //生成SEVERE级别的日志
 }
}


