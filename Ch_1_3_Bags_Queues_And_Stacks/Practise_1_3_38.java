package Ch_1_3_Bags_Queues_And_Stacks;

import edu.princeton.cs.algs4.*;
/*
 * 思路 :
 * 
 * 对于 序列 "6 7 空 空 0 1 2 3 4 5"
 *              👆   👆
 *              tail head
 * 假如我们想要删除第 6 个元素，那么我们就从要删除元素的前一个元素开始，往后覆盖 6 次
 * 变为 5 7 空 空 0 0 1 2 3 4
 *         👆   👆
 *        tail head
 * 然后我们将 head 指向元素置为 null
 * 变为 5 7 空 空 空 0 1 2 3 4
 *         👆      👆
 *        tail    head
 * 接下来进行是否缩容的后续操作
 */
public class Practise_1_3_38 {
	interface GeneralizedQueue<T> {
		boolean isEmpty();
		void insert(T x);
		T delete(int k);
	}
	/*
	 * resizing array implementation
	 */
	static class ResizingArrayGeneralizedQueue<T> implements GeneralizedQueue<T> {
		@SuppressWarnings("unchecked")
		private T[] items = (T[])new Object[1];
		private int head;
		private int tail;
		private int size;
		ResizingArrayGeneralizedQueue() {}
		public boolean isEmpty() { return size == 0; }
		public void insert(T x) {
			if (size == items.length)
				resize(size * 2);
			size++;
			items[tail] = x;
			tail = (tail + 1) % items.length;
			StdOut.println(this);
		}
		public T delete(int k) {
			if (k >= size)
				throw new RuntimeException("index of " + k + "out of array bounds");
			int cur = (head + k) % items.length;
			T del = items[cur];
			for(int i = 0; i < k; i++) {
				int curIndex = cur--;
                if (cur < 0) cur = items.length - 1;
				int preIndex = curIndex - 1;
				if (preIndex < 0) preIndex = items.length - 1;
				items[curIndex] = items[preIndex];
			}
			items[head] = null;
			head = (head + 1) % items.length;
			size--;
			if (size > 0 && size == items.length / 4)
				resize(items.length / 2);
			StdOut.println(this + " delete : k = " + k + " value = " + del);
			return del;
		}
		void resize(int newsize) {
			@SuppressWarnings("unchecked")
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
		public String toString() {
			if (isEmpty()) return "[empty]";
			StringBuilder sb = new StringBuilder();
			sb.append("|");
			for(int i = 0; i < items.length; i++)
				sb.append(String.format("  %-3s|", items[i] == null ? " " : items[i]));
			return sb.toString();
		}
	}
	
	/*
	 * linked list implementation 
	 */
	static class LinkedListGeneralizedQueue<T> implements GeneralizedQueue<T> {
		private class Node {
			T item;
			Node prev;
			Node next;
			Node(T item, Node prev, Node next) {
				this.item = item;
				this.prev = prev;
				this.next = next;
			}
			Node() { this(null, null, null); }
			T delete() {
				T del = item;
				item = null;
				if (prev != null)
					prev.next = next;
				if (next != null)
					next.prev = prev;
				return del;
			}
			Node insertBefore(T item) {
				Node n = new Node(item, this.prev, this);
				if (prev != null)
					prev.next = n;
				prev = n;
				return n;
			}
		}
		private int size;
		private Node header = new Node();
		private Node tailer = new Node(); 
		{
			header.next = tailer;
			tailer.prev = header;
			header.prev = tailer.next = null;
		}
		public boolean isEmpty() { return size == 0; }
		public void insert(T item) {
			size++;
			tailer.insertBefore(item);
			StdOut.println(this);
		}
		public T delete(int k) {
			if (k >= size) 
				throw new RuntimeException("k " + k + " is out of array's bounds");
			int kk = k;
			Node cur = header.next;
			while(k-- > 0) cur = cur.next;
			size--;
			T del = cur.delete();
			StdOut.println(this + "          k = " + kk + " delete : " + del);
			return del;
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
	}
	public static void test() {
		GeneralizedQueue<Integer> queue = new ResizingArrayGeneralizedQueue<Integer>();
		for(int i = 0; i < 8; i++)
			queue.insert(i);
		for(int i = 0; i < 4; i++)
			queue.delete(0);
		for(int i = 8; i < 12; i++)
			queue.insert(i);
		queue.delete(0);
		queue.delete(4);
		queue.delete(2);
		queue.delete(3);
		queue.delete(1);
		queue.delete(2);
		queue.delete(1);
		queue.delete(0);
		
		
		queue = new LinkedListGeneralizedQueue<Integer>();
		for(int i = 0; i < 8; i++)
			queue.insert(i);
		for(int i = 0; i < 4; i++)
			queue.delete(0);
		for(int i = 8; i < 12; i++)
			queue.insert(i);
		queue.delete(0);
		queue.delete(4);
		queue.delete(2);
		queue.delete(3);
		queue.delete(1);
		queue.delete(2);
		queue.delete(1);
		queue.delete(0);
	}
	
