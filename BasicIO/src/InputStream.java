import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.zip.ZipInputStream;

/**
 * @author : [zqwzh]
 * @version : [v1.0]
 * @createTime : [2021-09-21 9:36]
 */
public class InputStream {
    public static void main(String[] args) throws IOException {



        String s="ABCDE";



        byte b[]=s.getBytes();



        FileOutputStream file=new FileOutputStream("test.txt",true);



        file.write(b);



        file.close();


    }
}
