package 第一章_背包_队列和栈;

import java.util.*;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_3_7 {
	interface StackInterface<T> {
		void push(T item);
		T pop();
		T peek();
		boolean isEmpty();
		boolean isFull();
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
		public boolean isEmpty() {
			return top == null;
		}
		public boolean isFull() {
			return false;
		}
		public Iterator<T> iterator() {
			return new Iterator<T>() {
				private Node n = top;
				public boolean hasNext() {
					return n != null;
				}
				public T next() {
					T ret = n.item;
					n = n.next;
					return ret;
				}
				public void remove() {
					throw new RuntimeException("不支持哦");
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
