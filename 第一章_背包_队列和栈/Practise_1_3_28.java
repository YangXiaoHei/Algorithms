package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.*;

public class Practise_1_3_28 {
	static class Node<T> {
		T item;
		Node<T> next;
		Node(T item, Node<T> next) {
			this.item = item;
			this.next = next;
		}
	}
	///////////////////////////////////////////////////////////////////////
	// the following linked list fundamental operation 
	// functions are all implemented by recursion
	//////////////////////////////////////////////////////////////////////
	/*
	 *  recursively find the maximum
	 *  
	 *  if we want to find the maximum in 
	 *  1->2->3->4
	 *  we should compare 1 and maximum in sublist 2->3->4
	 *  if we want to find the maximum in 2->3->4
	 *  we should compare 2 and maximum in sublist 3->4
	 *  ....
	 *  we should compare 3 and 4, so the max one is 4, return 4
	 *  2 < 4 so we return 4
	 *  1 < 4 so we return 4
	 *  eventually we obtain the maximum in linked list 1->2-3>-4 by recursion
	 */
	public static int recursiveMax(Node<Integer> list) {
		if (list == null) return 0;
		if (list.next == null) return list.item;
		return Math.max(list.item, recursiveMax(list.next));
	}
	/*
	 * recursively print the whole list in order
	 */
	public static void recursivePrint(Node<Integer> list) {
		if (list.next == null) {
			StdOut.print(list.item);
			return;
		}
		StdOut.print(list.item + " -> ");
		recursivePrint(list.next);
	}
	/*
	 * recursively print the whole list in reverse order
	 */
	public static void recursivePrintInReverseOrder(Node<Integer> list) {
		recursivePrintInDepth(list, 0);
	}
	public static int recursivePrintInDepth(Node<Integer> list, int depth) {
		if (list.next != null)
			depth = recursivePrintInDepth(list.next, --depth);
		if (depth == 0) 
			StdOut.print(list.item);
		else
			StdOut.print(list.item + " -> ");
		return ++depth;
	}
	
	/*
	 * recursively count the total nodes
	 */
	public static int recursiveCounter(Node<Integer> list) {
		if (list.next == null) return 1;
		return recursiveCounter(list.next) + 1;
	}
	/*
	 * recursively find the minimum node value
	 */
	public static int recursiveMin(Node<Integer> list) {
		if (list == null) return 0;
		if (list.next == null) return list.item;
		return Math.min(list.item, recursiveMin(list.next));
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
				new Node<Integer>(StdRandom.uniform(1, 100), null))))))))))))))))));
		StdOut.println("initialize a list");
		recursivePrint(first);
		
		StdOut.print("\nthe max value is " + recursiveMax(first));
		StdOut.print("\nthe node count is " + recursiveCounter(first));
		StdOut.print("\nthe min value is " + recursiveMin(first));
		StdOut.println("\nrecursively print list");
		recursivePrintInReverseOrder(first);
	}
}
