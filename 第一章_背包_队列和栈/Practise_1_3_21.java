package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.*;

public class Practise_1_3_21 {
	/*
	 * 链表节点
	 */
	static class Node<T> {
		T item;
		Node<T> next;
		Node(T item, Node<T> next) {
			this.item = item;
			this.next = next;
		}
	}
	/*
	 * 打印链表
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
	 * 在链表中查找 key
	 */
	public static <T> boolean find(Node<T> list, T key) {
		if (list == null) return false;
		while(list.next != null) {
			if (list.item.equals(key))
				return true;
			else
				list = list.next;
		}
		return false;
	}
	public static void main(String[] args) {
		Node<Integer> first = 
				new Node<Integer>(StdRandom.uniform(1, 20),
				new Node<Integer>(StdRandom.uniform(1, 20),
				new Node<Integer>(StdRandom.uniform(1, 20),
				new Node<Integer>(StdRandom.uniform(1, 20),
				new Node<Integer>(StdRandom.uniform(1, 20),
				new Node<Integer>(StdRandom.uniform(1, 20),
				new Node<Integer>(StdRandom.uniform(1, 20),
				new Node<Integer>(StdRandom.uniform(1, 20),
				new Node<Integer>(StdRandom.uniform(1, 20),
				new Node<Integer>(StdRandom.uniform(1, 20),
				new Node<Integer>(StdRandom.uniform(1, 20), 
				new Node<Integer>(StdRandom.uniform(1, 20), 
				new Node<Integer>(StdRandom.uniform(1, 20), 
				new Node<Integer>(StdRandom.uniform(1, 20), null))))))))))))));
		StdOut.println("initialize a list");
		printList(first);
		
		Integer key = StdRandom.uniform(1, 20);
		StdOut.println("\nsearch " + key + " in the list");
		StdOut.println("the result is " + find(first, key));
	}
	// output :
	/*
	 * 	initialize a list
		14 -> 12 -> 3 -> 7 -> 11 -> 5 -> 1 -> 1 -> 8 -> 4 -> 11 -> 11 -> 3 -> 10
		
		search 7 in the list
		the result is true
	 */
}
