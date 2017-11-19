package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_3_04 {
	/*
	 *  思路 :
	 *  
	 *  每读到一个左括号，将其压入栈中，每读到一个右括号，就去看一下栈顶元素是否是与之匹配的左括号
	 *  如果是，就将栈顶元素弹出，继续读表达式的下一个元素，如果不是，继续读表达式的下一个元素
	 *  在整体表达式读完后，判断栈是否为空，为空说明全部括号都匹配
	 *  
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
		boolean isEmpty() { return top == null; }
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
