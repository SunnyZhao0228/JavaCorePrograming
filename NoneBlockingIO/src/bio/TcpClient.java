package bio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

/**
 * @author zhaoqw
 * @date 2022/12/11
 */
public class TcpClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getByAddress("127.0.0.1".getBytes(StandardCharsets.UTF_8)), 9001);
    }
}
