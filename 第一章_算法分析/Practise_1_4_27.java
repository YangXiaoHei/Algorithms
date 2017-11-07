package 第一章_算法分析;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_4_27 {
	static class Stack<T> {
		private class Node {
			T item;
			Node next;
			Node prev;
			Node() {}
			Node(T item, Node prev, Node next) { 
				this.item = item;  
				this.next = next;
				this.prev = prev;
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
		private int size;
		{
			header.next = tailer;
			tailer.prev = header;
			header.prev = tailer.next = null;
		}
		int size() { return size; }
		boolean isEmpty() { return size == 0; }
		void push(T item) {
			size++;
			header.insertAfter(item);
		}
		T pop() {
			if (isEmpty())
				throw new RuntimeException("pop from a empty stack");
			size--;
			return header.next.delete();
		}
		public String toString() {
			if (isEmpty()) return "empty stack";
			Node cur = header.next;
			StringBuilder sb = new StringBuilder();
			while (cur.next != tailer) {
				sb.append(cur.item + " -> ");
				cur = cur.next;
			}
			sb.append(cur.item);
			return sb.toString();
		}
	}
	static class Queue<T> {
		private Stack<T> stack1 = new Stack<T>(); 
		private Stack<T> stack2 = new Stack<T>();
		boolean isEmpty() { return stack1.isEmpty() && stack2.isEmpty(); }
		int size() { return stack1.size() + stack2.size(); }
		void enqueue(T item) {
			stack2.push(item);
		}
		T dequeue() {
			if (isEmpty())
				throw new RuntimeException("dequeue from a empty queue");
			if (!stack1.isEmpty())
				return stack1.pop();
			while (!stack2.isEmpty())
				stack1.push(stack2.pop());
			return stack1.pop();
		}
	}
	public static void main(String[] args) {
		Queue<Integer> queue = new Queue<Integer>();
		for (int i = 0; i < 10; i++)
			queue.enqueue(i);
		StdOut.printf("Queue's size is %d\n", queue.size());
		while (!queue.isEmpty())
			StdOut.printf("dequeue %d from queue, now size is %d\n", 
					queue.dequeue(), queue.size());
	}
	// output
	/*
	 * 	Queue's size is 10
		dequeue 0 from queue, now size is 9
		dequeue 1 from queue, now size is 8
		dequeue 2 from queue, now size is 7
		dequeue 3 from queue, now size is 6
		dequeue 4 from queue, now size is 5
		dequeue 5 from queue, now size is 4
		dequeue 6 from queue, now size is 3
		dequeue 7 from queue, now size is 2
		dequeue 8 from queue, now size is 1
		dequeue 9 from queue, now size is 0

	 */
}
