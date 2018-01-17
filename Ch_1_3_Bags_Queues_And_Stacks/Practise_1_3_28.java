package Ch_1_3_Bags_Queues_And_Stacks;

import edu.princeton.cs.algs4.*;

public class Practise_1_3_28 {
    /*
     * 思路 :
     * 
     * 请阅读每个方法上的注释
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
	/*
	 * 此递归思想用自然语言描述如下:
	 * 
	 * 若要找出链表中的最大元素，相当于用头结点元素与排除头结点外剩下链表中所有结点中的最大元素作比较，大者即为所求
	 * 
	 * 从少量结点的例子出发便于解释该算法
	 * 对于 1 -> 2 -> 3
	 * 找出 1 和 { 2-> 3}中最大的那个元素
	 * 对于 2 -> 3 
	 * 找出 2 和 3 中最大的那个元素,
	 * 2 和 3 中最大元素为 3，返回 3
	 * 1 和 3 中最大元素为 3，返回 3，递归结束
	 */
	public static int recursiveMax(Node<Integer> list) {
		if (list == null) return 0;
		if (list.next == null) return list.item;
		return Math.max(list.item, recursiveMax(list.next));
	}
	public static int recursiveMin(Node<Integer> list) {
        if (list == null) return 0;
        if (list.next == null) return list.item;
        return Math.min(list.item, recursiveMin(list.next));
    }
	/*
	 * 递归打印链表
	 * 
	 * 递归基当前结点是否为尾结点
	 * 打印出头结点，然后把下一个元素作为参数递归调用，该函数会在顺序执行所有打印后于尾结点返回
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
	 * 递归打印逆序链表
	 * 
	 * 此算法会从尾结点开始第一次打印，然后将深度索引减1，返回到上一层，最后打印头结点时，由于深度为0，所以
	 * 不会再打印 "->"
	 */
	public static void recursivePrintInReverseOrder(Node<Integer> list) {
		recursivePrintInDepth(list, 0);
	}
	public static int recursivePrintInDepth(Node<Integer> list, int depth) {
		if (list.next != null)
			depth = recursivePrintInDepth(list.next, ++depth);
		if (depth == 0) 
			StdOut.print(list.item);
		else
			StdOut.print(list.item + " -> ");
		return --depth;
	}
	
	/*
	 * 递归记录链表中的结点个数
	 * 
	 * 递归基为是否是尾结点
	 * 如果不是尾结点，就记录当前结点的 1 加上后续所有结点的个数
	 */
	public static int recursiveCounter(Node<Integer> list) {
		if (list.next == null) return 1;
		return 1 + recursiveCounter(list.next);
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
		StdOut.println("initialize a list");
		recursivePrint(first);
		
		StdOut.print("\nthe max value is " + recursiveMax(first));
		StdOut.print("\nthe node count is " + recursiveCounter(first));
		StdOut.print("\nthe min value is " + recursiveMin(first));
		StdOut.println("\nrecursively print list");
		recursivePrintInReverseOrder(first);
	}
}
