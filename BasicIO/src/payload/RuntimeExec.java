package payload;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhaoqw
 * @date 2024/2/27
 */
public class RuntimeExec {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = Runtime.getRuntime().exec("whoami").getInputStream();
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int readLen = 0;
        while ((readLen = inputStream.read(bytes)) != -1) {
            bos.write(bytes, 0, readLen);
        }
        System.out.println(bos);
    }
}
