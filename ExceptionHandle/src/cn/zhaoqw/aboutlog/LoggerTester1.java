package cn.zhaoqw.aboutlog;

import java.util.logging.*;

public class LoggerTester1 {
    public static void main(String[] args) {
        Logger myLogger = Logger.getLogger("mylogger"); // 得到一个日志记录器对象
        myLogger.setLevel(Level.WARNING); // 设置WARNING日志级别

        myLogger.info("这是一条普通提示信息"); // 生成INFO级别的日志
        myLogger.warning("这是一条警告信息"); // 生成WARNING级别的日志
        myLogger.severe("这是一条严重错误信息"); // 生成SEVERE级别的日志
    }
}