	public static void main(String[] args) {
		test();
	}
	// output --> resizing array implementation
	/*
	 * 	|  0  |
		|  0  |  1  |
		|  0  |  1  |  2  |     |
		|  0  |  1  |  2  |  3  |
		|  0  |  1  |  2  |  3  |  4  |     |     |     |
		|  0  |  1  |  2  |  3  |  4  |  5  |     |     |
		|  0  |  1  |  2  |  3  |  4  |  5  |  6  |     |
		|  0  |  1  |  2  |  3  |  4  |  5  |  6  |  7  |
		|     |  1  |  2  |  3  |  4  |  5  |  6  |  7  | delete : k = 0 value = 0
		|     |     |  2  |  3  |  4  |  5  |  6  |  7  | delete : k = 0 value = 1
		|     |     |     |  3  |  4  |  5  |  6  |  7  | delete : k = 0 value = 2
		|     |     |     |     |  4  |  5  |  6  |  7  | delete : k = 0 value = 3
		|  8  |     |     |     |  4  |  5  |  6  |  7  |
		|  8  |  9  |     |     |  4  |  5  |  6  |  7  |
		|  8  |  9  |  10 |     |  4  |  5  |  6  |  7  |
		|  8  |  9  |  10 |  11 |  4  |  5  |  6  |  7  |
		|  8  |  9  |  10 |  11 |     |  5  |  6  |  7  | delete : k = 0 value = 4
		|  7  |  8  |  10 |  11 |     |     |  5  |  6  | delete : k = 4 value = 9
		|  6  |  8  |  10 |  11 |     |     |     |  5  | delete : k = 2 value = 7
		|  5  |  6  |  8  |  11 |     |     |     |     | delete : k = 3 value = 10
		|     |  5  |  8  |  11 |     |     |     |     | delete : k = 1 value = 6
		|  5  |  8  |     |     | delete : k = 2 value = 11
		|  5  |     | delete : k = 1 value = 8
		[empty] delete : k = 0 value = 5
	 */
	// output --> linked list implementation
	/*
	 * 	0
		0 -> 1
		0 -> 1 -> 2
		0 -> 1 -> 2 -> 3
		0 -> 1 -> 2 -> 3 -> 4
		0 -> 1 -> 2 -> 3 -> 4 -> 5
		0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6
		0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
		1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7          k = 0 delete : 0
		2 -> 3 -> 4 -> 5 -> 6 -> 7          k = 0 delete : 1
		3 -> 4 -> 5 -> 6 -> 7          k = 0 delete : 2
		4 -> 5 -> 6 -> 7          k = 0 delete : 3
		4 -> 5 -> 6 -> 7 -> 8
		4 -> 5 -> 6 -> 7 -> 8 -> 9
		4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10
		4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> 11
		5 -> 6 -> 7 -> 8 -> 9 -> 10 -> 11          k = 0 delete : 4
		5 -> 6 -> 7 -> 8 -> 10 -> 11          k = 4 delete : 9
		5 -> 6 -> 8 -> 10 -> 11          k = 2 delete : 7
		5 -> 6 -> 8 -> 11          k = 3 delete : 10
		5 -> 8 -> 11          k = 1 delete : 6
		5 -> 8          k = 2 delete : 11
		5          k = 1 delete : 8
		[empty]          k = 0 delete : 5
	 */
}
