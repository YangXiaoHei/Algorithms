package 第一章_背包_队列和栈;

import java.util.*;

import edu.princeton.cs.algs4.StdOut;

interface Deque<T> extends Iterable<T> {
	boolean isEmpty();		// 双向队列是否为空
	int size();				// 双向队列的元素数量
	void pushLeft(T item);	// 向左端添加一个新元素
	void pushRight(T item);	// 向右端添加一个新元素
	T popLeft();			// 从左端删除一个元素
	T popRight();			// 从右端删除一个元素
}

public class Practise_1_3_33 {
	/*
	 * implemented based in resizing array 
	 */
	static class ResizingArrayDeque<T> implements Deque<T> {
		@SuppressWarnings("unchecked")
		private T[] items = (T[])new Object[2];
		private int size;
		private int head = 0;
		private int tail = 1;
		public boolean isEmpty() { return size == 0; }
		public int size() { return size; }
		public void pushLeft(T item) {
			if (head < 0)
				pushResize(items.length * 2, true);
			size++;
			items[head--] = item;
			StdOut.println(this);
		}
		public void pushRight(T item) {
			if (tail == items.length)
				pushResize(items.length * 2, false);
			size++;
			items[tail++] = item;
			StdOut.println(this);
		}
		public T popLeft() {
			if (isEmpty())
				throw new RuntimeException("pop from a empty deque");
			size--;
			T pop = items[++head];
			items[head] = null;
			if (size == 0) {
				head = 0;
				tail = 1;
			} else if (size > 0 && size == items.length / 4)
				popResize(items.length / 2);
			StdOut.println(this);
			return pop;
		}
		public T popRight() {
			if (isEmpty())
				throw new RuntimeException("pop from a empty deque");
			size--;	
			T pop = items[--tail];
			items[tail] = null;
			if (size == 0) {
				head = 0;
				tail = 1;
			} else if (size > 0 && size == items.length / 4)
				popResize(items.length / 2);
			
			StdOut.println(this);
			return pop;
		}
		/*
		 * i supposed that pop or push from right or left are occurred in equal chance
		 * so every time i resized the array to a larger or smaller size
		 * i would like to make all the elements centered in the new array
		 */
		@SuppressWarnings("unchecked")
		void pushResize(int newsize, boolean left) {
			T[] newItems = (T[])new Object[newsize];
			int index = Math.abs(newsize - size - 1) / 2;
			int cur = left ? index + 1 : index;
			for(int i = head + 1; i < tail; i++)
				newItems[cur++] = items[i];
			head = left ? index : index - 1;
			tail = left ? index + size + 1 : index + size;
			items = newItems;
		}
		@SuppressWarnings("unchecked")
		void popResize(int newsize) {
			T[] newItems = (T[])new Object[newsize];
			int index = Math.abs(newsize - size) / 2;
			int cur = index;
			for(int i = head + 1; i < tail; i++)
				newItems[cur++] = items[i];
			head = index - 1;
			tail = index + size;
			items = newItems;
		}
		
		public Iterator<T> iterator() {
			return new Iterator<T>() {
				private int i = head;
				public boolean hasNext() {
					return i < tail;
				}
				public T next() {
					T next = items[i];
					i++;
					return next;
				}
				public void remove() {
					throw new UnsupportedOperationException();
				}
			};
		}
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("|");
			for(int i = 0; i < items.length; i++)
				sb.append(String.format("%3s |", items[i] == null ? 
						" " : items[i].toString()));
			sb.append(String.format("      <<<<< head : %d tail : %d >>>>>>", head, tail));
			return sb.toString();
		}
	}
	public static void main(String[] args) {
		Deque<Integer> deque = new ResizingArrayDeque<Integer>();
		deque.pushLeft(0);
		deque.pushRight(1);
		deque.pushLeft(2);
		deque.pushLeft(3);
		deque.popLeft();
		deque.popRight();
		deque.popLeft();
		deque.pushLeft(99);
		deque.pushRight(98);
		deque.pushLeft(97);
		deque.pushLeft(96);
		deque.pushLeft(95);
		deque.pushRight(94);
		deque.pushLeft(93);
		deque.pushLeft(92);
		deque.popLeft();
		deque.popRight();
		deque.popLeft();
		deque.pushLeft(91);
		deque.pushRight(90);
		deque.pushLeft(89);
		deque.pushLeft(88);
		deque.popLeft();
		deque.popLeft();
		deque.popLeft();
		deque.popLeft();
		deque.popLeft();
		deque.popLeft();
		deque.popLeft();
		deque.popRight();
		deque.popLeft();
		deque.popLeft();
		
	}
}
