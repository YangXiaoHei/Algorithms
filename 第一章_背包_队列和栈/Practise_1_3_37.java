package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_3_37 {
	static class Josephus {
		private class Node {
			int index = -1;
			Node next;
			Node prev;
			Node(int index,Node prev, Node next) {
				this.index = index;
				this.next = next;
				this.prev = prev;
			}
			Node() { this(-1, null, null); }
			Node insertBefore(int index) {
				Node n = new Node(index, this.prev, this);
				if (prev != null)
					prev.next = n;
				prev = n;
				return n;
			}
			int delete() {
				int del = index;
				if (next != null)
					next.prev = prev;
				if (prev != null)
					prev.next = next;
				index = -1;
				StdOut.print(del + " ");
				return del;
			}
		}
		Node current = null;
		Node header = new Node();
		Node tailer = new Node();
		{
			header.next = tailer;
			tailer.prev = header;
			header.prev = null;
			tailer.next = null;
		}
		private int M;
		private int size;
		private Node cur = null;
		public Josephus(int N, int M) {
			if (N == 0 || M == 0)
				throw new RuntimeException("fuck you!");
			this.M = M;
			for(int i = 0; i < N; i++)
				tailer.insertBefore(size++);
			cur = header.next;
			tailer.prev.next = header.next;
			header.next.prev = tailer.prev;
			header.next = tailer.prev = null;
		}
		public int size() { return size; }
		public void kill() {
			if (size == 0) return;
			if (size > 1) {
				for(int i = 0; i < M; i++)
					cur = cur.next;
				cur.prev.delete();
				size--;
			} else {
				StdOut.print("\nSurvivor : ");
				cur.delete();
				size--;
			}
		}
		public void play() {
			while(size() != 0)
				kill();
		}
	}
	public static void main(String[] args) {
		Josephus game = new Josephus(7, 2);
		game.play();
	}
}
