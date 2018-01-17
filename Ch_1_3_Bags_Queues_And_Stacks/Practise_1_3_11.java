package Ch_1_3_Bags_Queues_And_Stacks;

import java.util.*;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_3_11 {
	/*
	 * 思路 :
	 * 
	 * 准备一个操作数栈，遍历整个表达式
	 * 
	 * 如果读到运算符，就在操作数栈中弹两个元素出来，把运算后结果放回操作数栈
	 * 如果读到操作数，就将其放到操作数栈中
	 * 最后操作数栈顶元素即为最终结果
	 * 
	 */
	static class Stack<T> {
		@SuppressWarnings("unchecked")
		T[] item = (T[])new Object[1];
		private int N;
		void resize(int size) {
			@SuppressWarnings("unchecked")
			T[] newItem = (T[])new Object[size];
			for(int i = 0; i < N; i++) {
				newItem[i] = item[i];
			}
			item = newItem;
		}
		void push(T e) {
			if (N == item.length)
				resize(N * 2);
			item[N++] = e;
		}
		T pop() {
			T pop = item[--N];
			item[N] = null;
			if (N > 0 && N == item.length / 4)
				resize(item.length / 2);
			return pop;
		}
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("=== Top ===\n");
			for(int i = N - 1; i >= 0; i--) {
				sb.append(item[i] + "\n");
			}
			sb.append("=== Bottom ===\n");
			return sb.toString();
		}
	}
	public static boolean isOpnd(String s) {
		return s.charAt(0) >= '0' && s.charAt(0) <= '9';
	}
	/*
	 * 通过后序表达式计算结果值
	 */
	public static double resultFromPostfix(String postfix) {
		Stack<String> stack = new Stack<String>();
		for(String s : postfix.split(" ")) {
			if (!isOpnd(s)) {
				double opnd2 = Double.parseDouble(stack.pop());
				double opnd1 = Double.parseDouble(stack.pop());
				if (s.equals("+")) {
					stack.push((opnd1 + opnd2) + "");
				} else if (s.equals("-")) {
					stack.push((opnd1 - opnd2) + "");
				}  else if (s.equals("*")) {
					stack.push((opnd1 * opnd2) + "");
				} else if (s.equals("/")) {
					stack.push((opnd1 / opnd2) + "");
				}
			} else {
				stack.push(s);
			}
		}
		return Double.parseDouble(stack.pop());
	}
	public static void main(String[] args) {
		StdOut.println(resultFromPostfix("11 2 3 * 4 5 + / + 9 8 3 + * -"));
		StdOut.println(resultFromPostfix("2 3 * 2 1 - / 3 4 1 - * +"));
	}
	// output 
	/*
	 * 	-87.33333333333333
		15.0
	 */
}
