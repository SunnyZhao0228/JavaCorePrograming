import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * @author zhaoqw
 * @date 2024/6/18
 */
public class OpenSslTest {
    public static final String CERT_NAME = "CA";
    public static final String C_VALUE = "CC";
    public static final String S_VALUE = "SValue";
    public static final String L_VALUE = "LValue";
    public static final String O_VALUE = "Ovalue";
    public static final String OU_VALUE = "OUvalue";
    public static final String CN_VALUE = "CNvalue";

    public static String pw = "password";

    public static int expireTime = 365;

    public static void main(String[] args) {
        try{
            File file1 = new File("D:/logs/certs/0618");
            if (!file1.exists()) {
                file1.mkdirs();
            }
            Runtime runtime = Runtime.getRuntime();
            String cmd0 = "D:/JavaDevEnvir/OpenSSL-Win64/bin/openssl.exe";

            String cmd1 = " genrsa -out D:/logs/certs/0618/" + CERT_NAME + "-key.pem 1024";
            String cmd2 = " req -new -out D:/logs/certs/0618/" + CERT_NAME + "-req.csr -key D:/logs/certs/0618/"
                    + CERT_NAME + "-key.pem -passin pass:" + pw
                    + " -subj /C=" + C_VALUE + "/ST=" + S_VALUE + "/L=" + L_VALUE
                    + "/O=" + O_VALUE + "/OU=" + OU_VALUE + "/CN=" + CN_VALUE;
            String cmd3 = " x509 -req -in D:/logs/certs/0618/" + CERT_NAME + "-req.csr -out D:/logs/certs/0618/"
                    + CERT_NAME + "-cert.pem -signkey D:/logs/certs/0618/" + CERT_NAME + "-key.pem -days "
                    + expireTime;

            String cmd4 = " pkcs12 -export -clcerts -in D:/logs/certs/0618/" + CERT_NAME
                    + "-cert.pem -inkey D:/logs/certs/0618/" + CERT_NAME
                    + "-key.pem -out D:/logs/certs/0618/" + CERT_NAME + ".p12 -password pass:" + pw;

            Process process;
            int pcode = 0;
            process = runtime.exec(cmd0 + cmd1);
            process.waitFor();
            // pcode=0, 无错误，等于=1，说明有错误，C语言没有异常处理机制
            if (pcode == 1) {
                throw new Exception(getErrorMessage(process));
            }

            process = runtime.exec(cmd0 + cmd2);
            process.waitFor();
            // pcode=0, 无错误，等于=1，说明有错误，C语言没有异常处理机制
            if (pcode == 1) {
                throw new Exception(getErrorMessage(process));
            }

            process = runtime.exec(cmd0 + cmd3);
            process.waitFor();
            // pcode=0, 无错误，等于=1，说明有错误，C语言没有异常处理机制
            if (pcode == 1) {
                throw new Exception(getErrorMessage(process));
            }

            process = runtime.exec(cmd0 + cmd4);
            process.waitFor();
            // pcode=0, 无错误，等于=1，说明有错误，C语言没有异常处理机制
            if (pcode == 1) {
                throw new Exception(getErrorMessage(process));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getErrorMessage(Process process) {
        String message = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            message = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }
}
