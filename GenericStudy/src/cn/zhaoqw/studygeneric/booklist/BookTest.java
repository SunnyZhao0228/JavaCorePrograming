package cn.zhaoqw.studygeneric.booklist;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class BookTest {
    //存储图书信息
    private List<Book> booksList = new ArrayList<>();


    public void go() {
        getBooks();
        booksList.forEach(System.out::println);
        System.out.println("=========================================");
        bookSort(3);    //按照价格排序
        booksList.forEach(System.out::println);
    }

    private void bookSort(int i) {
        switch(i) {
            case 1:
                //按照书名进行排序
                Collections.sort(booksList, (book1,book2)-> book1.getBookName().compareTo(book2.getBookName()));
                break;
            case 2:
                //按照作者进行排序
                Collections.sort(booksList, (book1,book2)-> book1.getAuthor().compareTo(book2.getAuthor()));
                break;
            case 3:
                //按照价格进行排序
                Collections.sort(booksList, (book1,book2)-> book1.getPrice().compareTo(book2.getPrice()));
                break;
            case 4:
                //按照ISBN号进行排序
                Collections.sort(booksList, (book1,book2)-> book1.getISBN().compareTo(book2.getISBN()));
                break;

        }
    }


    public void getBooks() {
        File file = new File("t_book.txt");
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while((line = bufferedReader.readLine()) != null) {
                String[] taken = line.split("/");
                Book book = new Book();
                book.setBookId(taken[0]);
                book.setBookName(taken[1]);
                book.setAuthor(taken[2]);
                book.setTypeId(Integer.parseInt(taken[3]));
                book.setPrice(Double.parseDouble(taken[4]));
                book.setISBN(taken[5]);
                book.setPublishing(taken[6]);
                book.setPublishTime(new SimpleDateFormat("yyyy-MM-dd").parse(taken[7]));
                book.setStaus(taken[8].toCharArray()[0]);
                booksList.add(book);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        new BookTest().go();
    }
}
