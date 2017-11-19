package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.StdOut;

/*
 * 思路 :
 * 
 * 使用双向环形链表来解决这个问题
 * 我们使用一个 cur 变量来记录当前报数的人，如果报到 M，我们就将这个结点删掉，模拟杀死过程
 * 最后当 kill 时，如果环形链表只剩下一个结点，那么这个结点就表示最终存活的人
 * 
 * 我们在结点内部封装了 delete 方法，因此我们
 * 
 */
public class Practise_1_3_37 {
	static class Josephus {
		private class Node {
			int index;
			Node next, prev;
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
			public String toString() { return index + ""; }
		}
		private int M;
		private int size;
		private Node cur = null;
		public Josephus(int N, int M) {
			if (N == 0 || M == 0)
				throw new RuntimeException("are you serious?");
			this.M = M;
			/*
			 * 构建环形链表
			 */
			Node header = new Node();
			Node tailer = new Node();
			header.next = tailer;
			tailer.prev = header;
			header.prev = null;
			tailer.next = null;
			for(int i = 0; i < N; i++)
				tailer.insertBefore(size++);
			cur = header.next;
			tailer.prev.next = header.next;
			header.next.prev = tailer.prev;
		}
		public int size() { return size; }
		public void kill() {
			if (size == 0) return;
			if (size == 1) {
				StdOut.print("\nSurvivor : ");
				cur.delete();
			} else {
				for(int i = 0; i < M; i++)
					cur = cur.next;
				
				// 此时 cur 指向报出死亡数字的下一个人，我们把上一个报数的人杀死
				cur.prev.delete();
			} 
			size--;
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
	// output
	/*
	 * 	1 3 5 0 4 2 
		Survivor : 6 
	 */
}
