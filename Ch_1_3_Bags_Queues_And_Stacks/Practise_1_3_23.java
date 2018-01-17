package Ch_1_3_Bags_Queues_And_Stacks;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_3_23 {
    /*
     * 思路 :
     * 
     * x.next = t
     * 
     * 此时 x 指向 t
     * 之后令 t.next = x.next
     * 相当于让 t 指向了它自身
     */
	static class Node<T> {
		T item;
		Node<T> next;
		Node(T item) { this(item, null); }
		Node(T item, Node<T> next) {
			this.item = item;
			this.next = next;
		}
	}
	/*
	 * 在 x 的后面插入一个 t
	 */
	public static <T> void insertAfter(Node<T> x, Node<T> t) {
		
		// correct code
		t.next = x.next;
		x.next = t;
		
		// buggy code
//		x.next = t;
//		t.next = x.next;
	}
	public static <T> void printList(Node<T> list) {
		if (list == null) return;
		while(list.next != null) {
			StdOut.print(list.item + " -> ");
			list = list.next;
		}
		StdOut.println(list.item);
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
				new Node<Integer>(7))))))));
		StdOut.println("initialize a list");
		printList(first);
		
		StdOut.println("\nafter insert a new node after the first one");
		insertAfter(first, new Node<Integer>(99));
		printList(first);
		
		StdOut.println("\nafter insert a new node after the first one");
		insertAfter(first, new Node<Integer>(98));
		printList(first);
		
		StdOut.println("\nafter insert a new node after the first one");
		insertAfter(first, new Node<Integer>(97));
		printList(first);
	}
	// output :
	/*
	 * 	initialize a list
		0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
		
		after insert a new node after the first one
		0 -> 99 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
		
		after insert a new node after the first one
		0 -> 98 -> 99 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
		
		after insert a new node after the first one
		0 -> 97 -> 98 -> 99 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7

	 */
}
