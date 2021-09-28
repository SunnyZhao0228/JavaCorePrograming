package cn.zhaoqw.studygeneric.jukebox;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class JukeBox {
    ArrayList<Song> songList = null;
    JukeBox() {
        songList = new ArrayList<>();
    }
    /**
     * 读取songList的内容
     *
     * @param args
     */
    public static void main(String[] args) {
        new JukeBox().go();
    }


    /**
     * 播放歌曲
     */
    public void go() {
        getSongs();         //获取歌曲信息
        System.out.println(songList); //输出所有歌曲信息
        songSort(1);                //歌曲排序,按照歌名
        System.out.println(songList);
    }

    public void songSort(int i) {
        /*Collections.sort(songList, new Comparator<Song>() {
            @Override
            public int compare(Song o1, Song o2) {
               switch (i) {
                   case 1:
                       return o1.title.compareTo(o2.title);
                   case 2:
                       return o1.artist.compareTo(o2.artist);
               }
               return 0;
            }
        });*/

        Collections.sort(songList, (o1,o2) ->{
            switch (i) {
                case 1:
                    return o1.title.compareTo(o2.title);
                case 2:
                    return o1.artist.compareTo(o2.artist);
            }
            return 0;
        });

    }


    /**
     * 获取歌曲列表加入到List中
     */
    public void getSongs() {
        File file = new File("songList.txt");
        FileReader fr = null;
        BufferedReader bfr = null;

        try {

            fr = new FileReader(file);
            bfr = new BufferedReader(fr);
            String line;
            while ((line = bfr.readLine()) != null) {
                String[] tokens = line.split("/");
                songList.add(new Song(tokens[0],tokens[1],tokens[2],tokens[3]));
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bfr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
