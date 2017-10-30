package 第一章_背包_队列和栈;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_3_35 {
	static class RandomQueue<T> {
		@SuppressWarnings("unchecked")
		private T[] items = (T[])new Object[1];
		private int size;
		private int head;
		private int tail;
		RandomQueue() {}
		boolean isEmpty() { return size == 0; }
		void enqueue(T item) {
			if (size == items.length)
				resize(2 * size);
			size++;
			items[tail] = item;
			tail = (tail + 1) % items.length;
			StdOut.println(this);
		}
		T dequeue() {
			if (isEmpty())
				throw new RuntimeException("dequeue from a empty queue");
			int r = randomIndex();
			size--;
			T tmp = items[head];
			items[head] = items[r];
			items[r] = tmp;
			T del = items[head];
			items[head] = null;
			head = (head + 1) % items.length;
			if (size > 0 && size == items.length / 4)
				resize(items.length / 2);
			StdOut.println(this + " dequeue -> " + del);
			return del;
		}
		private int randomIndex() {
			if (head < tail)
				return head + StdRandom.uniform(tail - head);
			else {
				int[] indexs = new int[size];
				int k = 0, cur = head;
				do {
					indexs[k++] = cur;
					cur = (cur + 1) % items.length;
				} while(cur != tail);
				return indexs[StdRandom.uniform(size)];
			}
		}
		T sample() {
			return items[randomIndex()];
		}
		@SuppressWarnings("unchecked")
		private void resize(int newsize) {
			T[] newItems = (T[])new Object[newsize];
			int cur = head, k = 0;
			do {
				newItems[k++] = items[cur];
				cur = (cur + 1) % items.length;
			} while (cur != tail);
			head = 0;
			tail = size;
			items = newItems;
		}
		public String toString() {
			StringBuilder sb = new StringBuilder();
			if (isEmpty()) return "[empty]";
			sb.append("|");
			for(int i = 0; i < items.length; i++)
				sb.append(String.format(" %3s |", items[i] == null ? " " : items[i]));
			return sb.toString();
		}
	}
	public static void main(String[] args) {
		RandomQueue<Integer> queue = new RandomQueue<Integer>();
		for(int i = 0; i < 10; i++)
			queue.enqueue(i);
		for(int i = 0; i < 9; i++)
			queue.dequeue();
		for(int i = 11; i < 15; i++)
			queue.enqueue(i);
		for(int i = 0; i < 2; i++)
			queue.dequeue();
		for(int i = 0; i < 4; i++)
			queue.enqueue(i);
		for(int i = 0; i < 7; i++)
			queue.dequeue();
		
	}
}
