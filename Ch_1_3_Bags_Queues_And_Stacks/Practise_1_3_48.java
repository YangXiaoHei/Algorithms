package Ch_1_3_Bags_Queues_And_Stacks;

import edu.princeton.cs.algs4.*;
import java.util.*;
/*
 * 思路 :
 * 
 * 首先实现一个 Deque
 * 然后保证两个栈的 push 和 pop 操作分别只用到 Deque 的一端就好了
 * 
 * 在每个栈中使用一个 size 变量来记录栈的规模，以方便判断栈是否为空
 */
public class Practise_1_3_48 {
	@SuppressWarnings("unchecked")
	static class ResizingArrayDeque<T> {
		private T[] items = (T[])new Object[2];
		private int size, head, tail = 1;
		private boolean logOn;
		public boolean isEmpty() { return size == 0; }
		public int size() { return size; }
		public void pushLeftResize(int newsize) {
			T[] newItems = (T[])new Object[newsize];
			int index = (newsize - size - 1) / 2;
			for(int i = head + 1, j = index + 1; i < tail; i++, j++)
				newItems[j] = items[i];
			head = index;
			tail = index + size + 1;
			
			items = newItems;
		}
		public void pushRightResize(int newsize) {
			T[] newItems = (T[])new Object[newsize];
			int index = (newsize - size - 1) / 2;
			for(int i = head + 1, j = index; i < tail; i++, j++)
				newItems[j] = items[i];
			head = index - 1;
			tail = index + size;
			items = newItems;
		}
		
		public void popResize(int newsize) {
			T[] newItems = (T[])new Object[newsize];
			int index = (newsize - size) / 2;
			for(int i = head + 1, j = index; i < tail; i++, j++)
				newItems[j] = items[i];
			head = index - 1;
			tail = index + size;
			items = newItems;
		}
		
		public void pushLeft(T item) {
			if (head < 0) 
				pushLeftResize(size * 2);
			size++;
			items[head--] = item;
			if (logOn)
				StdOut.println(this);
		}
		public void pushRight(T item) {
			if (tail == items.length)
				pushRightResize(2 * size);
			size++;
			items[tail++] = item;
			if (logOn)
				StdOut.println(this);
		}
		public T popLeft() {
			if (isEmpty()) 
				throw new RuntimeException("pop from a empty deque");
			size--;
			T del = items[++head];
			items[head] = null;
			if (size > 0 && size == items.length / 4)
				popResize(items.length / 2);
			if (logOn)
				StdOut.println(this);
			return del;
		}
		public T popRight() {
			if (isEmpty()) 
				throw new RuntimeException("pop from a empty deque");
			size--;
			T del = items[--tail];
			items[tail] = null;
			if (size > 0 && size == items.length / 4)
				popResize(items.length / 2);
			if (logOn)
				StdOut.println(this);
			return del;
		}
		public String toString() {
			if (isEmpty()) return "[empty]";
			StringBuilder sb = new StringBuilder();
			sb.append("|");
			for(int i = 0; i < items.length; i++)
				sb.append(String.format(" %2s |", items[i] == null ? " " : items[i]));
			sb.append(String.format("            <<<<<<<<<< head : %d tail : %d size : %d >>>>>>>>>>", head, tail, size));
			return sb.toString();
		}
		public Iterable<T> traverse(int n, boolean left) {
			return new Iterable<T>() {
				public Iterator<T> iterator() {
					if (left) {
						return new Iterator<T>() {
							private int count = 0;
							private int i = head + 1;
							public boolean hasNext() { return count < n; }
							public T next() { count++; return items[i++]; }
						};
					} else {
						return new Iterator<T>() {
							private int i = tail - 1;
							private int count = 0;
							public boolean hasNext() { return count < n; }
							public T next() { count++; return items[i--]; }
						};
					}
				}
			};
		}
		public static void dequeTest() {
			ResizingArrayDeque<Integer> deq = new ResizingArrayDeque<Integer>();
			deq.logOn = true;
			deq.pushLeft(0);
			deq.pushLeft(1);
			deq.pushRight(2);
			deq.pushLeft(3);
			deq.pushRight(4);
			deq.pushRight(5);
			deq.pushLeft(6);
			deq.popLeft();
			deq.popRight();
			deq.popRight();
			deq.popLeft();
			deq.popLeft();
			deq.popLeft();
			deq.pushLeft(7);
			deq.pushRight(8);
			deq.pushLeft(9);
			deq.pushRight(10);
			deq.pushLeft(11);
			deq.pushRight(12);
			deq.pushLeft(13);
			deq.pushRight(14);
			deq.pushLeft(15);
			deq.pushRight(16);
			deq.pushLeft(17);
			deq.pushRight(18);
		}
	}
	
