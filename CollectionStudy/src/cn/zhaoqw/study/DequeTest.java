package cn.zhaoqw.study;

import java.util.Deque;
import java.util.LinkedList;

public class DequeTest {

	public static void main(String[] args) {


			Deque<String> queue = new LinkedList<String>();
			
			queue.add("aaa");
			queue.add("bbb");
			queue.addFirst("ccc");
			queue.addLast("ddd");
			
			System.out.println(queue.poll());
			
			System.out.println("-----------------");
			queue.forEach(e -> System.out.println(e));

	}

}
