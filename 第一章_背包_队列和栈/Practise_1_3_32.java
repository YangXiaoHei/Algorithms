package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.*;

public class Practise_1_3_32 {
    /*
     * 思路 :
     * 
     * 在链表内部使用一对虚拟的头结点和尾结点
     * 并在结点内部封装 insertAfter insertBefore 方法
     * 让我们可以在 O(1) 时间内完成删除头，删除尾，在尾部添加 和 在头部删除等操作，而无需遍历定位头尾结点位置
     */
	static class Steque<T> {
		private class Node {
			T item;
			Node next;
			Node prev;
			Node() {}
			Node(T item, Node prev, Node next) {
				this.item = item;
				this.prev = prev;
				this.next = next;
			}
			Node insertAfter(T item) {
				Node n = new Node(item, this, this.next);
				if (next != null)
					next.prev = n;
				next = n;
				return n;
			}
			Node insertBefore(T item) {
				Node n = new Node(item, this.prev, this);
				if (prev != null)
					prev.next = n;
				prev = n;
				return n;
			}
			T delete() {
				T del = item;
				item = null;
				if (next != null)
					next.prev = prev;
				if (prev != null)
					prev.next = next;
				return del;
			}
		}
		private Node header = new Node();
		private Node tailer = new Node();
		private int size;
		{
			header.next = tailer;
			tailer.prev = header;
			header.prev = null;
			tailer.next = null;
		}
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
		boolean isEmpty() { return size == 0; }
		void enqueue(T item) {
			size++;
			tailer.insertBefore(item);
		}
		T dequeue() {
			if (isEmpty())
				throw new RuntimeException("dequeue from a empty queue");
			size--;
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
	}
	public static void main(String[] args) {
		Steque<Integer> steque = new Steque<Integer>();
		
		// push 0 ~ 9
		StdOut.println("push 0 ~ 9");
		for(int i = 0; i < 10; i++)
			steque.push(i);
		StdOut.println(steque);
		
		//pop 9 ~ 6
		StdOut.println("\npop 9 ~ 6");
		steque.pop();
		steque.pop();
		steque.pop();
		steque.pop();
		StdOut.println(steque);
		
		// enqueue 95 ~ 99
		StdOut.println("\nenqueue 95 ~ 99");
		for(int i = 95; i < 100; i++)
			steque.enqueue(i);
		StdOut.println(steque);
		
		// dequeue 5 ~ 2
		StdOut.println("\ndequeue 5 ~ 2");
		steque.dequeue();
		steque.dequeue();
		steque.dequeue();
		steque.dequeue();
		StdOut.println(steque);
	}
	// output
	/*
	 * 	push 0 ~ 9
		9 -> 8 -> 7 -> 6 -> 5 -> 4 -> 3 -> 2 -> 1 -> 0
		
		pop 9 ~ 6
		5 -> 4 -> 3 -> 2 -> 1 -> 0
		
		enqueue 95 ~ 99
		5 -> 4 -> 3 -> 2 -> 1 -> 0 -> 95 -> 96 -> 97 -> 98 -> 99
		
		dequeue 5 ~ 2
		1 -> 0 -> 95 -> 96 -> 97 -> 98 -> 99
	 */
}
