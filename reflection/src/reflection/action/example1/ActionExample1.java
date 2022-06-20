package reflection.action.example1;

import java.lang.reflect.Array;

/**
 * @author: zhaoqw
 * @date: 2022/3/4 13:09
 */
public class ActionExample1 {
  public static void main(String[] args) {
    int[] arr = {1,2,3,4,5,6,7,8,9,10};
    arr = arrayExpansion(arr, arr.length * 2);
    System.out.println(arr.length);
  }

  /**
   * 反射技术给数组扩容
   * @param oldArray
   * @param newLength
   * @param <T>
   * @return
   */
  public static <T> T arrayExpansion(T oldArray, int newLength) {
    Class aClass = oldArray.getClass();

    // 获取数组中的元素类型
    Class componentType = aClass.getComponentType();

    // 旧数组长度
    int length = Array.getLength(oldArray);

    // 新数组
    T newArray = (T) Array.newInstance(componentType, newLength);

    // 拷贝旧数据
    System.arraycopy(oldArray, 0, newArray,0, length);

    return newArray;
  }
}
