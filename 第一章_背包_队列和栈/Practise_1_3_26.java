package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.*;

public class Practise_1_3_26 {
	static class Node<T> {
		T item;
		Node<T> next;
		Node() { this(null, null); }
		Node(T item, Node<T> next) {
			this.item = item;
			this.next = next;
		}
		Node(T item) { this(item, null); }
	}
	static class List<L> {
		// if you don't keep a header sentinel node, you can't remove the 
		// first node when you assign the first node to function argument
		private Node<L> header = new Node<L>();
		// if we keep a tailer sentinel node, inserting a node can be much more easily
		// but now, i just don't want to use the above mentioned method here
		List<L> insert(L item) {
			Node<L> node = new Node<L>(item);
			if (header.next == null) {
				header.next = node;
				node.next = null;
			} else {
				Node<L> tmp = header;
				while(tmp.next != null)
					tmp = tmp.next;
				tmp.next = node;
				node.next = null;
			}
			// return self reference so that we can use dot to achieve chained function call
			return this;
		}
		/*
		 * remove all node value of key
		 */
		public void remove(L key) {
			Node<L> ne = header.next;
			if (ne == null) return;
			Node<L> pr = header;
			while(ne != null) {
				if (ne.item.equals(key)) {
					pr.next = pr.next.next;
					ne.item = null;
					ne = pr.next;
				} else {
					pr = ne;
					ne = ne.next;
				}
			}
		}
		public String toString() {
			Node<L> tmp = header.next;
			if (tmp == null)
				return "[empty]";
			StringBuilder sb = new StringBuilder();
			while(tmp.next != null) {
				sb.append(tmp.item + " -> ");
				tmp = tmp.next;
			}
			sb.append(tmp.item);
			return sb.toString();
		}
	}
	public static <T> void remove(List<T> list, T key) {
		list.remove(key);
	}
	public static void main(String[] args) {
		List<Integer> list = new List<Integer>();
		list
		.insert(StdRandom.uniform(1, 4))
		.insert(StdRandom.uniform(1, 4))
		.insert(StdRandom.uniform(1, 4))
		.insert(StdRandom.uniform(1, 4))
		.insert(StdRandom.uniform(1, 4))
		.insert(StdRandom.uniform(1, 4))
		.insert(StdRandom.uniform(1, 4))
		.insert(StdRandom.uniform(1, 4))
		.insert(StdRandom.uniform(1, 4))
		.insert(StdRandom.uniform(1, 4))
		.insert(StdRandom.uniform(1, 4))
		.insert(StdRandom.uniform(1, 4))
		.insert(StdRandom.uniform(1, 4))
		.insert(StdRandom.uniform(1, 4))
		.insert(StdRandom.uniform(1, 4))
		.insert(StdRandom.uniform(1, 4))
		.insert(StdRandom.uniform(1, 4))
		.insert(StdRandom.uniform(1, 4))
		.insert(StdRandom.uniform(1, 4))
		.insert(StdRandom.uniform(1, 4));
		StdOut.println("initialize a list");
		StdOut.println(list);
		
		Integer key = StdRandom.uniform(1, 4);
		StdOut.println("\nafter remove all node value of " + key);
		remove(list, key);
		StdOut.println(list);
	}
	// output 
	/*
	 * 	initialize a list
		2 -> 3 -> 3 -> 2 -> 1 -> 3 -> 3 -> 2 -> 2 -> 3 -> 2 -> 3 -> 3 -> 1 -> 3 -> 1 -> 1 -> 2 -> 3 -> 2
		
		after remove all node value of 2
		3 -> 3 -> 1 -> 3 -> 3 -> 3 -> 3 -> 3 -> 1 -> 3 -> 1 -> 1 -> 3

	 */
}
