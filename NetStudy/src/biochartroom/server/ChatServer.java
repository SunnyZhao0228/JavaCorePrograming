package biochartroom.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-09-20 13:51]
 */
public class ChatServer {
    private final String QUIT = "quit";
    private final int DEFAULT_SERVER_PORT=8999;
    private ServerSocket serverSocket = null;
    private Map<Integer, Writer> connectedClients = null;

    public ChatServer() {
        connectedClients = new HashMap<>();
    }


    public void addClient(Socket socket) throws IOException {
        if (socket != null) {
            int port = socket.getPort();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            connectedClients.put(port,writer);

            System.out.println("客户端[" + socket.getPort() +"]已经连接到服务器");
        }

    }

    public void removeClient(Socket socket) throws IOException {
        if (socket != null) {
            int port = socket.getPort();
            if (connectedClients.containsKey(port)) {
                connectedClients.get(port).close();
            }
            connectedClients.remove(port);
            System.out.println("客户端[" + port +"]已经断开链接");
        }
    }


    /**
     * 转发信息
     * @param socket
     * @param fwdMsg
     * @throws IOException
     */
    public void forwardMessage(Socket socket, String fwdMsg) throws IOException {
        for (Integer id:connectedClients.keySet()) {
            if (!id.equals(socket.getPort())) {
                Writer writer = connectedClients.get(id);
                writer.write(fwdMsg);
                writer.flush();
            }

        }
    }


    public void start() {

        try {
            //绑定监听端口
            serverSocket = new ServerSocket(DEFAULT_SERVER_PORT);
            System.out.println("启动服务器，监听端口"+ DEFAULT_SERVER_PORT);

            while(true) {
                //等待客户端处理
                Socket socket = serverSocket.accept();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



    }

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        chatServer.start();
    }
}
