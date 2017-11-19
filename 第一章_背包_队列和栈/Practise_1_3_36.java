package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.*;
import java.util.*;

/*
 * 思路 :
 * 
 * 拷贝一份原队列中数组的内容，只拷贝实际的元素，空位忽略
 * 然后打乱这份数组拷贝
 * 在迭代器中使用这份打乱的数组拷贝
 * 
 */
public class Practise_1_3_36 {
	static class RandomQueue<T> implements Iterable<T> {
		@SuppressWarnings("unchecked")
		T[] items = (T[])new Object[1];
		private int size, head, tail;
		RandomQueue() {}
		void enqueue(T item) {
			if (size == items.length)
				resize(2 * items.length);
			size++;
			items[tail] = item;
			tail = (tail + 1) % items.length;
		}
		T dequeue() {
			if (isEmpty())
				throw new RuntimeException("dequeue from empty queue");
			int r = randomIndex();
			T tmp = items[r];
			items[r] = items[head];
			items[head] = tmp;
			T del = items[head];
			items[head] = null;
			size--;
			head = (head + 1) % items.length;
			if (size > 0 && size == items.length / 4)
				resize(items.length / 2);
			return del;
		}
		boolean isEmpty() { return size == 0; }
		@SuppressWarnings("unchecked")
		void resize(int newsize) {
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
		int randomIndex() {
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
		@SuppressWarnings("unchecked")
		T[] copy() {
			T[] copy = (T[])new Object[size];
			int k = 0, cur = head;
			do {
				copy[k++] = items[cur];
				cur = (cur + 1) % items.length;
			} while(cur != tail);
			return copy;
		}
		void shuffle(T[] arr) {
			for(int i = 0; i < arr.length; i++) {
				int r = i + StdRandom.uniform(arr.length - i);
				T tmp = arr[r];
				arr[r] = arr[i];
				arr[i] = tmp;
			}
		}
		public Iterator<T> iterator() {
			return new Iterator<T>() {
				private T[] copy = copy();
				{ shuffle(copy); }
				private int index = 0;
				public boolean hasNext() {
					return index < copy.length;
				}
				public T next() {
					return copy[index++];
				}
				public void remove() {
					throw new UnsupportedOperationException();
				}
			};
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
	public static void randomTest() {
		RandomQueue<Integer> queue = new RandomQueue<Integer>();
		for(int i = 0; i < 10; i++)
			queue.enqueue(i);
		for(int i = 0; i < 10; i++) {
			for(Integer ii : queue)
				StdOut.print(ii + " ");
			StdOut.println();
		}
		
	}
	public static void main(String[] args) {
		randomTest();
	}
	// output
	/*
	 * 	4 7 0 6 5 8 9 2 1 3 
		7 3 1 5 6 2 0 9 8 4 
		4 2 0 7 5 6 3 8 9 1 
		1 7 4 0 3 6 2 9 8 5 
		8 3 5 2 6 0 1 4 7 9 
		8 1 4 7 6 0 9 5 3 2 
		9 7 2 5 1 4 3 0 8 6 
		1 8 6 5 4 7 2 3 0 9 
		5 6 2 8 4 1 9 0 7 3 
		7 3 0 9 1 5 8 4 2 6 

	 */
}
