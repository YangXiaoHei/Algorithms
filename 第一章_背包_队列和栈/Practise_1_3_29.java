package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.*;

public class Practise_1_3_29 {
	static class Node<T> {
		T item;
		Node<T> next;
		Node(T item, Node<T> next) {
			this.item = item;
			this.next = next;
		}
		Node(T item) { this(item, null); }
	}
	static class CircularList<T> {
		private Node<T> head = null;
		private int size;
		void insertLast(T item) {
			if (head == null) {
				Node<T> node = new Node<T>(item);
				node.next = node;
				head = node;
			} else {
				Node<T> tmp = head;
				for(int i = 1; i < size;i ++)
					tmp = tmp.next;
				Node<T> node = new Node<T>(item);
				tmp.next = node;
				node.next = head;
			}
			size++;
		}
		T removeFirst() {
			if (size == 0) 
				throw new RuntimeException("empty circular list");
			T del = head.item;
			if (size == 1) {
				head = null;
			} else {
				head.item = null;
				head = head.next;
				Node<T> tmp = head;
				for(int i = 1; i < size - 1; i++)
					tmp = tmp.next;
				tmp.next = head;
			}
			size--;
			return del;
		}
		public String toString() {
			StringBuilder sb = new StringBuilder();
			Node<T> node = head;
			for(int i = 0; i < size - 1; i++) {
				sb.append(node.item + " -> ");
				node = node.next;
			}
			sb.append(node.item);
			return sb.toString();
		}
	}
	static class Queue<Q> extends CircularList<Q> {
		void enqueue(Q item) {
			insertLast(item);
		}
		Q dequeue() {
			return removeFirst();
		}
		public String toString() {
			return super.toString();
		}
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
