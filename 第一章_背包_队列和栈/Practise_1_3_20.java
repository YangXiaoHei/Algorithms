package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.*;

public class Practise_1_3_20 {
	static class Node {
		int item;
		Node next;
		Node(int item, Node next) {
			this.item = item;
			this.next = next;
		}
	}
	public static void printList(Node beg) {
		while(beg.next != null) {
			if (beg.item >= 0) 
				StdOut.print(beg.item + " -> ");
			beg = beg.next;
		}
		StdOut.println(beg.item);
	}
	public static int deleteKthElem(Node sentinel, int k) {
		if (sentinel == null || sentinel.next == null) 
			throw new RuntimeException("the list does not have any element to delete");
		
		int kk = k;
		// at least one element to delete
		Node p = sentinel, q = sentinel.next;
		while(k-- > 0) {
			q = q.next;
			if (q == null) 
				throw new RuntimeException(kk + " is out of list's size boundary");
			p = p.next;
		}
		p.next = p.next.next;
		return q.item;
	}
	
	public static void main(String[] args) {
		Node header =
				new Node(-1,	//👈  header sentinel : 
				new Node(0, 	// 👆 assume this node is not visible for user 
				new Node(1, 	// and the index is negative one
				new Node(2, 	// if we don't arrange a sentinel node, it's impossible
				new Node(3, 	// to delete first node in java
				new Node(4, 
				new Node(5, 
				new Node(6, 
				new Node(7, 
				new Node(8,
				new Node(9, 
				new Node(10, 
				new Node(11, null)))))))))))));
		StdOut.println("initialize a list");
		printList(header);
		
		StdOut.println("\nafter delete the first element");
		deleteKthElem(header, 0);
		printList(header);
		
		StdOut.println("\nafter delete the 3th element");
		deleteKthElem(header, 3);
		printList(header);
		
		StdOut.println("\nafter delete the 5th element");
		deleteKthElem(header, 5);
		printList(header);
		
		StdOut.println("\nafter delete a not exist element");
		deleteKthElem(header, 9);
	}
	// output
	/*
	 * 	initialize a list
		0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> 11
		
		after delete the first element
		1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> 11
		
		after delete the 3th element
		1 -> 2 -> 3 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> 11
		
		after delete the 5th element
		1 -> 2 -> 3 -> 5 -> 6 -> 8 -> 9 -> 10 -> 11
		
		after delete a not exist element
		Exception in thread "main" java.lang.RuntimeException: 9 is out of list's size boundary
			at 第一章_背包_队列和栈.Practise_1_3_20.deleteKthElem(Practise_1_3_20.java:34)
			at 第一章_背包_队列和栈.Practise_1_3_20.main(Practise_1_3_20.java:72)

	 */
}
