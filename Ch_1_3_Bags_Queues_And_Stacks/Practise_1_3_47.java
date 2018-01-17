package Ch_1_3_Bags_Queues_And_Stacks;

import edu.princeton.cs.algs4.StdOut;

/*
 * 思路 : 
 * 
 * 只需要将其中某个栈，队列或 Steque 的尾结点指向另一个栈，队列或 Steque 的首结点即可
 * 
 */
public class Practise_1_3_47 {
	static class Node<T> {
		T item;
		Node<T> prev;
		Node<T> next;
		Node(T item, Node<T> prev, Node<T> next) {
			this.item = item;
			this.prev = prev;
			this.next = next;
		}
		Node() {}
		Node<T> insertAfter(T item) {
			Node<T> n = new Node<T>(item, this, next);
			if (next != null)
				next.prev = n;
			next = n;
			return n;
		}
		Node<T> insertBefore(T item) {
			Node<T> n = new Node<T>(item, prev, this);
			if (prev != null)
				prev.next = n;
			prev = n;
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
	static class Queue<T> {
		private Node<T> header = new Node<T>();
		private Node<T> tailer = new Node<T>();
		{
			header.next = tailer;
			tailer.prev = header;
			header.prev = tailer.next = null;
		}
		public void enqueue(T item) { tailer.insertBefore(item); }
		public boolean isEmpty() { return header.next == tailer; }
		public T dequeue() {
			if (isEmpty())
				throw new RuntimeException("dequeue from a empty queue");
			return header.next.delete();
		}
		public void catenation(Queue<T> queue) {
			tailer.prev.next = queue.header.next;
			queue.tailer.prev.next = tailer;
		}
		public String toString() {
			if (isEmpty()) return "[empty]";
			StringBuilder sb = new StringBuilder();
			Node<T> cur = header.next;
			while(cur.next != tailer) {
				sb.append(cur.item + " -> ");
				cur = cur.next;
			}
			sb.append(cur.item);
			return sb.toString();
		}
		public static void queueTest() {
			Queue<Integer> queue = new Queue<Integer>();
			for(int i = 0; i < 10; i++)
				queue.enqueue(i);
			Queue<Integer> queue2 = new Queue<Integer>();
			for(int i = 90; i < 100; i++)
				queue2.enqueue(i);
			queue.catenation(queue2);
			StdOut.println(queue);
		}
	}
	
	static class Stack<T> {
		private Node<T> header = new Node<T>();
		private Node<T> tailer = new Node<T>();
		{
			header.next = tailer;
			tailer.prev = header;
			header.prev = tailer.next = null;
		}
		public boolean isEmpty() { return header.next == tailer; }
		public void push(T item) { header.insertAfter(item); }
		public T pop() {
			if (isEmpty())
				throw new RuntimeException("pop from a empty stack");
			return header.next.delete();
		}
		public void catenation(Stack<T> stack) {
			tailer.prev.next = stack.header.next;
			stack.tailer.prev.next = tailer;
		}
		public String toString() {
			if (isEmpty()) return "[empty]";
			StringBuilder sb = new StringBuilder();
			Node<T> cur = header.next;
			while(cur.next != tailer) {
				sb.append(cur.item + " -> ");
				cur = cur.next;
			}
			sb.append(cur.item);
			return sb.toString();
		}
		public static void stackTest() {
			Stack<Integer> stack = new Stack<Integer>();
			for(int i = 0; i < 10; i++)
				stack.push(i);
			Stack<Integer> stack2 = new Stack<Integer>();
			for(int i = 90; i < 100; i++)
				stack2.push(i);
			stack.catenation(stack2);
			StdOut.println(stack);
		}
	}
	
	static class Steque<T> {
		private Node<T> header = new Node<T>();
		private Node<T> tailer = new Node<T>();
		{
			header.next = tailer;
			tailer.prev = header;
			header.prev = tailer.next = null;
		}
		public boolean isEmpty() { return header.next == tailer; }
		public void push(T item) { header.insertAfter(item); }
		public T pop() {
			if (isEmpty())
				throw new RuntimeException("pop from a empty steque");
			return header.next.delete();
		}
		public void enqueue(T item) { tailer.insertBefore(item); }
		public T dequeue() { return header.next.delete(); }
		public void catenation(Steque<T> steque) {
			tailer.prev.next = steque.header.next;
			steque.tailer.prev.next = tailer;
		}
		public String toString() {
			if (isEmpty()) return "[empty]";
			StringBuilder sb = new StringBuilder();
			Node<T> cur = header.next;
			while(cur.next != tailer) {
				sb.append(cur.item + " -> ");
				cur = cur.next;
			}
			sb.append(cur.item);
			return sb.toString();
		}
		public static void stequeTest() {
			Steque<Integer> s1 = new Steque<Integer>();
			for(int i = 0; i < 10; i++)
				s1.push(i);
			for(int i = 10; i < 20; i++)
				s1.enqueue(i);
			for(int i = 0; i < 5; i++)
				s1.dequeue();
			StdOut.println(s1);
			
			Steque<Integer> s2 = new Steque<Integer>();
			for(int i = 90; i < 100; i++)
				s2.enqueue(i);
			for(int i = 0; i < 3; i++)
				s2.pop();
			
			s1.catenation(s2);
			StdOut.println(s1);
		}
	}
	
	public static void main(String[] args) {
		Queue.queueTest();
		Stack.stackTest();
		Steque.stequeTest();
 	}
	// output
	/*
	 * 	0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 90 -> 91 -> 92 -> 93 -> 94 -> 95 -> 96 -> 97 -> 98 -> 99
		9 -> 8 -> 7 -> 6 -> 5 -> 4 -> 3 -> 2 -> 1 -> 0 -> 99 -> 98 -> 97 -> 96 -> 95 -> 94 -> 93 -> 92 -> 91 -> 90
		4 -> 3 -> 2 -> 1 -> 0 -> 10 -> 11 -> 12 -> 13 -> 14 -> 15 -> 16 -> 17 -> 18 -> 19
		4 -> 3 -> 2 -> 1 -> 0 -> 10 -> 11 -> 12 -> 13 -> 14 -> 15 -> 16 -> 17 -> 18 -> 19 -> 93 -> 94 -> 95 -> 96 -> 97 -> 98 -> 99

	 */
}
