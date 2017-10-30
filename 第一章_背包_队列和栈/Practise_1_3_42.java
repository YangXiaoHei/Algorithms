package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_3_42 {
	/*
	 * resizing array implementation
	 */
	@SuppressWarnings("unchecked")
	static class ResizingArrayStack<T> {
		T[] items = (T[])new Object[1];
		private int size;
		private static int counter = 0;
		private final int id = counter++;
		ResizingArrayStack() {}
		ResizingArrayStack(ResizingArrayStack<T> stack) {
			this.size = stack.size;
			items = (T[])new Object[stack.items.length];
			for(int i = 0; i < items.length; i++)
				items[i] = stack.items[i];
		}
		void resize(int newsize) {
			T[] newItems = (T[])new Object[newsize];
			for(int i = 0; i < size; i++)
				newItems[i] = items[i];
			items = newItems;
		}
		void push(T item) {
			if (size == items.length)
				resize(size * 2);
			items[size++] = item;
			StdOut.println(this);
		}
		T pop() {
			if (isEmpty())
				throw new RuntimeException("pop from a empty stack");
			T del = items[--size];
			items[size] = null;
			if (size > 0 && size == items.length / 4)
				resize(items.length / 2);
			StdOut.println(this);
			return del;
		}
		boolean isEmpty() { return size == 0; }
		public String toString() {
			if (isEmpty()) return "[empty]";
			StringBuilder sb = new StringBuilder();
			sb.append(String.format("stack-%d |", id));
			for(int i = 0; i < items.length; i++)
				sb.append(String.format(" %3s |", items[i] == null ? " " : items[i]));
			return sb.toString();
		}
	}
	public static void arrayImplementationTest() {
		ResizingArrayStack<Integer> stack = new ResizingArrayStack<Integer>();
		for(int i = 0; i < 10; i++)
			stack.push(i);
		for(int i = 0; i < 5; i++)
			stack.pop();
		ResizingArrayStack<Integer> stack2 = new ResizingArrayStack<Integer>(stack);
		for(int i = 0; i < 5; i++)
			stack2.pop();
		for(int i = 0; i < 10; i++)
			stack2.push(i);
	}
	/*
	 * linked list implementation
	 */
	static class LinkedListStack<T> {
		private class Node {
			T item;
			Node next;
			Node(T item, Node next) { this.item = item; this.next = next; }
		}
		private Node top = null;
		LinkedListStack() {}
		LinkedListStack(LinkedListStack<T> stack) {
			Node n = stack.top;
			while(n != null) {
				top = new Node(n.item, top);
				n = n.next;
			}
			Node reverse = null, first = top;
			while(first != null) {
				Node second = first.next;
				first.next = reverse;
				reverse = first;
				first = second;
			}
			top = reverse;
		}
		void push(T item) {
			top = new Node(item, top);
			StdOut.println(this);
		}
		T pop() {
			if (top == null)
				throw new RuntimeException("pop from a empty stack");
			T del = top.item;
			top = top.next;
			StdOut.println(this);
			return del;
		}
		String listString(Node n, String s) {
			if (n.next == null) 
				return "\n" + s + n.item;
			return "\n" + n.item + listString(n.next, s);
		}
		public String toString() {
			if (top == null) return "[empty]";
			StringBuilder sb = new StringBuilder();
			Node n = top;
			while(n.next != null) {
				sb.append(n.item + " -> ");
				n = n.next;
			}
			sb.append(n.item);
			return sb.toString();
		}
	}
	public static void listImplementationTest() {
		LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
		for(int i = 0; i < 10; i++)
			stack.push(i);
		for(int i = 0; i < 5; i++)
			stack.pop();
		LinkedListStack<Integer> stack2 = new LinkedListStack<Integer>(stack);
		for(int i = 0; i < 5; i++)
			stack2.pop();
		for(int i = 0; i < 10; i++)
			stack2.push(i);
	}
	public static void main(String[] args) {
		arrayImplementationTest();
		listImplementationTest();
	}
	// output
	/*
	 * 	stack-0 |   0 |
		stack-0 |   0 |   1 |
		stack-0 |   0 |   1 |   2 |     |
		stack-0 |   0 |   1 |   2 |   3 |
		stack-0 |   0 |   1 |   2 |   3 |   4 |     |     |     |
		stack-0 |   0 |   1 |   2 |   3 |   4 |   5 |     |     |
		stack-0 |   0 |   1 |   2 |   3 |   4 |   5 |   6 |     |
		stack-0 |   0 |   1 |   2 |   3 |   4 |   5 |   6 |   7 |
		stack-0 |   0 |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |     |     |     |     |     |     |     |
		stack-0 |   0 |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |     |     |     |     |     |     |
		stack-0 |   0 |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |     |     |     |     |     |     |     |
		stack-0 |   0 |   1 |   2 |   3 |   4 |   5 |   6 |   7 |     |     |     |     |     |     |     |     |
		stack-0 |   0 |   1 |   2 |   3 |   4 |   5 |   6 |     |     |     |     |     |     |     |     |     |
		stack-0 |   0 |   1 |   2 |   3 |   4 |   5 |     |     |     |     |     |     |     |     |     |     |
		stack-0 |   0 |   1 |   2 |   3 |   4 |     |     |     |     |     |     |     |     |     |     |     |
		stack-1 |   0 |   1 |   2 |   3 |     |     |     |     |
		stack-1 |   0 |   1 |   2 |     |     |     |     |     |
		stack-1 |   0 |   1 |     |     |
		stack-1 |   0 |     |
		[empty]
		stack-1 |   0 |     |
		stack-1 |   0 |   1 |
		stack-1 |   0 |   1 |   2 |     |
		stack-1 |   0 |   1 |   2 |   3 |
		stack-1 |   0 |   1 |   2 |   3 |   4 |     |     |     |
		stack-1 |   0 |   1 |   2 |   3 |   4 |   5 |     |     |
		stack-1 |   0 |   1 |   2 |   3 |   4 |   5 |   6 |     |
		stack-1 |   0 |   1 |   2 |   3 |   4 |   5 |   6 |   7 |
		stack-1 |   0 |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |     |     |     |     |     |     |     |
		stack-1 |   0 |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |     |     |     |     |     |     |
		0
		1 -> 0
		2 -> 1 -> 0
		3 -> 2 -> 1 -> 0
		4 -> 3 -> 2 -> 1 -> 0
		5 -> 4 -> 3 -> 2 -> 1 -> 0
		6 -> 5 -> 4 -> 3 -> 2 -> 1 -> 0
		7 -> 6 -> 5 -> 4 -> 3 -> 2 -> 1 -> 0
		8 -> 7 -> 6 -> 5 -> 4 -> 3 -> 2 -> 1 -> 0
		9 -> 8 -> 7 -> 6 -> 5 -> 4 -> 3 -> 2 -> 1 -> 0
		8 -> 7 -> 6 -> 5 -> 4 -> 3 -> 2 -> 1 -> 0
		7 -> 6 -> 5 -> 4 -> 3 -> 2 -> 1 -> 0
		6 -> 5 -> 4 -> 3 -> 2 -> 1 -> 0
		5 -> 4 -> 3 -> 2 -> 1 -> 0
		4 -> 3 -> 2 -> 1 -> 0
		3 -> 2 -> 1 -> 0
		2 -> 1 -> 0
		1 -> 0
		0
		[empty]
		0
		1 -> 0
		2 -> 1 -> 0
		3 -> 2 -> 1 -> 0
		4 -> 3 -> 2 -> 1 -> 0
		5 -> 4 -> 3 -> 2 -> 1 -> 0
		6 -> 5 -> 4 -> 3 -> 2 -> 1 -> 0
		7 -> 6 -> 5 -> 4 -> 3 -> 2 -> 1 -> 0
		8 -> 7 -> 6 -> 5 -> 4 -> 3 -> 2 -> 1 -> 0
		9 -> 8 -> 7 -> 6 -> 5 -> 4 -> 3 -> 2 -> 1 -> 0
	 */
}
