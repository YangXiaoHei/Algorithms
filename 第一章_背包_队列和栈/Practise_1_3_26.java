package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.*;

public class Practise_1_3_26 {
	/*
	 * 链表结点
	 */
	static class Node<T> {
		T item;
		Node<T> next;
		Node(T item) { this(item, null); }
		Node(Node<T> next) { this(null, next); } 
		Node(T item, Node<T> next) { this.item = item; this.next = next; }
	}
	/*
	 * 删除以 list 为表头的链表中所有值为 key 的结点
	 */
	public static <T> Node<T> removeAll(Node<T> list, T key) {
		Node<T> header = new Node<T>(list);
		Node<T> cur = header;
		while (cur.next != null) {
			if (cur.next.item.equals(key))
				cur.next = cur.next.next;
			else
				cur = cur.next;
		}
		return header.next;
	}
	/*
	 * 打印链表
	 */
	public static <T> void printList(Node<T> list) {
		if (list == null) {
			StdOut.println("empty list");
			return;
		}
		while (list.next != null) {
			StdOut.print(list.item + " -> ");
			list = list.next;
		}
		StdOut.println(list.item);
	}
	
	public static void main(String[] args) {
		
		// Key to be removed
		int key = 2;
		
		// TestCase 1
		Node<Integer> list = new Node<Integer>(2, 
							 new Node<Integer>(2,
							 new Node<Integer>(2,
							 new Node<Integer>(3,
							 new Node<Integer>(4,
							 new Node<Integer>(5,
							 new Node<Integer>(2,
							 new Node<Integer>(2,
							 new Node<Integer>(2)))))))));
		StdOut.println("Initialize list");
		printList(list);
		StdOut.println("After removing all node value of " + key);
		list = removeAll(list, key);
		printList(list);
		
		// TestCase 2
		list = new Node<Integer>(2, 
			   new Node<Integer>(2,
			   new Node<Integer>(2)));
		 StdOut.println("\nInitialize list");
		 printList(list);
		 StdOut.println("After removing all node value of " + key);
		 list = removeAll(list, key);
		 printList(list);
		  
		 
		// TestCase 3
		list = new Node<Integer>(3, 
			   new Node<Integer>(4,
			   new Node<Integer>(5)));
		 StdOut.println("\nInitialize list");
		 printList(list);
		 StdOut.println("After removing all node value of " + key);
		 list = removeAll(list, key);
		 printList(list);
		 
		// TestCase 4
		 list = null;
		 StdOut.println("\nInitialize list");
		 printList(list);
		 StdOut.println("After removing all node value of " + key);
		 list = removeAll(list, key);
		 printList(list);																	 
	}
	// output 
	/*
	 * 	Initialize list
		2 -> 2 -> 2 -> 3 -> 4 -> 5 -> 2 -> 2 -> 2
		After removing all node value of 2
		3 -> 4 -> 5
		
		Initialize list
		2 -> 2 -> 2
		After removing all node value of 2
		empty list
		
		Initialize list
		3 -> 4 -> 5
		After removing all node value of 2
		3 -> 4 -> 5
		
		Initialize list
		empty list
		After removing all node value of 2
		empty list

	 */
}
