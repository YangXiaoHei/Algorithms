package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.*;

public class Practise_1_3_29 {
	static class Node<T> {
		T item;
		Node<T> next;
		Node(T item) { this(item, null); }
		Node(T item, Node<T> next) {
			this.item = item;
			this.next = next;
		}
	}
	static class CircularList<T> {
		private Node<T> tail = null, head = null;
		private int size;
		boolean isEmpty() { return size == 0; }
		void insertLast(T item) {
			if (isEmpty()) {
				Node<T> node = new Node<T>(item);
				node.next = node;
				head = tail = node;
			} else {
				Node<T> node = new Node<T>(item);
				tail.next = node;
				node.next = head;
				tail = node;
			}
			size++;
		}
		T removeFirst() {
			if (isEmpty()) 
				throw new RuntimeException("empty circular list");
			T del = tail.next.item;
			tail.next.item = null;
			if (size == 1) {
				head = tail = null;
			} else {
				head = head.next;
				tail.next = tail.next.next;
			}
			size--;
			return del;
		}
		public String toString() {
			if (isEmpty()) return "empty list";
			Node<T> cur = head;
			StringBuilder sb = new StringBuilder();
			while (cur != tail) {
				sb.append(cur.item + " -> ");
				cur = cur.next;
			}
			sb.append(cur.item);
			return sb.toString();
		}
	}
	static class Queue<Q> extends CircularList<Q> {
		void enqueue(Q item) { insertLast(item); }
		Q dequeue() { return removeFirst(); }
		public String toString() { return super.toString(); }
	}
	public static void main(String[] args) {
		Queue<Integer> queue = new Queue<Integer>();
		for(int i = 0; i < 10; i++)
			queue.enqueue(i);
		StdOut.println(queue);
		for(int i = 0; i < 4; i++)
			queue.dequeue();
		StdOut.println(queue);
		for(int i = 99; i > 90; i--)
			queue.enqueue(i);
		StdOut.println(queue);
		for(int i = 0; i < 10; i++)
			queue.dequeue();
		StdOut.println(queue);
	}
	// output 
	/*
	 *	0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9
		4 -> 5 -> 6 -> 7 -> 8 -> 9
		4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 99 -> 98 -> 97 -> 96 -> 95 -> 94 -> 93 -> 92 -> 91
		95 -> 94 -> 93 -> 92 -> 91
	 */
	
}