	static class LeftStack<T> {
		private ResizingArrayDeque<T> deque;
		private int size;
		public LeftStack(ResizingArrayDeque<T> deque) { this.deque = deque; }
		public void push(T item) { size++; deque.pushLeft(item); }
		public T pop() { 
			if (isEmpty())
				throw new RuntimeException("pop from a emtpy stack");
			size--; 
			return deque.popLeft(); 
		}
		public boolean isEmpty() { return size == 0;}
		public String toString() {
			if (isEmpty()) return "[empty]";
			StringBuilder sb = new StringBuilder();
			sb.append("====== LeftStack ======");
			for (T t : deque.traverse(size, true))
				sb.append("\n" + t);
			sb.append("\n====== Bottom ======");
			return sb.toString();
		}
	}
	static class RightStack<T> {
		private ResizingArrayDeque<T> deque;
		private int size;
		public RightStack(ResizingArrayDeque<T> deque) { this.deque = deque; }
		public void push(T item) { size++; deque.pushRight(item); }
		public T pop() { 
			if (isEmpty())
				throw new RuntimeException("pop from a emtpy stack");
			size--; 
			return deque.popRight(); 
		}
		public boolean isEmpty() { return size == 0;}
		public String toString() {
			if (isEmpty()) return "[empty]";
			StringBuilder sb = new StringBuilder();
			sb.append("====== RightStack ======");
			for (T t : deque.traverse(size, false))
				sb.append("\n" +  t);
			sb.append("\n====== Bottom ======");
			return sb.toString();
		}
	}
	public static void test() {
		ResizingArrayDeque<Integer> deque = new ResizingArrayDeque<Integer>();
		LeftStack<Integer> lstack = new LeftStack<Integer>(deque);
		RightStack<Integer> rstack = new RightStack<Integer>(deque);
		for (int i = 0; i < 10; i++)
			lstack.push(i);
		StdOut.println(lstack);
		for (int i = 0; i < 5; i++)
			lstack.pop();
		StdOut.println(lstack);
		
		for (int i = 80; i < 100; i++)
			rstack.push(i);
		StdOut.println(rstack);
		
		for (int i = 10; i < 20; i++)
			lstack.push(i);
		StdOut.println(lstack);
		
		for (int i = 0; i < 5; i++) {
			lstack.pop();
			rstack.pop();
		}
		StdOut.println(lstack);
		StdOut.println(rstack);
			
	}
	public static void main(String[] args) {
		test();
	}
	// output
	/*
        ====== LeftStack ======
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
        ====== Bottom ======
        ====== LeftStack ======
        4
        3
        2
        1
        0
        ====== Bottom ======
        ====== RightStack ======
        99
        98
        97
        96
        95
        94
        93
        92
        91
        90
        89
        88
        87
        86
        85
        84
        83
        82
        81
        80
        ====== Bottom ======
        ====== LeftStack ======
        19
        18
        17
        16
        15
        14
        13
        12
        11
        10
        4
        3
        2
        1
        0
        ====== Bottom ======
        ====== LeftStack ======
        14
        13
        12
        11
        10
        4
        3
        2
        1
        0
        ====== Bottom ======
        ====== RightStack ======
        94
        93
        92
        91
        90
        89
        88
        87
        86
        85
        84
        83
        82
        81
        80
        ====== Bottom ======

	 */
}
