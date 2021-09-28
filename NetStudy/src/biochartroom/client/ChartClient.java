package biochartroom.client;

import java.net.Socket;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-09-20 13:51]
 */
public class ChartClient {
    private final String QUIT = "quit";
    private final String DEFAULT_SERVER_HOST = "127.0.0.1";
    private final int DEFAULT_SERVER_PORT=8999;
    private Socket socket = null;
}
