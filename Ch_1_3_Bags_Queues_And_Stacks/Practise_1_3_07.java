package Ch_1_3_Bags_Queues_And_Stacks;

import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;

public class Practise_1_3_07 {
    /*
     * 思路 :
     * 
     * 检查栈中是否有元素，没有就返回 null
     * 有就返回栈顶元素而不弹出
     * 
     * 
     */
	interface StackInterface<T> {
		void push(T item);
		T pop();
		T peek();
		boolean isEmpty();
	}
	static class Stack<T> implements StackInterface<T>, Iterable<T> {
		class Node {
			T item;
			Node next;
			Node(T item, Node next) { this.item = item; this.next = next; }
			Node(T item) { this(item, null); }
			Node() { this(null, null); }
		}
		private Node top;
		public void push(T item) {
			top = new Node(item, top);
		}
		public T peek() {
			if (isEmpty()) return null;
			return top.item;
		}
		public T pop() {
			if (isEmpty())
				throw new RuntimeException("stack is empty, you cannot pop a element");
			T pop = top.item;
			top = top.next;
			return pop;
		}
		public boolean isEmpty() { return top == null; }
		public Iterator<T> iterator() {
			return new Iterator<T>() {
				private Node n = top;
				public boolean hasNext() { return n != null; }
				public T next() {
					T ret = n.item;
					n = n.next;
					return ret;
				}
			};
		}
	}
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		for(int i = 0; i < 10; i++)
			stack.push(i);
		StdOut.println(stack.peek());
	}
	// output :
	/*
	 * 9
	 */
}
