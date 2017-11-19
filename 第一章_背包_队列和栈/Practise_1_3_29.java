package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.*;

public class Practise_1_3_29 {
    /*
     * 思路 :
     * 
     * 用环形链表实现 Queue,因此我们可以实现具备了完整 Queue 功能的环形链表，
     * 再让 Queue 继承环形链表实现外部 API 即可
     * 
     * 队列需要在尾部添加元素，在头部删除元素，
     * 所以我们为环形链表提供了 removeFirst 和 insertAsLast API
     * 同时我们使用两个指针变量来记录环形链表的头结点和尾结点
     * 当在环形链表中插入结点时，若此时链表为空，则让插入结点指向自身，并将其作同时作为头结点和尾结点
     * 若链表不为空，我们让插入的结点指向头结点，并让尾结点指向它，实现闭环效果
     * 当在环形链表中删除头结点时，我们首先让头结点指向它的下一个结点，然后让尾结点指向此时的头结点
     * 若在只有一个元素的环形链表中删除元素，那么，我们只需将 头尾指针指向空即可
     * 可以看出，使用环形链表实现的队列，入队和出队都是 O(1) 操作
     * 
     */
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
		void insertAsLast(T item) {
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
		void enqueue(Q item) { insertAsLast(item); }
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
