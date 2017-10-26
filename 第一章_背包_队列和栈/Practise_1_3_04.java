package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_3_04 {
	/*
	 * 使用链表实现的栈
	 */
	static class LinkedListStack<Item> {
		class Node {
			Item item;
			Node next;
			Node(Item item, Node next) { this.item = item; this.next = next; }
			Node(Item item) { this(item, null); }
			Node() { this(null, null); }
		}
		private Node top;
		void push(Item item) {
			top = new Node(item, top);
		}
		Item peek() {
			if (isEmpty()) return null;
			return top.item;
		}
		Item pop() {
			if (isEmpty())
				throw new RuntimeException("attempt to pop from a empty stack!");
			Item old = top.item;
			top = top.next;
			return old;
		}
		boolean isEmpty() {
			return top == null;
		}
		public String toString() {
			StringBuilder sb = new StringBuilder();
			Node n = top;
			sb.append("=== Top ===\n");
			while(n.next != null) {
				sb.append(n.item + "\n");
				n = n.next;
			}
			sb.append(n.item + "\n");
			sb.append("=== Bottom ===\n");
			return sb.toString();
		}
	}
	/*
	 * 检查括号是否匹配
	 */
	public static boolean isParenthesesPairing(String s) {
		LinkedListStack<String> stack = new LinkedListStack<String>();
		for(String ss : s.split("")) 
			if (stack.isEmpty())
				stack.push(ss);
			else if ((stack.peek().equals("(") && ss.equals(")")) ||
					 (stack.peek().equals("{") && ss.equals("}")) ||
					 (stack.peek().equals("[") && ss.equals("]")))
				stack.pop();
		return stack.isEmpty();
	}
	public static void main(String[] args) {
		String s1 = "[()]{}{[()()]()}";
		String s2 = "[(]]";
		StdOut.println(isParenthesesPairing(s1));
		StdOut.println(isParenthesesPairing(s2));
	}
	// output 
	/*
	 * 	true
		false
	 */
}
