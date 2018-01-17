package Ch_1_3_Bags_Queues_And_Stacks;

import java.util.*;
import edu.princeton.cs.algs4.*;

/*
 * 思路 :
 * 
 * 这里提供了基于可变数组的实现，以及链表的实现
 * 对于可变数组来说，只需要在构造器中拷贝一份数组的副本即可
 * 对于链表来说，需要对每个结点进行拷贝
 */
public class Practise_1_3_41 {
	/*
	 * resizing array implementation
	 */
	@SuppressWarnings("unchecked")
	static class ResizingArrayQueue<T> {
		private static int counter = 0;
		private final int id = counter++;
		private int head;
		private int tail;
		private int size;
		private T[] items = (T[])new Object[1];
		ResizingArrayQueue() {}
		ResizingArrayQueue(ResizingArrayQueue<T> queue) {
			this.head = queue.head;
			this.tail = queue.tail;
			this.size = queue.size;
			this.items = (T[])new Object[queue.items.length];
			for(int i = 0; i < items.length; i++)
				items[i] = queue.items[i];
		}
		void resize(int newsize) {
			T[] newItems = (T[])new Object[newsize];
			int cur = head, k = 0;
			do {
				newItems[k++] = items[cur];
				cur = (cur + 1) % items.length;
			} while(cur != tail);
			head = 0;
			tail = size;
			items = newItems;
		}
		void enqueue(T item) {
			if (item == null)
				throw new NullPointerException();
			if (size == items.length)
				resize(2 * items.length);
			size++;
			items[tail] = item;
			tail = (tail + 1) % items.length;
			StdOut.println(this);
		}
		T dequeue() {
			T del = items[head];
			items[head] = null;
			size--;
			head = (head + 1) % items.length;
			if (size > 0 && size == items.length / 4)
				resize(items.length / 2);
			StdOut.println(this);
			return del;
		}
		boolean isEmpty() { return size == 0; }
		public String toString() {
			if (isEmpty()) return "[empty]";
			StringBuilder sb = new StringBuilder();
			sb.append("queue-" + id);
			sb.append("|");
			for(int i = 0; i < items.length; i++)
				sb.append(String.format(" %3s |", items[i] == null ? " " : items[i]));
			sb.append(String.format("           <<<<<<<<<< size : %d head : %d tail : %d >>>>>>>>>>", size, head, tail));
			return sb.toString();
		}
	}
	public static void arrayImplementationTest() {
		ResizingArrayQueue<Integer> queue = new ResizingArrayQueue<Integer>();
		for(int i = 0; i < 10; i++)
			queue.enqueue(i);
		
		for(int i = 0; i < 5; i++)
			queue.dequeue();
		
		ResizingArrayQueue<Integer> queue2 = new ResizingArrayQueue<Integer>(queue);
		for(int i = 0; i < 5; i++)
			queue2.dequeue();
		
		for(int i = 0; i < 10; i++)
			queue2.enqueue(i);
		
	}
	
