package Ch_1_3_Bags_Queues_And_Stacks;

import java.util.*;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_3_12 {
	/*
	 * 思路 :
	 * 
	 * 用数组实现的栈拷贝功能很简单，下面说一下用链表实现如何拷贝
	 * 基本思路是遍历链表中的每个结点，并将其 push 到新的 栈中
	 * 原栈为 
	 * 栈顶 4 -> 3 -> 2 -> 1 -> 0 栈底
	 * 假如直接用 top 开始依次拷贝，我们只能得到一个逆序的副本
	 * 栈顶 0 -> 1 -> 2 -> 3 -> 4 栈底
	 * 因此我们准备一个逆序的迭代器，在对原栈的遍历中，就可以从栈底元素开始 push 到新栈
	 * 从而得到
	 * 新栈栈顶 4 -> 3 -> 2 -> 1 -> 0
	 * 以下是双向链表的实现
	 */
	static class Stack<T> implements Iterable<T> {
		private static int counter = 0;
		private final int id = counter++;
		class Node {
			T item;
			Node next;
			Node prev;
			Node(T it, Node ne, Node pr) { item = it; next = ne; prev = pr; }
			Node(T it) { this(it, null, null); }
			Node() { this(null, null, null); }
		}
		private Node top = null;
		void push(T item) {
			if (top == null) {
				top = new Node(item);
			} else {
				Node n = new Node(item);
				n.next = top;
				top.prev = n;
				top = n;
			}
		}
		T pop() {
			if(isEmpty()) 
				throw new RuntimeException("empty stack!");
			T pop = top.item;
			top = top.next;
			top.prev = null;
			return pop;
		}
		boolean isEmpty() {
			return top == null;
		}
		public Iterator<T> iterator() {
			return new Iterator<T>() {
				private Node n = top;
				public boolean hasNext() {
					return n != null;
				}
				public T next() {
					T item = n.item;
					n = n.next;
					return item;
				}
				public void remove() {
					throw new UnsupportedOperationException();
				}
			};
		}
		public Iterable<T> reverse() {
			return new Iterable<T>() {
				public Iterator<T> iterator() {
					return new Iterator<T>() {
						Node n;
						{
							Node cur = top;
							while(cur != null && cur.next != null)
								cur = cur.next;
							n = cur;
						}
						public boolean hasNext() {
							return n != null;
						}
						public T next() {
							T item = n.item;
							n = n.prev;
							return item;
						}
					};
				}
			};
		}
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("=== Stack 【" + id + "】 ===\n");
			for(T t : this)
				sb.append(t + "\n");
			sb.append("=== Bottom ===\n");
			return sb.toString();
		}
		/*
		 * 复制
		 */
		public static Stack<String> copy(Stack<String> source) {
			Stack<String> copy = new Stack<String>();
			for(String s : source.reverse())
				copy.push(s);
			return copy;
		}
	}
	public static void main(String[] args) {
		Stack<String> s = new Stack<String>();
		for(int i = 0; i < 10; i++)
			s.push(i + "");
		StdOut.println("被复制的栈 :");
		StdOut.println(s);
		
		StdOut.println("复制出来的栈 :");
		Stack<String> copy = Stack.copy(s);
		StdOut.println(copy);
	}
	// output
	/*
	 *  被复制的栈 :
        === Stack 【0】 ===
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
        
        复制出来的栈 :
        === Stack 【1】 ===
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
	 */
}
