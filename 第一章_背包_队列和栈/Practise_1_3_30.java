package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.*;

public class Practise_1_3_30 {
    /*
     * 思路 :
     * 
     * 请看每个方法上的注释
     * 
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
	 * 
	 * 这里其实用到的是栈思想
	 * 首先记录当前结点的后续，然后让当前结点指向它的前一个结点，接着把当前结点往后挪到刚才记录的后续位置
	 * 举例来说，对于链表 6 -> 5 -> 4 -> 3 -> 2 -> 1
	 * 变化过程如下
	 * 
	 * 6 -> null 
	 * 5 -> 4 -> 3 -> 2 -> 1
	 * 5 -> 6 -> null
	 * 4 -> 5 -> 6 -> null
	 * 3 -> 4 -> 5 -> 6 -> null
	 * 2 -> 3 -> 4 -> 5 -> 6 -> null
	 * 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
	 * 
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
	 * 
	 * 此算法的递归基是 判断当前结点是否为尾结点，如果是，那么就返回该尾结点
	 * 所以第一次连接操作发生在尾结点和它的前一个元素之间，此时 second 是尾结点
	 * 让尾结点指向尾结点的前一个元素（通过上一次递归函数的函数帧记录变量）
	 * 然后让被指向的那个元素指向 null, 返回 尾结点
	 * 
	 * 对于链表 5 -> 4 -> 3 -> 2 -> 1 变化如下
	 * 
	 * 5 -> 4 -> 3 -> 2 -> 1
	 *      👆
     * 5 -> 4 -> 3 -> 2 -> 1
     *           👆
     * 5 -> 4 -> 3 -> 2 -> 1
     *                👆
     * 5 -> 4 -> 3 -> 2 -> 1   rest 指向 1
     *                     👆
     *                     
     *                     👇
     * 5 -> 4 -> 3 -> 2 <- 1  rest 指向 1
     *                |
     *               null
      *         👇
     * 5 -> 4 -> 3 <- 2 <- 1 rest 指向 1
     *           |
     *          null
     *          
      *    👇
     * 5 -> 4 <- 3 <- 2 <- 1 rest 指向 1
     *      |
     *     null  
     *👇
     * 5 <- 4 <- 3 <- 2 <- 1 rest 指向 1 返回原先的尾结点 rest 作为 逆转后链表的头结点，递归结束
     * |
     *null
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
