package cn.zhaoqw.aboutlog;

import java.util.logging.*;
import java.io.IOException;
public class LoggerTester3{

  static class MyFormatter extends Formatter {  //自定义的日志输出格式类
    public String format(LogRecord record) { //覆盖format()方法
      return "<"+record.getLevel() + ">:" + record.getMessage()+"\n"; 
    } 
  }

  public  static void main(String[] args)throws IOException{  
    Logger myLogger = Logger.getLogger("mylogger"); //得到一个日志记录器对象
    
    FileHandler fileHandler = new FileHandler("test1.log"); 
    fileHandler.setFormatter(new MyFormatter()); //设置自定义的日志输出格式

    myLogger.addHandler(fileHandler); //把FileHandler与Logger对象关联

    myLogger.info("这是一条普通提示信息");   //生成INFO级别的日志
    myLogger.warning("这是一条警告信息");   //生成WARNING级别的日志
 }
}


