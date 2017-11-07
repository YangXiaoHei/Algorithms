package 第一章_算法分析;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_4_28 {
	static class Queue<T> {
		@SuppressWarnings("unchecked")
		private T[] items = (T[])new Object[1];
		private int size;
		private int head, tail;
		@SuppressWarnings("unchecked")
		void resize(int newSize) {
			T[] newItems = (T[])new Object[newSize];
			int cur = head, k = 0;
			do {
				newItems[k++] = items[cur++];
				if (cur == items.length) cur = 0;
			} while (cur != tail);
			head = 0;
			tail = size;
			items = newItems;
		}
		int size() { return size; }
		boolean isEmpty() { return size == 0; }
		void enqueue(T item) {
			if (size == items.length)
				resize(size * 2);
			size++;
			items[tail++] = item;
			if (tail == items.length) tail = 0;
		}
		T dequeue() {
			if (isEmpty())
				throw new RuntimeException("dequeue from a empty queue");
			size--;
			T del = items[head];
			items[head++] = null;
			if (head == items.length) head = 0;
			if (size > 0 && size == items.length / 4)
				resize(items.length / 2);
			return del;
		}
		public String toString() {
			if (isEmpty()) return "empty queue";
			StringBuilder sb = new StringBuilder();
			sb.append("|");
			for (int i = 0; i < items.length; i++) 
				sb.append(String.format(" %3s |", items[i] == null ? " " : items[i]));
			sb.append(String.format("         <<<<<<<<<< head : %d tail : %d size : %d >>>>>>>>>>", head, tail, size));
			return sb.toString();
		}
	}
	static class Stack<T> {
		private Queue<T> queue = new Queue<T>();
		boolean isEmpty() { return queue.isEmpty(); }
		int size() { return queue.size(); }
		void push(T item) {
			if (queue.isEmpty())
				queue.enqueue(item);
			else {
				@SuppressWarnings("unchecked")
				T[] tmp = (T[])new Object[queue.size()];
				int k = 0;
				while (!queue.isEmpty())
					tmp[k++] = queue.dequeue();
				queue.enqueue(item);
				for (T t : tmp)
					queue.enqueue(t);
			}
		}
		T pop() {
			if (isEmpty())
				throw new RuntimeException("pop from a empty stack");
			return queue.dequeue();
		}
	}
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < 10; i++)
			stack.push(i);
		StdOut.printf("Stack size is %d\n", stack.size());
		while (!stack.isEmpty())
			StdOut.printf("pop %d from stack, now stack size is %d\n", stack.pop(), stack.size());
	}
	// output
	/*
	 *	Stack size is 10
		pop 9 from stack, now stack size is 9
		pop 8 from stack, now stack size is 8
		pop 7 from stack, now stack size is 7
		pop 6 from stack, now stack size is 6
		pop 5 from stack, now stack size is 5
		pop 4 from stack, now stack size is 4
		pop 3 from stack, now stack size is 3
		pop 2 from stack, now stack size is 2
		pop 1 from stack, now stack size is 1
		pop 0 from stack, now stack size is 0
	 */
}
