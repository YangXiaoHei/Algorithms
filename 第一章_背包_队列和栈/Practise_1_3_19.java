package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_3_19 {
	static class Node<T> {
	    /*
	     * 思路:
	     * 
	     * 这一道题需要考虑周到一些
	     * 如果要删除尾结点的链表只有一个结点，那么直接返回 null 
	     * 如果不止一个结点，只需要准备一个指针，在检查到下一个元素是尾元素时，
	     * 直接把当前元素的 next 置为 null 即可
	     */
		T item;
		Node<T> next;
		Node(T item) { this(item, null); }
		Node(T item, Node<T> next) {
			this.item = item;
			this.next = next;
		}
	}
	public static <T> void printList(Node<T> beg) {
	    if (beg == null) {
	        StdOut.println("empty list");
	        return;
	    }
		Node<T> tmp = beg;
		while(tmp.next != null) {
			StdOut.print(tmp.item + " -> ");
			tmp = tmp.next;
		}
		StdOut.println(tmp.item);
	}
	public static <T> Node<T> removeLast(Node<T> list) {
		if (list == null) throw new NullPointerException();
		if (list.next == null) return null;
		Node<T> p = list; 
		while (p.next.next != null) 
			p = p.next;
		p.next = null;
		return list;
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
		StdOut.println("\ninitialize a list");
		printList(first);
		
		StdOut.println("after delete tail node");
		first = removeLast(first);
		printList(first);
		
	    first = new Node<Integer>(0);
	    StdOut.println("\ninitialize a list");
        printList(first);
        
        StdOut.println("after delete tail node");
        first = removeLast(first);
        printList(first);
	}
	// output
	/*
	 * 	
        initialize a list
        0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
        after delete tail node
        0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6
        
        initialize a list
        0
        after delete tail node
        empty list

	 */
}
