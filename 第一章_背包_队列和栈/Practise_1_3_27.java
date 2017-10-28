package 第一章_背包_队列和栈;


import edu.princeton.cs.algs4.*;

public class Practise_1_3_27 {
	static class Node<T> {
		T item;
		Node<T> next;
		Node(T item, Node<T> next) {
			this.item = item;
			this.next = next;
		}
	}
	/*
	 * print whole list
	 */
	public static <T> void printList(Node<T> list) {
		if (list == null) return;
		while(list.next != null) {
			StdOut.print(list.item + " -> ");
			list = list.next;
		}
		StdOut.println(list.item);
	}
	/*
	 * find the maximum
	 */
	public static int max(Node<Integer> list) {
		if (list == null) return 0;
		int max = list.item;
		list = list.next;
		while(list != null) {
			if (list.item > max)
				max = list.item;
			list = list.next;
		}
		return max;
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
		printList(first);
		
		StdOut.print("\nthe max value is " + max(first));
		
	}
}
