package Ch_1_3_Bags_Queues_And_Stacks;

import edu.princeton.cs.algs4.*;

public class Practise_1_3_20 {
    /*
     * 思路 :
     * 
     * 需要考虑 k 越界，k = 0两种情况
     * 假设当前链表是没有哨兵结点的，那么在 k = 0时，直接返回 first.next，相当于删除了头结点
     * 否则就用一个指针依次往后移动，如果下一个元素是 null 说明 k 越界
     * 如果下一个元素就是待删除元素，跳出循环，将当前指针的 .next 置为 .next.next
     */
	static class Node {
		int item;
		Node next;
		Node(int item) { this(item, null); }
		Node(int item, Node next) {
			this.item = item;
			this.next = next;
		}
	}
	public static void printList(Node beg) {
	    if (beg == null) {
	        StdOut.println("empty list");
	        return;
	    }
		while(beg.next != null) {
			if (beg.item >= 0) 
				StdOut.print(beg.item + " -> ");
			beg = beg.next;
		}
		StdOut.println(beg.item);
	}
	public static Node deleteKthElem(Node first, int k) {
		if (first == null || k < 0) 
		    throw new IllegalArgumentException();
		if (k == 0) return first.next;
		Node cur = first;
		while (--k > 0) {
		    cur = cur.next;
		    if (cur.next == null)
		        throw new RuntimeException("list out of bounds");
		}
		cur.next = cur.next.next;
		return first;
	}
	
	public static void main(String[] args) {
		Node header = new Node(0, 	
				      new Node(1, 	
				      new Node(2, 	
		              new Node(3, 	
                      new Node(4, 
                      new Node(5, 
                      new Node(6, 
                      new Node(7, 
                      new Node(8,
                      new Node(9, 
                      new Node(10, 
                      new Node(11))))))))))));
		StdOut.println("initialize a list");
		printList(header);
		
		StdOut.println("\nafter delete the first element");
		header = deleteKthElem(header, 0);
		printList(header);
		
		StdOut.println("\nafter delete the 3th element");
		header = deleteKthElem(header, 3);
		printList(header);
		
		StdOut.println("\nafter delete the 5th element");
		header = deleteKthElem(header, 5);
		printList(header);
		
		StdOut.println("\nafter delete the 9th element");
		header = deleteKthElem(header, 9);
		printList(header);
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
        
        after delete the 9th element
        Exception in thread "main" java.lang.RuntimeException: list out of bounds
            at 第一章_背包_队列和栈.Practise_1_3_20.deleteKthElem(Practise_1_3_20.java:35)
            at 第一章_背包_队列和栈.Practise_1_3_20.main(Practise_1_3_20.java:70)

	 */
}
