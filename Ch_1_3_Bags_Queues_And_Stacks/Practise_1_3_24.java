package Ch_1_3_Bags_Queues_And_Stacks;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_3_24 {
    /*
     * 思路 :
     * 
     * 只要让该结点的 next 域指向 next 域的 next 域即可
     * 
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
	public static <T> void printList(Node<T> list) {
		if (list == null) return;
		while(list.next != null) {
			StdOut.print(list.item + " -> ");
			list = list.next;
		}
		StdOut.println(list.item);
	}
	public static <T> void removeAfter(Node<T> node) {
		if (node == null || node.next == null) return;
		node.next = node.next.next;
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
		
		StdOut.println("\nafter remove the next node of first");
		removeAfter(first);
		printList(first);
		
		StdOut.println("\nafter remove the next node of first");
		removeAfter(first);
		printList(first);
		
		StdOut.println("\nafter remove the next node of first");
		removeAfter(first);
		printList(first);
		
		StdOut.println("\nafter remove the next node of first");
		removeAfter(first);
		printList(first);
		
		StdOut.println("\nafter remove the next node of first");
		removeAfter(first);
		printList(first);
		
		StdOut.println("\nafter remove the next node of first");
		removeAfter(first);
		printList(first);
		
		StdOut.println("\nafter remove the next node of first");
		removeAfter(first);
		printList(first);
		
		StdOut.println("\nafter remove the next node of first");
		removeAfter(first);
		printList(first);
	}
	// output 
	/*
	 * 	initialize a list
		0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
		
		after remove the next node of first
		0 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
		
		after remove the next node of first
		0 -> 3 -> 4 -> 5 -> 6 -> 7
		
		after remove the next node of first
		0 -> 4 -> 5 -> 6 -> 7
		
		after remove the next node of first
		0 -> 5 -> 6 -> 7
		
		after remove the next node of first
		0 -> 6 -> 7
		
		after remove the next node of first
		0 -> 7
		
		after remove the next node of first
		0
		
		after remove the next node of first
		do nothing
		0
	 */
}
