package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_3_49 {
	static class Stack<T> {
		private T[] items = (T[])new Object[1];
		private int size;
		public boolean isEmpty() { return size == 0; }
		public int size() { return size; }
		public void push(T item) {
			if (size == items.length)
				resize(items.length * 2);
			items[size++] = item;
		}
		public T pop() {
			T del = items[--size];
			items[size] = null;
			if (size > 0 && size == items.length / 4)
				resize(items.length / 2);
			return del;
		}
		public void resize(int newsize) {
			T[] newItems = (T[])new Object[newsize];
			for (int i = 0; i < size; i++)
				newItems[i] = items[i];
			items = newItems;
		}
		public String toString() {
			if (isEmpty()) return "[empty]";
			StringBuilder sb = new StringBuilder();
			sb.append("|");
			for(int i = 0; i < items.length; i++)
				sb.append(String.format(" %2s |", items[i] == null ? " " : items[i]));
			return sb.toString();
		}
		public static void stackTest() {
			Stack<Integer> stack = new Stack<Integer>();
			for (int i = 0; i < 10; i++)
				stack.push(i);
			for (int i = 0; i < 6; i++)
				stack.pop();
			StdOut.println(stack);
		}
	}
	
	static class Queue<T> {
		private Stack<T> stack1 = new Stack<T>();
		private Stack<T> stack2 = new Stack<T>();
		public int size() { return stack1.size() + stack2.size(); }
		public boolean isEmpty() { return stack1.isEmpty() && stack2.isEmpty(); }
		public void enqueue(T item) {
			if (stack1.isEmpty() && stack2.isEmpty()) {
				stack1.push(item);
			} else if (stack1.isEmpty()) {
				while (!stack2.isEmpty())
					stack1.push(stack2.pop());
				stack1.push(item);
			} else {
				stack1.push(item);
			}
		}
		public T dequeue() {
			if (stack1.isEmpty() && stack2.isEmpty()) {
				throw new RuntimeException("dequeue from a empty queue");
			} else if (stack1.isEmpty()) {
				return stack2.pop();
			} else {
				while (stack1.size() > 1)
					stack2.push(stack1.pop());
				return stack1.pop();
			}
		}
		public static void queueTest() {
			Queue<Integer> queue = new Queue<Integer>();
			for (int i = 0; i < 10; i++)
				queue.enqueue(i);
			for (int i = 0; i < 10; i++)
				StdOut.print(queue.dequeue() + " ");
		}
	}
	public static void main(String[] args) {
		Stack.stackTest();
		Queue.queueTest();
	}
}
