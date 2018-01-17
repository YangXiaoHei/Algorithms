package Ch_1_3_Bags_Queues_And_Stacks;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_3_18 {
    /*
     * 思路 :
     * 
     * 没啥好说的
     */
	static class Node<T> {
		T item;
		Node<T> next;
		Node(T item, Node<T> next) {
			this.item = item;
			this.next = next;
		}
		Node(T item) { this(item, null); }
		Node() { this(null, null); }
		Node<T> insertAfter(T item) {
			Node<T> n = new Node<T>(item, this.next);
			this.next = n;
			return n;
		}
		public String toString() {
			return item.toString();
		}
	}
	static class List<T> {
		private Node<T> header = new Node<T>();
		private Node<T> tailer = new Node<T>();
		private int size;
		{
			size = 0;
			header.next = tailer;
			tailer.next = null;
		}
		Node<T> insertAsFirst(T item) {
			header.insertAfter(item);
			size++;
			return header.next;
		}
		Node<T> findPrev(T item) {
			Node<T> n = header.next;
			while(n.next != tailer)
				if (n.next.item.equals(item))
					return n;
				else
					n = n.next;
			return null;
		}
		T remove(T item) {
			Node<T> x = findPrev(item);
			T del = x.next.item;
			
			/*
			 * 删除 x 的后续节点
			 */
			x.next = x.next.next;
			size--;
			return del;
		}
		int size() { return size; }
		public String toString() {
			Node<T> n = header.next;
			StringBuilder sb = new StringBuilder();
			while(n.next != tailer) {
				sb.append(n.item + " -> ");
				n = n.next;
			}
			sb.append(n.item);
			return sb.toString();
		}
	}
	public static void main(String[] args) {
		StdOut.println("initialize a list structure");
		List<Integer> list = new List<Integer>();
		list
		.insertAsFirst(0)
		.insertAfter(1)
		.insertAfter(2)
		.insertAfter(3)
		.insertAfter(4)
		.insertAfter(5)
		.insertAfter(6)
		.insertAfter(7)
		.insertAfter(8);
		StdOut.println(list);
		
		StdOut.println("\ndelete node value is 4");
		list.remove(4);
		
		StdOut.println(list);
	}
	// output 
	/*
	 * 	initialize a list structure
		0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8
		
		delete node value is 4
		0 -> 1 -> 2 -> 3 -> 5 -> 6 -> 7 -> 8
	 */
}
