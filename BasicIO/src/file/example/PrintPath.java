package file.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * @author zhaoqw
 * @date 2023/03/09
 */
public class PrintPath {
    public static void main(String[] args) {
        File file = new File("D:\\JavaSrc\\IdeaWorkspace\\JavaCorePrograming");
        ArrayList<String> paths = new ArrayList<>();
        getAllPath(file, paths);
        for (String path : paths) {
            System.out.println(path);
        }

    }

    private static void getAllPath(File file, List<String> paths) {
        File[] files = file.listFiles();
        if (files == null) {
            return ;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                paths.add(f.getPath());
                getAllPath(f, paths);
            } else {
                paths.add(f.getPath());
            }
        }
    }
}
