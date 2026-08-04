package exercise1;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Handler;

/**
 * @author zhaoqw
 * @version 1.0
 * @date 2026/1/21
 */
public class ConnectionPerThreadWithPool implements Runnable {
    @Override
    public void run() {
        ExecutorService pool = Executors.newFixedThreadPool(100);
        try {
            ServerSocket serverSocket = new ServerSocket(9010);
            while (! Thread.currentThread().isInterrupted()) {
                Socket accept = serverSocket.accept();
                //接收一个连接后，为socket连接，新建一个专属的处理器对象
                Handler handler = new Handler(accept);
                //创建新线程来handle
//                pool.execute(handler);
                //或者，使用线程池来处理
                new Thread(handler).start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public class Handler implements Runnable {
        private static final int SERVER_BUFFER_SIZE = 1024;
        final Socket socket;
        Handler(Socket s)
        {
            socket = s;
        }
        public void run()
        {
            //死循环处理读写事件
            boolean ioCompleted=false;
            while (!ioCompleted)
            {
                try
                {
                    byte[] input = new byte[SERVER_BUFFER_SIZE];
                    /* 读取数据 */
                    socket.getInputStream().read(input);
                    // 如果读取到结束标志
                    // ioCompleted= true
                    // socket.close();

                    /* 处理业务逻辑，获取处理结果 */
                    byte[] output = null;
                    /* 写入结果 */
                    socket.getOutputStream().write(output);
                } catch (IOException ex)
                { /*处理异常*/ }
            }
        }
    }
}


