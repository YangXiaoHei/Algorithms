package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_3_02 {
	/*
	 * 思路 :
	 * 
	 * was
	 * it
	 */
    /*
     * 
     * 
     * (was 被弹出)
     * it
     */  //was
    /*
     * 
     * (best 被弹出)
     * the
     * it 
     */ // was best
    /*
     * (times 被弹出)
     * (of 被弹出)
     * (the 被弹出)
     * it 
     */ // was best times of the
    /*
     * 
     * (was 被弹出)
     * it
     * it
     */ // was best times of the was
    /*
     * (the 被弹出)
     * (it 被弹出)
     * it
     */ // was best times of the was the it
	static class LinkedListStack<Item> {
		class Node {
			Item item;
			Node next;
			Node() { this(null, null); }
			Node(Item it) { this(it, null); }
			Node(Item it, Node ne) { item = it; next = ne; }
		}
		private Node top;
		void push(Item item) {
			top = new Node(item, top);
		}
		Item pop() {
			if(top == null)
				throw new RuntimeException("attempt to pop in a empty stack");
			Item old = top.item;
			top = top.next;
			return old;
		}
		public String toString() {
			Node n = top;
			StringBuilder sb = new StringBuilder();
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
	 * 测试用例
	 */
	public static void stackTest() {
		LinkedListStack<String> stack = new LinkedListStack<String>();
		for(int i = 0; i < 10; i++)
			stack.push(i + "");
		StdOut.println(stack);
		for(int i = 0; i < 7; i++)
			stack.pop();
		StdOut.println(stack);
	}
	/*
	 * 输出打印
	 */
	public static void inputPrint() {
		LinkedListStack<String> stack = new LinkedListStack<String>();
		for(String s : "it was - the best - of times - - - it was - the - -".split(" "))
			if (s.equals("-"))
				StdOut.print(stack.pop() + " ");
			else
				stack.push(s);
	}
	public static void main(String[] args) {
		inputPrint();
	}
	// output 
	/*
	 * was best times of the was the it 
	 */
}
