package biochartroom.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-09-20 14:00]
 */
public class ChatHandler implements Runnable{
    private ChatServer server;
    private Socket socket;

    public ChatHandler(ChatServer server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            //存储新上线的用户
            server.addClient(socket);

            //读取用户发来的信息
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg = null;
            while((msg = reader.readLine()) != null) {
                String forwordMsg = "客户端[" + socket.getPort() + "]" + msg;
                System.out.println(forwordMsg);

                //将信息转发给聊天室里的其他用户
                server.forwardMessage(socket,forwordMsg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
