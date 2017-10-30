package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.*;
import java.util.*;
public class Practise_1_3_38 {
	interface GeneralizedQueue<T> {
		boolean isEmpty();
		void insert(T x);
		T delete(int k);
	}
	/*
	 * resizing array implementation
	 */
	static class ResizingArrayGeneralizedQueue<T> implements GeneralizedQueue<T> {
		@SuppressWarnings("unchecked")
		private T[] items = (T[])new Object[1];
		private int head;
		private int tail;
		private int size;
		ResizingArrayGeneralizedQueue() {}
		public boolean isEmpty() { return size == 0; }
		public void insert(T x) {
			if (size == items.length)
				resize(size * 2);
			size++;
			items[tail] = x;
			tail = (tail + 1) % items.length;
			StdOut.println(this);
		}
		public T delete(int k) {
			if (k >= size)
				throw new RuntimeException("index of " + k + "out of array bounds");
			int cur = head;
			for(int i = 0; i < k; i++)
				cur = (cur + 1) % items.length;
			T del = items[cur];
			for(int i = 0; i < k; i++) {
				int curIndex = cur;
				int preIndex = curIndex - 1 < 0 ? items.length - 1 : curIndex - 1;
				items[curIndex] = items[preIndex];
				cur--;
				if (cur < 0) cur = items.length - 1;
			}
			items[head] = null;
			head = (head + 1) % items.length;
			size--;
			if (size > 0 && size == items.length / 4)
				resize(items.length / 2);
			StdOut.println(this + " delete : k = " + k + " value = " + del);
			return del;
		}
		void resize(int newsize) {
			@SuppressWarnings("unchecked")
			T[] newItems = (T[])new Object[newsize];
			int cur = head, k = 0;
			do {
				newItems[k++] = items[cur];
				cur = (cur + 1) % items.length;
			} while(cur != tail);
			head = 0;
			tail = size;
			items = newItems;
		}
		public String toString() {
			if (isEmpty()) return "[empty]";
			StringBuilder sb = new StringBuilder();
			sb.append("|");
			for(int i = 0; i < items.length; i++)
				sb.append(String.format("  %-3s|", items[i] == null ? " " : items[i]));
			return sb.toString();
		}
	}
	
	public static void main(String[] args) {
		ResizingArrayGeneralizedQueue<Integer> queue = new ResizingArrayGeneralizedQueue<Integer>();
		for(int i = 0; i < 8; i++)
			queue.insert(i);
		for(int i = 0; i < 4; i++)
			queue.delete(0);
		for(int i = 8; i < 12; i++)
			queue.insert(i);
		queue.delete(0);
		queue.delete(4);
		queue.delete(2);
		queue.delete(3);
		queue.delete(1);
		queue.delete(2);
		queue.delete(1);
		queue.delete(0);
		
	}
}
