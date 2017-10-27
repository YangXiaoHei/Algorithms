package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Practise_1_3_14 {
	static class Queue<T> {
		@SuppressWarnings("unchecked")
		private T[] items = (T[])new Object[1];
		private int head, tail, size;
		void enqueue(T it) {
			if (size == items.length) 
				resize(items.length * 2);
			items[tail] = it;
			tail = (tail + 1) % items.length;
			size++;
			StdOut.println(this);
		}
		T dequeue() {
			if (isEmpty())
				throw new RuntimeException("queue is empty");
			size--;
			T deq = items[head];
			items[head] = null;
			head = (head + 1) % items.length;
			if (size > 0 && size == items.length / 4)
				resize(items.length / 2);
			StdOut.println(this);
			return deq;
		}
		boolean isEmpty() {
			return size == 0;
		}
		@SuppressWarnings("unchecked")
		void resize(int len) {
			T[] newItem = (T[])new Object[len];
			int cur = head, k = 0;
			do {
				newItem[k++] = items[cur];
				cur = (cur + 1) % items.length; 
			} while(cur != tail);
			head = 0;
			tail = size;
			items = newItem;
		}
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("|");
			for(int i = 0; i < items.length; i++) {
				sb.append(String.format(" %-2s |", items[i] == null ? " " : items[i]));
			}
			sb.append(String.format("     <<< head : %d  tail : %d >>>", head, tail));
			return sb.toString();
		}
	}
	public static void main(String[] args) {
		boolean[] optrs = new boolean[20];
		for(int i = 0; i < 20; i++)
			optrs[i] = StdRandom.bernoulli(0.4);
		Queue<Integer> queue = new Queue<Integer>();
		int k = 0;
		queue.enqueue(k++);
		queue.enqueue(k++);
		queue.enqueue(k++);
		queue.enqueue(k++);
		queue.enqueue(k++);
		queue.enqueue(k++);
		try {
			for(int i = 0; i < 20; i++)
				if (optrs[i])
					queue.enqueue(k++);
				else
					queue.dequeue();
		} catch (Exception e) {
			StdOut.println("ahead of schedule");
		}
		StdOut.println("game over");
	}
	// output 
	/*
	 * 	| 0  |     <<< head : 0  tail : 0 >>>
		| 0  | 1  |     <<< head : 0  tail : 0 >>>
		| 0  | 1  | 2  |    |     <<< head : 0  tail : 3 >>>
		| 0  | 1  | 2  | 3  |     <<< head : 0  tail : 0 >>>
		| 0  | 1  | 2  | 3  | 4  |    |    |    |     <<< head : 0  tail : 5 >>>
		| 0  | 1  | 2  | 3  | 4  | 5  |    |    |     <<< head : 0  tail : 6 >>>
		|    | 1  | 2  | 3  | 4  | 5  |    |    |     <<< head : 1  tail : 6 >>>
		|    | 1  | 2  | 3  | 4  | 5  | 6  |    |     <<< head : 1  tail : 7 >>>
		|    | 1  | 2  | 3  | 4  | 5  | 6  | 7  |     <<< head : 1  tail : 0 >>>
		|    |    | 2  | 3  | 4  | 5  | 6  | 7  |     <<< head : 2  tail : 0 >>>
		|    |    |    | 3  | 4  | 5  | 6  | 7  |     <<< head : 3  tail : 0 >>>
		|    |    |    |    | 4  | 5  | 6  | 7  |     <<< head : 4  tail : 0 >>>
		|    |    |    |    |    | 5  | 6  | 7  |     <<< head : 5  tail : 0 >>>
		| 6  | 7  |    |    |     <<< head : 0  tail : 2 >>>
		| 7  |    |     <<< head : 0  tail : 1 >>>
		|    |    |     <<< head : 1  tail : 1 >>>
		ahead of schedule
		game over
	 */
}
