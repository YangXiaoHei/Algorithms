package 第一章_背包_队列和栈;

import java.util.*;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_3_15 {
	static class Queue<T> {
		@SuppressWarnings("unchecked")
		private T[] items = (T[])new Object[1];
		private int head;
		private int tail;
		private int size;
		@SuppressWarnings("unchecked")
		void resize(int N) {
			T[] newItems = (T[])new Object[N];
			int cur = head, k = 0;
			do {
				newItems[k++] = items[cur];
				cur = (cur + 1) % items.length; 
			} while(cur != tail);
			head = 0;
			tail = size;
			items = newItems;
		}
		void enqueue(T item) {
			if (size == items.length)
				resize(2 * size);
			size++;
			items[tail] = item;
			tail = (tail + 1) % items.length;
		}
		T dequeue() {
			if (isEmpty())
				throw new RuntimeException("empty queue");
			size--;
			T deq = items[head];
			items[head] = null;
			head = (head + 1) % items.length;
			if (size > 0 && size == items.length / 4)
				resize(items.length / 2);
			return deq;
		}
		int size() {
			return size;
		}
		boolean isEmpty() {
			return size == 0;
		}
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("|");
			for(int i = 0; i < items.length; i++)
				sb.append(String.format("%8s |", items[i] == null ? " " : items[i]));
			sb.append("       <<<<< head : " + head + " tail : " + tail + " >>>>");
			return sb.toString();
		}
	}
	/*
	 * 打印倒数第 k 个字符串
	 */
	public static void printKthString(String[] s, int k) {
		Queue<String> queue = new Queue<String>();
		for(String ss : s)
			queue.enqueue(ss);
		int size = queue.size;
		for(int i = 0; i < size - k; i++)
			queue.dequeue();
		StdOut.println("倒数第 " + k + " 个字符串是 " + queue.dequeue());
	}
	public static void main(String[] args) {
		printKthString("to be or not to be that's a question".split(" "), 5);
	}
	// output :
	/*
	 * 倒数第 5 个字符串是 to
	 */
}
