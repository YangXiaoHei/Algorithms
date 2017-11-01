package 第一章_背包_队列和栈;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Practise_1_3_45 {
	static class Stack<T> {
		private class Node {
			T item;
			Node next;
			Node(T item, Node next) { this.item = item; this.next = next; }
			Node insertAfter(T item) {
				Node n = new Node(item, next);
				next = n;
				return n;
			}
		}
		private Node top = null;
		private int size;
		public int size() { return size; }
		public boolean isEmpty() { return top == null; }
		public void push(T item) { top = new Node(item, top); size++; }
		public T peek() {
			if (isEmpty()) 
				return null;
			return top.item;
		}
		public T pop() {
			if (isEmpty())
				throw new RuntimeException("pop from a empty stack");
			size--;
			T del = top.item;
			top.item = null;
			top = top.next;
			return del;
		}
		public String toString() {
			if (isEmpty()) return "[empty]";
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
	static String[] operations = new String[20];
	static String[] numbers = new String[10];
	static {
		for(int i = 0; i < 10; i++)
			operations[i] = i + "";
		for(int i = 10; i < 20; i++)
			operations[i] = "-";
		for(int i = 0; i < numbers.length; i++)
			numbers[i] = i + "";
	}
	public static String[] shuffle(String[] operations) {
		for(int i = 0; i < operations.length; i++) {
			int r = i + StdRandom.uniform(operations.length - i);
			String temp = operations[r];
			operations[r] = operations[i];
			operations[i] = temp;
		}
		return operations;
	}
	/*
	 * if stack operations cause underflow
	 */
	public static boolean isStackOperationsUnderflow(String[] optrs) {
		int count = 0;
		for (String s : optrs) {
			if (s.equals("-")) 
				count--;
			else
				count++;
			if (count < 0) return true;
		}
		return false;
	}
	/*
	 * if the specified sequence can be created by stack operations
	 */
	public static void isSequenceValid(String[] sequence) {
		for (String s : sequence)
			StdOut.print(s + " ");
		StdOut.print(" : ");
		Stack<String> stack = new Stack<String>();
		int i = 0, j = 0;
		while(i < sequence.length && j <= sequence.length) {
			if (!sequence[i].equals(stack.peek())) 
				stack.push(Integer.toString(j++));
		    else {
				stack.pop();
				i++;
			}
		}
		StdOut.println(stack.isEmpty()); ;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			String[] optrs = shuffle(operations);
			StdOut.println(Arrays.toString(optrs) + " : " + isStackOperationsUnderflow(optrs));
		}
		isSequenceValid("4 3 2 1 0 9 8 7 6 5".split(" "));
		isSequenceValid("4 6 8 7 5 3 2 9 0 1".split(" "));
		isSequenceValid("2 5 6 7 4 8 9 3 1 0".split(" "));
		isSequenceValid("4 3 2 1 0 5 6 7 8 9".split(" "));
		isSequenceValid("1 2 3 4 5 6 9 8 7 0".split(" "));
		isSequenceValid("0 4 6 5 3 8 1 7 2 9".split(" "));
		isSequenceValid("1 4 7 9 8 6 5 3 0 2".split(" "));
		isSequenceValid("2 1 4 3 6 5 8 7 9 0".split(" "));	
	}
	// output
	/*
	 * 	[0, -, -, -, 8, 3, 5, 4, -, 6, 7, -, -, -, -, -, 1, 2, 9, -] : true
		[-, -, -, -, 2, 7, -, -, -, -, 3, -, 9, 0, -, 1, 6, 8, 4, 5] : true
		[-, 4, -, 3, -, 2, -, -, 1, -, -, -, 7, 9, -, 6, 5, 0, -, 8] : true
		[3, -, -, 2, 8, -, -, -, 9, 1, 4, 7, 6, -, 0, -, -, 5, -, -] : true
		[8, 9, 2, -, -, 0, 5, 4, 7, -, -, 6, 3, -, 1, -, -, -, -, -] : false
		[8, -, 1, 2, -, 0, -, 6, 9, -, -, 4, 5, -, 7, 3, -, -, -, -] : false
		[-, 4, 9, 8, 3, 7, -, -, 2, 0, -, -, -, -, -, -, 5, 1, 6, -] : true
		[1, -, 9, 0, -, -, 7, -, 5, 8, 2, 6, -, 4, -, -, -, -, 3, -] : false
		[5, -, -, -, 4, 8, 2, -, -, 0, 3, -, 1, 6, 9, -, -, -, -, 7] : true
		[5, -, -, -, -, 6, 7, -, 0, -, 1, -, 2, -, 4, -, 8, -, 9, 3] : true
		4 3 2 1 0 9 8 7 6 5  : true
		4 6 8 7 5 3 2 9 0 1  : false
		2 5 6 7 4 8 9 3 1 0  : true
		4 3 2 1 0 5 6 7 8 9  : true
		1 2 3 4 5 6 9 8 7 0  : true
		0 4 6 5 3 8 1 7 2 9  : false
		1 4 7 9 8 6 5 3 0 2  : false
		2 1 4 3 6 5 8 7 9 0  : true
	 */
}
