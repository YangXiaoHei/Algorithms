package 第一章_背包_队列和栈;

import java.util.*;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_3_50 {
	static class Stack<T> implements Iterable<T> {
		private class Node {
			T item;
			Node prev, next;
			Node() {}
			Node(T item, Node prev, Node next) {
				this.item = item;
				this.prev = prev;
				this.next = next;
			}
			Node insertAfter(T item) {
				Node n = new Node(item, this, next);
				if (next != null)
					next.prev = n;
				next = n;
				return n;
			}
			T delete() {
				T del = item;
				item = null;
				if (prev != null)
					prev.next = next;
				if (next != null)
					next.prev = prev;
				return del;
			}
		}
		private Node header = new Node();
		private Node tailer = new Node();
		{
			header.next = tailer;
			tailer.prev = header;
			header.prev = tailer.next = null;
		}
		private int popCount = 0;
		private int pushCount = 0;
		boolean isEmpty() { return header.next == tailer; }
		void push(T item) { pushCount++; header.insertAfter(item); }
		T pop() {
			if (isEmpty()) 
				throw new RuntimeException("pop from a empty stack");
			popCount++;
			return header.next.delete();
		}
		public String toString() {
			if (isEmpty()) return "[empty]";
			StringBuilder sb = new StringBuilder();
			Node cur = header.next;
			while(cur.next != tailer) {
				sb.append(cur.item + " -> ");
				cur = cur.next;
			}
			sb.append(cur.item);
			return sb.toString();
		}
		public Iterator<T> iterator() {
			return new Iterator<T>() {
				private int popC = Stack.this.popCount;
				private int pushC = Stack.this.pushCount;
				private Node cur = header.next;
				public boolean hasNext() {
					if (popC != Stack.this.popCount || pushC != Stack.this.pushCount)
						throw new ConcurrentModificationException();
					return cur != tailer;
				}
				public T next() {
					if (popC != Stack.this.popCount || pushC != Stack.this.pushCount)
						throw new ConcurrentModificationException();
					T item = cur.item;
					cur = cur.next;
					return item;
				}
			};
		}
		public static void ConcurrentModificationExceptionTest() {
			Stack<Integer> stack = new Stack<Integer>();
			for (int i = 0; i < 10; i++)
				stack.push(i);
			StdOut.println(stack);
			
			for (Integer i : stack) {
				if (i == 6)
					stack.pop();
				else
					StdOut.println(i);
			}
		}
	}
	
	public static void main(String[] args) {
		Stack.ConcurrentModificationExceptionTest();
	}
	// output
	/*
	 * 	9 -> 8 -> 7 -> 6 -> 5 -> 4 -> 3 -> 2 -> 1 -> 0
		9
		8
		7
		Exception in thread "main" java.util.ConcurrentModificationException
			at 第一章_背包_队列和栈.Practise_1_3_50$Stack$1.hasNext(Practise_1_3_50.java:70)
			at 第一章_背包_队列和栈.Practise_1_3_50$Stack.ConcurrentModificationExceptionTest(Practise_1_3_50.java:88)
			at 第一章_背包_队列和栈.Practise_1_3_50.main(Practise_1_3_50.java:98)

	 */
}
