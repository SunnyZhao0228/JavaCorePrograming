package cn.zhaoqw.study.test;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author zhaoqw
 * @date 2023/01/07
 */
public class WordFrequency {

    final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

    public void run(String fileName, long chunkSize) throws ExecutionException, InterruptedException {
        File file = new File(fileName);
        long fileSize = file.length();
        long posistion = 0;

        // 获取结果的Future
        ArrayList<ForkJoinTask> tasks = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        while (posistion < fileSize) {
            long next = Math.min(posistion + chunkSize, fileSize);
            CountTask countTask = new CountTask(fileName, posistion, next);
            posistion = next;
            ForkJoinTask submit = forkJoinPool.submit(countTask);
            tasks.add(submit);
        }

        HashMap<String, Integer> totalMap = new HashMap<>();
        for (ForkJoinTask future : tasks) {
            HashMap<String, Integer> map =(HashMap<String, Integer>) future.get();
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                incKey(entry.getKey(), totalMap, entry.getValue());
            }
        }
        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println(totalMap.size());
    }

    class CountTask implements Callable {
        private final long start;
        private final long end;
        private final String fileName;

        public CountTask(String fileName, long start, long end) {
            this.start = start;
            this.end = end;
            this.fileName = fileName;
        }

        @Override
        public Object call() throws Exception {
            HashMap<String, Integer> map = new HashMap<>();
            FileChannel channel = new RandomAccessFile(this.fileName, "rw").getChannel();
            // 读取[start, end] - memory
            MappedByteBuffer byteBuffer = channel.map(FileChannel.MapMode.READ_ONLY,
                    this.start, this.end - this.start);
            String str = StandardCharsets.UTF_8.decode(byteBuffer).toString();
            return countByStr(str);
        }
    }

    public void compareWithSingle() {
        try(
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream("test.txt"))
        ) {
            byte[] buffer = new byte[1024 * 4];
            int len = 0;
            HashMap<String, Integer> totalMap = new HashMap<>();
            long startTime = System.currentTimeMillis();
            while ((len = bis.read(buffer)) != -1) {
                // 处理最后一次可能读不满buffer的情况
                byte[] bytes = Arrays.copyOfRange(buffer, 0, len);
                String str = new String(buffer);
                HashMap<String, Integer> countMap = countByStr(str);
                for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
                    String key = entry.getKey();
                    incKey(key, totalMap, entry.getValue());
                }
            }
            System.out.println(System.currentTimeMillis() - startTime);
            System.out.println(totalMap.size());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private HashMap<String, Integer> countByStr(String str) {
        HashMap<String, Integer> countMap = new HashMap<>();
        StringTokenizer stringTokenizer = new StringTokenizer(str);
        while (stringTokenizer.hasMoreTokens()) {
            String word = stringTokenizer.nextToken();
            incKey(word, countMap, 1);
        }
        return countMap;
    }
    private void incKey(String key, HashMap<String, Integer> map, Integer value) {
        if (map.containsKey(key)) {
            map.put(key, map.get(key) + value);
        } else {
            map.put(key, value);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        WordFrequency wordFrequency = new WordFrequency();
        wordFrequency.compareWithSingle();
        wordFrequency.run("test.txt", 1024 * 4);
    }
}
