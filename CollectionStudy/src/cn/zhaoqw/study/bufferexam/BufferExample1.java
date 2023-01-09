package cn.zhaoqw.study.bufferexam;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousChannel;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 *
 *
 * @author zhaoqw
 * @date 2023/01/05
 */
public class BufferExample1 {
    public static void main(String[] args) throws IOException {
        Random random = new Random();

        String fileName = "word";
        int bufferSize = 4 * 1024;
        //FileOutputStream fos = new FileOutputStream(fileName);
        BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(fileName), bufferSize);
        long start = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 5; j++) {
                fos.write(97 + random.nextInt(5));
            }
        }

        System.out.println(System.currentTimeMillis() - start);
//        fos.close();
//        readTest();
//        testNio();
          testChinese();
    }

    public static void readTest() throws IOException {
        String fileName = "word";
        //FileInputStream fos = new FileInputStream(fileName);
        BufferedInputStream fis = new BufferedInputStream(new FileInputStream(fileName));
        long start = System.currentTimeMillis();

        int len;
        byte[] bytes = new byte[1024];
        while((len = fis.read(bytes)) != -1) {

        }

        System.out.println(System.currentTimeMillis() - start);
        fis.close();
    }

    public static void testNio() {

        String fileName = "word";

        try (
                FileChannel channel = new FileInputStream(fileName).getChannel();
            ){

            ByteBuffer buffer = ByteBuffer.allocate(1024 * 8);
            long start = System.currentTimeMillis();
            while(channel.read(buffer) != -1) {
                buffer.flip();
                // 读取数据
                System.out.println(new String(buffer.array()));
                buffer.flip();
                buffer.clear();
            }
            System.out.println(System.currentTimeMillis() - start);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void testAio() {
        String fileName = "Word";
        try(
                AsynchronousFileChannel channel = AsynchronousFileChannel.open(Path.of(fileName), StandardOpenOption.READ);
            ) {
            ByteBuffer buffer = ByteBuffer.allocate(1024 * 8);
            Future<Integer> operation = channel.read(buffer, 0);
            Integer integer = operation.get();
            buffer.flip();
            System.out.println(new String(buffer.array()));
            buffer.clear();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void testChinese() {
        String raw = "窗前明月光，疑是地上霜。举头望明月，低头思故乡";
        Charset charset = StandardCharsets.UTF_8;
        byte[] array = charset.encode(raw).array();
        byte[] bytes = Arrays.copyOfRange(array, 0, 11);
        ByteBuffer byteBuffer = ByteBuffer.allocate(12);
        CharBuffer charBuffer = CharBuffer.allocate(12);
        byteBuffer.put(bytes);
        byteBuffer.flip();

        charset.newDecoder().decode(byteBuffer, charBuffer, true);
        charBuffer.flip();
        char[] tmp = new char[charBuffer.length()];
        if (charBuffer.hasRemaining()) {
            charBuffer.get(tmp);
            System.out.println("Content:" + new String(tmp));
        }
        System.out.printf("limit-pos = %d \n", byteBuffer.limit() - byteBuffer.position());

        Arrays.copyOfRange(byteBuffer.array(), byteBuffer.position(), byteBuffer.limit());
    }
}
