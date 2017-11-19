package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_3_06 {
	/*
	 * 思路 :
	 * 
	 * 脑补一下可以知道，假如队列内容是 1 2 3 4 5
	 * 入栈后变为
	 * 5
	 * 4
	 * 3
	 * 2
	 * 1
	 * 再入队后变为 5 4 3 2 1 相当于逆序了这个队列
	 * 
	 */
	static class Stack<Item> {
		private Item[] items;
		private int size;
		Stack() { this(10); }
		@SuppressWarnings("unchecked")
		Stack(int max) {
			items = (Item[])(new Object[max]);
		}
		void push(Item i) {
			if (isFull())
				throw new RuntimeException("stack is full!");
			items[size++] = i;
		}
		Item pop() {
			if (isEmpty())
				throw new RuntimeException("nothing to pop in the stack");
			Item e = items[--size];
			items[size] = null;
			return e;
		}
		boolean isEmpty() {
			return size == 0;
		}
		boolean isFull() {
			return size == items.length;
		}
	}
	/*
	 * 容量固定的队列
	 */
	static class Queue<Item> {
		private Item[] items;
		private int size;
		private int head;
		private int tail;
		Queue() { this(10); }
		@SuppressWarnings("unchecked")
		Queue(int max) {
			items = (Item[])new Object[max];
			head = tail = 0;
		}
		void enqueue(Item i) {
			if (isFull())
				throw new RuntimeException("queue is full");
			items[tail] = i;
			tail = (tail + 1) % items.length;
			size++;
		}
		boolean isFull() {
			return size == items.length;
		}
		boolean isEmpty() {
			return size == 0;
		}
		Item dequeue() {
			if (isEmpty())
				throw new RuntimeException("queue is empty");
			size--;
			Item deq = items[head];
			items[head] = null;
			head = (head + 1) % items.length;
			return deq;
		}
		public String toString() {
			StringBuilder sb = new StringBuilder("队列 : ");
			for(int i = 0; i < items.length; i++)
				sb.append(items[(head + i) % items.length] + " ");
			return sb.toString();
		}
	}
	/*
	 * 逆序一个队列
	 */
	public static void whatOperation(Queue<String> q) {
		Stack<String> stack = new Stack<String>();
		while(!q.isEmpty())
			stack.push(q.dequeue());
		while(!stack.isEmpty())
			q.enqueue(stack.pop());
	}
	public static void main(String[] args) {
		Queue<String> queue = new Queue<String>();
		for(int i = 0; i < 10; i++)
			queue.enqueue(i + "");
		StdOut.println("===== 操作前  =====");
		StdOut.println(queue);
		StdOut.println("===== 操作后  =====");
		whatOperation(queue);
		StdOut.println(queue);
	}
	// output 
	/*
	 * 	===== 操作前  =====
		队列 : 0 1 2 3 4 5 6 7 8 9 
		===== 操作后  =====
		队列 : 9 8 7 6 5 4 3 2 1 0 
	 */
}
