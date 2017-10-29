package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.*;

public class Practise_1_3_30 {
	static class Node<T> {
		T item;
		Node<T> next;
		Node(T item, Node<T> next) {
			this.item = item;
			this.next = next;
		}
		Node(T item) { this(item, null); }
	}
	public static <T> void printList(Node<T> first) {
		if (first.next == null) {
			StdOut.println(first.item);
			return;
		}
		StdOut.print(first.item + " -> ");
		printList(first.next);
	}
	/*
	 * 迭代法反转链表
	 */
	public static <T> Node<T> reverse(Node<T> x) {
		Node<T> first = x;
		Node<T> reverse = null;
		while(first != null) {
			Node<T> second = first.next;
			first.next = reverse;
			reverse = first;
			first = second;
		}
		return reverse;
	}
	/*
	 * 递归法反转链表
	 */
	public static <T> Node<T> reverseRecursive(Node<T> x) {
		if (x == null) return null;
		if (x.next == null) return x;
		Node<T> second = x.next;
		Node<T> rest = reverseRecursive(second);
		second.next = x;
		x.next = null;
		return rest;
	}
	public static void main(String[] args) {
		Node<Integer> first = 
				new Node<Integer>(StdRandom.uniform(1, 100), 
				new Node<Integer>(StdRandom.uniform(1, 100), 
				new Node<Integer>(StdRandom.uniform(1, 100), 
				new Node<Integer>(StdRandom.uniform(1, 100), 
				new Node<Integer>(StdRandom.uniform(1, 100), 
				new Node<Integer>(StdRandom.uniform(1, 100), 
				new Node<Integer>(StdRandom.uniform(1, 100), 
				new Node<Integer>(StdRandom.uniform(1, 100), 
				new Node<Integer>(StdRandom.uniform(1, 100), 
				new Node<Integer>(StdRandom.uniform(1, 100), 
				new Node<Integer>(StdRandom.uniform(1, 100), 
				new Node<Integer>(StdRandom.uniform(1, 100), 
				new Node<Integer>(StdRandom.uniform(1, 100), 
				new Node<Integer>(StdRandom.uniform(1, 100), 
				new Node<Integer>(StdRandom.uniform(1, 100), 
				new Node<Integer>(StdRandom.uniform(1, 100), 
				new Node<Integer>(StdRandom.uniform(1, 100), 
				new Node<Integer>(StdRandom.uniform(1, 100)))))))))))))))))));
		StdOut.println("Initialize a list");
		printList(first);
		
		StdOut.println("\niteratively reverse a list");
		Node<Integer> reverse = reverse(first);
		printList(reverse);
		
		StdOut.println("\nrecursively reverse a list");
		Node<Integer> recursiveReverse = reverseRecursive(reverse);
		printList(recursiveReverse);
	}
	// output
	/*
	 * 	Initialize a list
		2 -> 57 -> 70 -> 18 -> 64 -> 27 -> 13 -> 41 -> 44 -> 35 -> 40 -> 73 -> 19 -> 26 -> 1 -> 48 -> 45 -> 81
		
		iteratively reverse a list
		81 -> 45 -> 48 -> 1 -> 26 -> 19 -> 73 -> 40 -> 35 -> 44 -> 41 -> 13 -> 27 -> 64 -> 18 -> 70 -> 57 -> 2
		
		recursively reverse a list
		2 -> 57 -> 70 -> 18 -> 64 -> 27 -> 13 -> 41 -> 44 -> 35 -> 40 -> 73 -> 19 -> 26 -> 1 -> 48 -> 45 -> 81

	 */
}
