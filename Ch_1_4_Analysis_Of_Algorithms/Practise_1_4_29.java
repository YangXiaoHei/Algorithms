package Ch_1_4_Analysis_Of_Algorithms;

import edu.princeton.cs.algs4.*;

/*
 * 思路 :
 * 
 * 使用两个栈来实现 Steque
 * stack1 提供 push pop 操作支持
 * stack2 提供 enqueue 操作支持
 * 在每次 push 和 pop 操作时，都检查 stack2 是否有 enqueue 的元素，如果有的话，就把他们全部 pop 进 stack1
 * 同时，在每次进行 enqueue 操作时，检查 stack1 是否已经有入栈元素，如果有的话，就把他们全部倒进 stack2
 * 以方便从栈底添加元素，模拟 enqueue 操作
 * 
 */
public class Practise_1_4_29 {
	static class Stack<T> {
		private class Node {
			T item;
			Node prev, next;
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
			StringBuilder sb = new StringBuilder();
			Node cur = header.next;
			while (cur.next != tailer) {
				sb.append(cur.item + " -> ");
				cur = cur.next;
			}
			sb.append(cur.item);
			return sb.toString();
		}
	}
	static class Steque<T> {
		private Stack<T> stack1 = new Stack<T>(); // 入栈的正序
		private Stack<T> stack2 = new Stack<T>(); // 入队的逆序
		boolean isEmpty() { return stack1.isEmpty() && stack2.isEmpty(); }
		int size() { return stack1.size() + stack2.size(); }
		void stack1AllPopToStack2() {
			while (!stack1.isEmpty())
				stack2.push(stack1.pop());
		}
		void stack2AllPopToStack1() {
			while (!stack2.isEmpty())
				stack1.push(stack2.pop());
		}
		void push(T item) {
			stack2AllPopToStack1();
			stack1.push(item);
		}
		T pop() {
			stack2AllPopToStack1();
			return stack1.pop();
		}
		void enqueue(T item) {
			stack1AllPopToStack2();
			stack2.push(item);
		}
	}
	public static void main(String[] args) {
		Steque<Integer> steque = new Steque<Integer>();
		for (int i = 0; i < 5; i++)
			steque.push(i);
		for (int i = 5; i < 10; i++)
			steque.enqueue(i);
		StdOut.printf("Steque has %d elements", steque.size());
		while (!steque.isEmpty())
			StdOut.printf("pop %d from steque, now steque has %d elements\n", 
					steque.pop(), steque.size());
	}
	// output
	/*
	 * Steque has 10 elementspop 4 from steque, now steque has 9 elements
		pop 3 from steque, now steque has 8 elements
		pop 2 from steque, now steque has 7 elements
		pop 1 from steque, now steque has 6 elements
		pop 0 from steque, now steque has 5 elements
		pop 5 from steque, now steque has 4 elements
		pop 6 from steque, now steque has 3 elements
		pop 7 from steque, now steque has 2 elements
		pop 8 from steque, now steque has 1 elements
		pop 9 from steque, now steque has 0 elements
	 */
}
