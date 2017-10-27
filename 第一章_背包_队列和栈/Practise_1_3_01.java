package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_3_01 {
	/*
	 * 数组实现一个固定容量的栈
	 */
	static class FixedCapacityStackOfStrings {
		private String[] strings;
		private int N;
		FixedCapacityStackOfStrings(int max) {
			strings = new String[max];
		}
		void push(String s) {
			if (isFull())
				throw new RuntimeException("stack is full!");
			strings[N++] = s;
		}
		boolean isFull() {
			return N == strings.length;
		}
		boolean isEmpty() {
			return N == 0;
		}
		String pop() {
			if(isEmpty())
				throw new RuntimeException("attempt to pop in a empty stack!");
			String s = strings[--N];
			strings[N] = null;
			return s;
		}
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("=== Top ===\n");
			int i = N;
			while(--i >= 0)
				sb.append(strings[i] + "\n");
			sb.append("=== Bottom ===");
			return sb.toString();
		}
	}
	public static void main(String[] args) {
		FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(10);
		for(int i = 0; i < 10; i++)
			stack.push(Integer.toString(i));
		StdOut.println(stack);
		StdOut.println("push new elem into a full stack");
		stack.push("99");
	}
	// output : execute to see how exception be thrown
	/*
	 * === Top ===
		9
		8
		7
		6
		5
		4
		3
		2
		1
		0
		=== Bottom ===
		push new elem into a full stack
		Exception in thread "main" java.lang.RuntimeException: stack is full!
			at 第一章_背包_队列和栈.Practise_1_3_1$FixedCapacityStackOfStrings.push(Practise_1_3_1.java:17)
			at 第一章_背包_队列和栈.Practise_1_3_1.main(Practise_1_3_1.java:47)
	 */
}
