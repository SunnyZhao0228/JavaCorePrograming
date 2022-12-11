package file.example;

import java.io.File;
import java.io.IOException;

/**
 * @author zhaoqw
 * @date 2022/10/6
 */
public class FileExample1 {
    public static void main(String[] args) {
        File file = new File("d:/a.txt");
        File dir = new File("d:/aaa/bbb/ccc");
        try {
            boolean newFile = file.createNewFile();
            System.out.println(newFile ? "创建成功":"创建失败");
            System.out.println("文件目录:" + file.getPath());
            System.out.println("是否存在:" +  file.exists());
            System.out.println("是否是目录:" + file.isDirectory());
            System.out.println("文件大小(字节):" + file.length());
            System.out.println("文件名：" + file.getName());
            boolean delete = file.delete();
            System.out.println("文件是否被删除:" + delete);
            System.out.println("是否存在:" +  file.exists());

            // mkdir 创建单个目录 mkdirs 创建多级目录
            boolean created = dir.mkdirs();
            System.out.println(dir.getPath() + ":" + created);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
