package test01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author: zhaoqw
 * @date: 2022/3/23 15:13
 */
public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    int sum = 0;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) > '0' && str.charAt(i) < '9') {
        sum += Integer.parseInt(str.charAt(i) + "");
      }
    }

    System.out.println(sum);

  }
}
