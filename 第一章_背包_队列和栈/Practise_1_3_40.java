package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_3_40 {
	static class MoveToFront<T> {
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
			header.prev = tailer.next = null;
		}
		public void read(T item) {
			Node same = search(item);
			if (same != null)
				header.insertAfter(same.delete());
			else
				tailer.insertBefore(item);
			size++;
			StdOut.println(this + " 		read : " + item);
		}
		public boolean isEmpty() { return size == 0; }
		public Node search(T item) {
			if (isEmpty()) return null;
			Node cur = header.next;
			while(cur != tailer) {
				if (cur.item.equals(item))
					return cur;
				cur = cur.next;
			}
			return null;
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
		MoveToFront<String> mtf = new MoveToFront<String>();
		mtf.read("1");
		mtf.read("2");
		mtf.read("3");
		mtf.read("3");
		mtf.read("4");
		mtf.read("5");
		mtf.read("6");
		mtf.read("5");
		mtf.read("6");
		mtf.read("4");
		mtf.read("5");
	}
}
