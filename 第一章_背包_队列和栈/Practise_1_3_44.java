package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.StdOut;
import java.util.*;

public class Practise_1_3_44 {
	static class Stack<T> {
		private class Node {
			T item;
			Node prev, next;
			Node(T item, Node prev, Node next) {
				this.item = item;
				this.prev = prev;
				this.next = next;
			}
			Node() { this(null, null, null); }
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
			header.prev = null;
			tailer.next = null;
		}
		public int size() { return size; }
		public void push(T item) {
			size++;
			header.insertAfter(item);
		}
		public T pop() {
			if (isEmpty())
				throw new RuntimeException("pop from a empty stack");
			size--;
			return header.next.delete();
		}
		public boolean isEmpty() { return size == 0; }
		public String toString() {
			if (isEmpty()) return "";
			StringBuilder sb = new StringBuilder();
			Node n = header.next;
			while(n.next != tailer) {
				sb.append(n.item + " -> ");
				n = n.next;
			}
			sb.append(n.item);
			return sb.toString();
		}
		public Iterable<T> normalOrder() {
			return new Iterable<T>() {
				public Iterator<T> iterator() {
					return new Iterator<T>() {
						private Node cur = header.next;
						public boolean hasNext() { return cur != tailer; }
						public T next() {
							T next = cur.item;
							cur = cur.next;
							return next;
						}
					};
				}
			};
		}
		public Iterable<T> reverseOrder() {
			return new Iterable<T>() {
				public Iterator<T> iterator() {
					return new Iterator<T>() {
						private Node cur = tailer.prev;
						public boolean hasNext() { return cur != header; }
						public T next() {
							T prev = cur.item;
							cur = cur.prev;
							return prev;
						}
					};
				}
			};
		}
	}
	static class Buffer {
		Stack<Character> left = new Stack<Character>();
		Stack<Character> right = new Stack<Character>();
		Buffer() {}
		int size() { return left.size() + right.size(); }
		void insert(char c) { 
			left.push(c); 
			StdOut.println(this); 
		}
		char delete() {
			char del = left.pop(); 
			StdOut.println(this); 
			return del;
		}
		void left(int k) {
			while(k-- > 0) 
				right.push(left.pop()); 
			StdOut.println(this);
		}
		void right(int k) { 
			while(k-- > 0)
				left.push(right.pop());
			StdOut.println(this);
		}
		public String toString() { 
			if (left.size() == 0 && right.size() == 0) return "|";
			StringBuilder sb = new StringBuilder();
			if (left.size() == 0) {
				sb.append(" | ");
				for(Character c : right.normalOrder()) 
					sb.append(c + " ");
			} else if (right.size() == 0) {
				for(Character c : left.reverseOrder()) 
					sb.append(c + " ");
				sb.append(" | ");
			} else {
				for(Character c : left.reverseOrder())
					sb.append(c + " ");
				sb.append(" | ");
				for(Character c : right.normalOrder())
					sb.append(c + " ");
			}
			return sb.toString();
		}
	}
	public static void main(String[] args) {
		Buffer buffer = new Buffer();
		StdOut.println("insert character at cursor and move cursor point to next position");
		for (char c = 'a'; c <= 'h'; c++)
			buffer.insert(c);
		StdOut.println("\ncursor move left");
		buffer.left(5);
		
		StdOut.println("\ncursor move right");
		buffer.right(3);
		
		StdOut.println("\ndelete character at cursor");
		buffer.delete();
		buffer.delete();
		buffer.delete();
		
		StdOut.println("\ncursor move right");
		buffer.right(2);
		
		StdOut.println("\ndelete character at cursor");
		buffer.delete();
		buffer.delete();
		buffer.delete();
		
		StdOut.println("\ntell present size");
		StdOut.println(buffer.size());
	}
	// output
	/*
	 * 	insert character at cursor and move cursor point to next position
		a  | 
		a b  | 
		a b c  | 
		a b c d  | 
		a b c d e  | 
		a b c d e f  | 
		a b c d e f g  | 
		a b c d e f g h  | 
		
		cursor move left
		a b c  | d e f g h 
		
		cursor move right
		a b c d e f  | g h 
		
		delete character at cursor
		a b c d e  | g h 
		a b c d  | g h 
		a b c  | g h 
		
		cursor move right
		a b c g h  | 
		
		delete character at cursor
		a b c g  | 
		a b c  | 
		a b  | 
		
		tell present size
		2
	 */
}
