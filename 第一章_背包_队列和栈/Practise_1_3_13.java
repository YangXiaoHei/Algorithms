package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_3_13 {
	static class Queue<T> {
		private T[] items;
		private int N;
		private int head;
		private int tail;
		@SuppressWarnings("unchecked")
		Queue(int max) {
			items = (T[])new Object[max];
		}
		Queue() {
			this(10);
		}
		void enqueue(T e) {
			if (isFull())
				throw new RuntimeException("queue is full");
			N++;
			items[tail] = e;
			tail = (tail + 1) % items.length;
		}
		T dequeue() {
			if(isEmpty())
				throw new RuntimeException("queue is empty");
			N--;
			T deq = items[head];
			head = (head + 1) % items.length;
			StdOut.print(deq + " ");
			return deq;
		}
		boolean isEmpty() { return N == 0; }
		boolean isFull() { return N == items.length; }
		public String toString() {
			StringBuilder sb = new StringBuilder();
			int cur = head;
			do {
				sb.append(items[cur] + " ");
				cur = (cur + 1) % items.length;
			} while(cur != tail);
			return sb.toString();
		}
	}
	public static void main(String[] args) {
		Queue<Integer> queue = new Queue<Integer>();
 		queue.enqueue(0);
 		queue.enqueue(1);
 		queue.enqueue(2);
 		queue.enqueue(3);
 		queue.enqueue(4);
 		queue.enqueue(5);
 		queue.enqueue(6);
 		queue.enqueue(7);
 		queue.enqueue(8);
 		queue.enqueue(9);
 		queue.dequeue();
 		queue.dequeue();
 		queue.dequeue();
 		queue.dequeue();
 		queue.dequeue();
 		queue.dequeue();
 		queue.dequeue();
 		queue.dequeue();
 		queue.dequeue();
 		queue.dequeue();
 		StdOut.println("除了 a 是可能的序列，其他序列都不可能出现");
	}
}