	/*
	 * linked list implementation
	 */
	static class LinkedListQueue<T> {
		private class Node {
			T item;
			Node next;
			Node(T item, Node next) { this.item = item; this.next = next; }
			Node() { this(null, null); }
			Node insertAfter(T item) {
				return next = new Node(item, next);
			}
		}
		private static int counter = 0;
		private final int id = counter++;
		private Node header = new Node();
		LinkedListQueue() {}
		LinkedListQueue(LinkedListQueue<T> queue) {
			Node cur = queue.header.next;
			while(cur != null) {
				Node curr = header;
				while(curr.next != null)
					curr = curr.next;
				curr.insertAfter(cur.item);
				cur = cur.next;
			}
		}
		void enqueue(T item) {
			Node cur = header;
			while(cur.next != null)
				cur = cur.next;
			cur.insertAfter(item);
			StdOut.println(this);
		}
		T dequeue() {
			if (header.next == null)
				throw new RuntimeException("dequeue from a empty queue");
			T del = header.next.item;
			header.next.item = null;
			header.next = header.next.next;
			StdOut.println(this);
			return del;
		}
		public String toString() {
			if (header.next == null) return "[empty]";
			StringBuilder sb = new StringBuilder();
			Node cur = header.next;
			sb.append(String.format("queue-%d :", id));
			while(cur.next != null) {
				sb.append(cur.item + " -> ");
				cur = cur.next;
			}
			sb.append(cur.item);
			return sb.toString();
		}
	}
	public static void listImplementationTest() {
		LinkedListQueue<Integer> queue = new LinkedListQueue<Integer>();
		for(int i = 0; i < 10; i++)
			queue.enqueue(i);
		
		for(int i = 0; i < 5; i++)
			queue.dequeue();

		LinkedListQueue<Integer> queue2 = new LinkedListQueue<Integer>(queue);
		for(int i = 0; i < 5; i++)
			queue2.dequeue();
		
		for(int i = 0; i < 10; i++)
			queue2.enqueue(i);
	}
	public static void main(String[] args) {
		arrayImplementationTest();
		listImplementationTest();
	}
	// output
	/*
	 * 	queue-0|   0 |           <<<<<<<<<< size : 1 head : 0 tail : 0 >>>>>>>>>>
		queue-0|   0 |   1 |           <<<<<<<<<< size : 2 head : 0 tail : 0 >>>>>>>>>>
		queue-0|   0 |   1 |   2 |     |           <<<<<<<<<< size : 3 head : 0 tail : 3 >>>>>>>>>>
		queue-0|   0 |   1 |   2 |   3 |           <<<<<<<<<< size : 4 head : 0 tail : 0 >>>>>>>>>>
		queue-0|   0 |   1 |   2 |   3 |   4 |     |     |     |           <<<<<<<<<< size : 5 head : 0 tail : 5 >>>>>>>>>>
		queue-0|   0 |   1 |   2 |   3 |   4 |   5 |     |     |           <<<<<<<<<< size : 6 head : 0 tail : 6 >>>>>>>>>>
		queue-0|   0 |   1 |   2 |   3 |   4 |   5 |   6 |     |           <<<<<<<<<< size : 7 head : 0 tail : 7 >>>>>>>>>>
		queue-0|   0 |   1 |   2 |   3 |   4 |   5 |   6 |   7 |           <<<<<<<<<< size : 8 head : 0 tail : 0 >>>>>>>>>>
		queue-0|   0 |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |     |     |     |     |     |     |     |           <<<<<<<<<< size : 9 head : 0 tail : 9 >>>>>>>>>>
		queue-0|   0 |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |     |     |     |     |     |     |           <<<<<<<<<< size : 10 head : 0 tail : 10 >>>>>>>>>>
		queue-0|     |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |     |     |     |     |     |     |           <<<<<<<<<< size : 9 head : 1 tail : 10 >>>>>>>>>>
		queue-0|     |     |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |     |     |     |     |     |     |           <<<<<<<<<< size : 8 head : 2 tail : 10 >>>>>>>>>>
		queue-0|     |     |     |   3 |   4 |   5 |   6 |   7 |   8 |   9 |     |     |     |     |     |     |           <<<<<<<<<< size : 7 head : 3 tail : 10 >>>>>>>>>>
		queue-0|     |     |     |     |   4 |   5 |   6 |   7 |   8 |   9 |     |     |     |     |     |     |           <<<<<<<<<< size : 6 head : 4 tail : 10 >>>>>>>>>>
		queue-0|     |     |     |     |     |   5 |   6 |   7 |   8 |   9 |     |     |     |     |     |     |           <<<<<<<<<< size : 5 head : 5 tail : 10 >>>>>>>>>>
		queue-1|   6 |   7 |   8 |   9 |     |     |     |     |           <<<<<<<<<< size : 4 head : 0 tail : 4 >>>>>>>>>>
		queue-1|     |   7 |   8 |   9 |     |     |     |     |           <<<<<<<<<< size : 3 head : 1 tail : 4 >>>>>>>>>>
		queue-1|   8 |   9 |     |     |           <<<<<<<<<< size : 2 head : 0 tail : 2 >>>>>>>>>>
		queue-1|   9 |     |           <<<<<<<<<< size : 1 head : 0 tail : 1 >>>>>>>>>>
		[empty]
		queue-1|     |   0 |           <<<<<<<<<< size : 1 head : 1 tail : 0 >>>>>>>>>>
		queue-1|   1 |   0 |           <<<<<<<<<< size : 2 head : 1 tail : 1 >>>>>>>>>>
		queue-1|   0 |   1 |   2 |     |           <<<<<<<<<< size : 3 head : 0 tail : 3 >>>>>>>>>>
		queue-1|   0 |   1 |   2 |   3 |           <<<<<<<<<< size : 4 head : 0 tail : 0 >>>>>>>>>>
		queue-1|   0 |   1 |   2 |   3 |   4 |     |     |     |           <<<<<<<<<< size : 5 head : 0 tail : 5 >>>>>>>>>>
		queue-1|   0 |   1 |   2 |   3 |   4 |   5 |     |     |           <<<<<<<<<< size : 6 head : 0 tail : 6 >>>>>>>>>>
		queue-1|   0 |   1 |   2 |   3 |   4 |   5 |   6 |     |           <<<<<<<<<< size : 7 head : 0 tail : 7 >>>>>>>>>>
		queue-1|   0 |   1 |   2 |   3 |   4 |   5 |   6 |   7 |           <<<<<<<<<< size : 8 head : 0 tail : 0 >>>>>>>>>>
		queue-1|   0 |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |     |     |     |     |     |     |     |           <<<<<<<<<< size : 9 head : 0 tail : 9 >>>>>>>>>>
		queue-1|   0 |   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |     |     |     |     |     |     |           <<<<<<<<<< size : 10 head : 0 tail : 10 >>>>>>>>>>
		queue-0 :0
		queue-0 :0 -> 1
		queue-0 :0 -> 1 -> 2
		queue-0 :0 -> 1 -> 2 -> 3
		queue-0 :0 -> 1 -> 2 -> 3 -> 4
		queue-0 :0 -> 1 -> 2 -> 3 -> 4 -> 5
		queue-0 :0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6
		queue-0 :0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
		queue-0 :0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8
		queue-0 :0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9
		queue-0 :1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9
		queue-0 :2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9
		queue-0 :3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9
		queue-0 :4 -> 5 -> 6 -> 7 -> 8 -> 9
		queue-0 :5 -> 6 -> 7 -> 8 -> 9
		queue-1 :6 -> 7 -> 8 -> 9
		queue-1 :7 -> 8 -> 9
		queue-1 :8 -> 9
		queue-1 :9
		[empty]
		queue-1 :0
		queue-1 :0 -> 1
		queue-1 :0 -> 1 -> 2
		queue-1 :0 -> 1 -> 2 -> 3
		queue-1 :0 -> 1 -> 2 -> 3 -> 4
		queue-1 :0 -> 1 -> 2 -> 3 -> 4 -> 5
		queue-1 :0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6
		queue-1 :0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
		queue-1 :0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8
		queue-1 :0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9
	 */
}
