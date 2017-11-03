package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_3_19 {
	static class Node<T> {
		T item;
		Node<T> next;
		Node(T item, Node<T> next) {
			this.item = item;
			this.next = next;
		}
	}
	public static <T> void printList(Node<T> beg) {
		Node<T> tmp = beg;
		while(tmp.next != null) {
			StdOut.print(tmp.item + " -> ");
			tmp = tmp.next;
		}
		StdOut.println(tmp.item);
	}
	
	public static <T> Node<T> removeLast(Node<T> list) {
		if (list == null)
			throw new NullPointerException();
		if (list.next == null) 
			return null;
		Node<T> p = list, q = list.next;
		while (q.next != null) {
			p = q;
			q = q.next;
		}
		q.item = null;
		p.next = null;
		return list;
	}
	public static void main(String[] args) {
		Node<Integer> first = 
				new Node<Integer>(0, 
				new Node<Integer>(1, 
				new Node<Integer>(2, 
				new Node<Integer>(3, 
				new Node<Integer>(4, 
				new Node<Integer>(5, 
				new Node<Integer>(6, 
				new Node<Integer>(7, null))))))));
		
		StdOut.println("initialize a list");
		printList(first);
		
		StdOut.println("\nafter delete tail node");
		first = removeLast(first);
		
		printList(first);
	}
	// output
	/*
	 * 	initialize a list
		0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
		
		after delete tail node
		0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6

	 */
}
