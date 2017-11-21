package 第一章_算法分析;

import edu.princeton.cs.algs4.StdOut;

/*
 * 思路 :
 * 
 * 三个栈的 A B C 功能如下
 * 
 * A 负责 pushLeft popLeft 
 * C 负责 pushRight popRight
 * B 负责 当 popLeft A 为空，作为 C 将底部一半元素倒入到 A 的临时缓冲区
 *   负责 当 popRight C 为空时，作为 A 将底部一半元素倒入 C 的临时缓冲区
 */
public class Practise_1_4_31 {
	static class Stack<T> {
		private class Node {
			T item;
			Node next, prev;
			Node() {}
			Node(T item, Node prev, Node next) {
				this.item = item;
				this.prev = prev;
				this.next = next;
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
				if (next != null)
					next.prev = prev;
				if (prev != null)
					prev.next = next;
				return del;
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
		boolean isEmpty() { return size == 0; }
		int size() { return size; }
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
	}
	static class Deque<T> {
		private Stack<T> stackLeft = new Stack<T>();
		private Stack<T> stackMiddle = new Stack<T>();
		private Stack<T> stackRight = new Stack<T>();
		boolean isEmpty() { return stackLeft.isEmpty() && stackRight.isEmpty(); }
		void pushLeft(T item) {
			stackLeft.push(item);
		}
		void pushRight(T item) {
			stackRight.push(item);
		}
		void rightToLeft() {
			int half = stackRight.size() / 2;
			while (half-- > 0) stackMiddle.push(stackRight.pop());
			while (!stackRight.isEmpty()) stackLeft.push(stackRight.pop());
			while (!stackMiddle.isEmpty()) stackRight.push(stackMiddle.pop());
		}
		void leftToRight() {
			int half = stackLeft.size() / 2;
			while (half-- > 0) stackMiddle.push(stackLeft.pop());
			while (!stackLeft.isEmpty()) stackRight.push(stackLeft.pop());
			while (!stackMiddle.isEmpty()) stackLeft.push(stackMiddle.pop());
		}
		T popLeft() {
			if (isEmpty())
				throw new RuntimeException("pop from a empty deque");
			if (stackLeft.isEmpty()) 
				rightToLeft();
			return stackLeft.pop();
		}
		T popRight() {
			if (isEmpty())
				throw new RuntimeException("pop from a empty deque");
			if (stackRight.isEmpty())
				leftToRight();
			return stackRight.pop();
		}
	}
	public static void main(String[] args) {
		Deque<Integer> deque = new Deque<Integer>();
		// 11 9 7 5 3 1 2 4 6 8 10
		deque.pushLeft(1);
		deque.pushRight(2);
		deque.pushLeft(3);
		deque.pushRight(4);
		deque.pushLeft(5);
		deque.pushRight(6);
		deque.pushLeft(7);
		deque.pushRight(8);
		deque.pushLeft(9);
		deque.pushRight(10);
		deque.pushLeft(11);
		
		StdOut.println(deque.popLeft());
		StdOut.println(deque.popRight());
		StdOut.println(deque.popLeft());
		StdOut.println(deque.popRight());
		StdOut.println(deque.popLeft());
		StdOut.println(deque.popRight());
		StdOut.println(deque.popLeft());
		StdOut.println(deque.popRight());
		StdOut.println(deque.popLeft());
		StdOut.println(deque.popRight());
		StdOut.println(deque.popLeft());
	}
	// output
	/*
	 *  11
		10
		9
		8
		7
		6
		5
		4
		3
		2
		1
	 */
}
