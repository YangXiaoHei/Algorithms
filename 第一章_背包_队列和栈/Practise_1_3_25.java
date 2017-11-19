package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_3_25 {
    /*
     * 思路 :
     * 
     * 先让作为后续结点的结点接住前续结点的后续，然后让前序结点指向该后续结点即可
     * 
     */
	static class Node<T> {
		T item;
		Node<T> next;
		Node(T item, Node<T> next) {
			this.item = item;
			this.next = next;
		}
		Node(T item) {
			this(item, null);
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
	public static <T> void insertAfter(Node<T> node, Node<T> second) {
		if (node == null || second == null) return;
		second.next = node.next;
		node.next = second;
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
		
		StdOut.println("\nafter insert a node after the first node");
		insertAfter(first, new Node<Integer>(99));
		printList(first);
		
		StdOut.println("\nafter insert a node after the first node");
		insertAfter(first, new Node<Integer>(98));
		printList(first);
		
		StdOut.println("\nafter insert a node after the first node");
		insertAfter(first, new Node<Integer>(97));
		printList(first);
		
		StdOut.println("\nafter insert a node after the first node");
		insertAfter(first, new Node<Integer>(96));
		printList(first);
		
		StdOut.println("\nafter insert a node after the first node");
		insertAfter(first, new Node<Integer>(95));
		printList(first);
		
		StdOut.println("\nafter insert a node after the first node");
		insertAfter(first, new Node<Integer>(94));
		printList(first);
		
		StdOut.println("\nafter insert a node after the first node");
		insertAfter(first, new Node<Integer>(93));
		printList(first);
		
		StdOut.println("\nafter insert a node after the first node");
		insertAfter(first, new Node<Integer>(92));
		printList(first);
	}
	// output
	/*
	 * 	initialize a list
		0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
		
		after insert a node after the first node
		0 -> 99 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
		
		after insert a node after the first node
		0 -> 98 -> 99 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
		
		after insert a node after the first node
		0 -> 97 -> 98 -> 99 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
		
		after insert a node after the first node
		0 -> 96 -> 97 -> 98 -> 99 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
		
		after insert a node after the first node
		0 -> 95 -> 96 -> 97 -> 98 -> 99 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
		
		after insert a node after the first node
		0 -> 94 -> 95 -> 96 -> 97 -> 98 -> 99 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
		
		after insert a node after the first node
		0 -> 93 -> 94 -> 95 -> 96 -> 97 -> 98 -> 99 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
		
		after insert a node after the first node
		0 -> 92 -> 93 -> 94 -> 95 -> 96 -> 97 -> 98 -> 99 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
	 */
	
}
