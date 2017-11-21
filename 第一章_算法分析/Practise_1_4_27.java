package 第一章_算法分析;

import java.awt.Color;
import edu.princeton.cs.algs4.*;
/*
 * 思路 :
 * 
 * 用一个栈来专门负责入队，一个栈来负责出队，当需要出队而出队栈没有元素时，把入队栈的所有元素倒入出队栈
 * 如果需要出队并且出队栈有元素时，则直接把出队栈栈顶元素弹出
 * 
 * 运行后可以看到均摊图像绘制
 * 
 */
public class Practise_1_4_27 {
	static class Stack<T> {
	    private int total;
	    private int cost;
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
		int total() { return total; }
		int cost() { return cost; }
		void clearCost() { cost = 0; }
		int size() { return size; }
		boolean isEmpty() { return size == 0; }
		void push(T item) {
			size++;
			header.insertAfter(item);
			cost++;
			total++;
		}
		T pop() {
			if (isEmpty())
				throw new RuntimeException("pop from a empty stack");
			cost++;
			total++;
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
	    private int totalOperationsCount;
		private Stack<T> stack1 = new Stack<T>(); 
		private Stack<T> stack2 = new Stack<T>();
		boolean isEmpty() { return stack1.isEmpty() && stack2.isEmpty(); }
		int size() { return stack1.size() + stack2.size(); }
		void enqueue(T item) {
			stack2.push(item);
			totalOperationsCount++;
			draw();
		}
		T dequeue() {
			if (isEmpty())
				throw new RuntimeException("dequeue from a empty queue");
			totalOperationsCount++;
			T del = null;
			if (!stack1.isEmpty()) {
				del = stack1.pop();
				draw();
	            return del;
			}
			while (!stack2.isEmpty())
				stack1.push(stack2.pop());
			del = stack1.pop();
			draw();
            return del;
		}
		void draw() {
		    StdDraw.setPenColor(Color.gray);
            StdDraw.point(totalOperationsCount, stack1.cost() + stack2.cost());
            stack1.clearCost();
            stack2.clearCost();
            StdDraw.setPenColor(Color.RED);
            StdDraw.point(totalOperationsCount,
                    (stack1.total() + stack2.total()) / totalOperationsCount);
		}
	}
	public static void main(String[] args) {
	    StdDraw.setXscale(0, 10000);
	    StdDraw.setYscale(-100, 100);
	    StdDraw.setPenRadius(.006);
		Queue<Integer> queue = new Queue<Integer>();
		for (int i = 0; i < 100; i++)
		    queue.enqueue(i);
	    while (!queue.isEmpty()) {
	        if (StdRandom.bernoulli(0.3))
	            queue.enqueue(1);
	        else
	            for (int i = 0; i < 30; i++) {
	                if (queue.isEmpty()) 
	                    break;
	                queue.dequeue();
	            }
	        
	        if (queue.size() <= 90)
	            for (int i = 0; i < 10; i++)
	                queue.enqueue(i);
	   
	    }
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
