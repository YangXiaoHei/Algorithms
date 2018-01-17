package Ch_1_3_Bags_Queues_And_Stacks;

import edu.princeton.cs.algs4.*;

public class Practise_1_3_21 {
	/*
	 * 思路 :
	 * 
	 * 从传入的结点开始依次检查元素的 item 域是否等于 key, 不等就往后挪
	 * 将进入循环条件设置为 list != null 避免了对传入参数为 null 的检查
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
	public static <T> boolean find(Node<T> list, T key) {
		while(list != null) {
			if (list.item.equals(key))
				return true;
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
				new Node<Integer>(StdRandom.uniform(1, 20)))))))))))))));
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
