package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_3_31 {
    /*
     * 思路 :
     * 
     * 通过将结点的插入添加删除等基本操纵封装到结点内部，我们可以便捷的实现所有链表操作
     * 
     */
	static class DoubleNode<T> {
		T item;
		DoubleNode<T> next;
		DoubleNode<T> prev;
		DoubleNode(T item, DoubleNode<T> prev, DoubleNode<T> next) {
			this.item = item;
			this.prev = prev;
			this.next = next;
		}
		DoubleNode(T item) { this(item, null, null); }
		DoubleNode<T> insertAfter(T item) {
			DoubleNode<T> node = new DoubleNode<T>(item, this, this.next);
			if (next != null)
				next.prev = node;
			next = node;
			return node;
		}
		DoubleNode<T> insertBefore(T item) {
			DoubleNode<T> node = new DoubleNode<T>(item, this.prev, this);
			if (prev != null)
				prev.next = node;
			prev = node;
			return node;
		}
		T delete() {
			T del = item;
			item = null;
			if (next != null)
				next.prev = prev;
			if (prev != null)
				prev.next = next;
			return del;
		}
		/*
		 * 查找指定结点
		 */
		 DoubleNode<T> search(T item) {
			DoubleNode<T> current = this;
			while(current.prev != null)
				current = current.prev;
			while(current != null)
				if (current.item.equals(item))
					return current;
				else
					current = current.next;
			return null;
		}
		/*
		 * 在指定结点之前插入新结点
		 */
		static <T> void insertAfter(DoubleNode<T> node, T item) { node.insertAfter(item); }
		/*
		 * 在指定结点之后插入新结点
		 */
		static <T> void insertBefore(DoubleNode<T> node, T item) { node.insertBefore(item); }
		/*
		 * 在表头插入结点
		 */
		static <T> void insertAsFirst(DoubleNode<T> random, T item) {
			while(random.prev != null)
				random = random.prev;
			random.insertBefore(item);
		}
		/*
		 * 在表尾插入结点
		 */
		static <T> void insertAsLast(DoubleNode<T> random, T item) {
			while(random.next != null)
				random = random.next;
			random.insertAfter(item);
		}
		/*
		 * 从表头删除结点
		 */
		static <T> T removeFirst(DoubleNode<T> random) {
			while(random.prev != null)
				random = random.prev;
			return random.delete();
		}
		/*
		 * 从表尾删除结点
		 */
		static <T> T removeLast(DoubleNode<T> random) {
			while(random.next != null)
				random = random.next;
			return random.delete();
		}
		/*
		 * 删除指定结点
		 */
		static <T> T remove(DoubleNode<T> random, T item) {
			DoubleNode<T> node = random.search(item);
			if (node == null) return null;
			return node.delete();
		}
		static <T> T remove(DoubleNode<T> random, DoubleNode<T> node) {
			return remove(random, node.item);
		}
		void print() {
			DoubleNode<T> current = this;
			while(current.prev != null)
				current = current.prev;
			while(current.next != null) {
				StdOut.print(current.item + " -> ");
				current = current.next;
			}
			StdOut.println(current.item);
		}
	}
	public static void main(String[] args) {
		DoubleNode<Integer> list = 
				new DoubleNode<Integer>(0);
				list
				.insertAfter(1)
				.insertAfter(2)
				.insertAfter(3)
				.insertAfter(4)
				.insertAfter(5)
				.insertAfter(6)
				.insertAfter(7)
				.insertAfter(8)
				.insertAfter(9);
		
		StdOut.println("print double double list");
		list.print();
		
		StdOut.println("\ninsert as first");
		DoubleNode.insertAsFirst(list, 99);
		list.print();
		
		StdOut.println("\ninsert as first");
		DoubleNode.insertAsFirst(list, 98);
		list.print();
		
		StdOut.println("\ninsert as first");
		DoubleNode.insertAsFirst(list, 97);
		list.print();
		
		StdOut.println("\ninsert as last");
		DoubleNode.insertAsLast(list, 96);
		list.print();
		
		StdOut.println("\ninsert as last");
		DoubleNode.insertAsLast(list, 95);
		list.print();
		
		StdOut.println("\ninsert as last");
		DoubleNode.insertAsLast(list, 94);
		list.print();
		
		DoubleNode<Integer> node = list.search(8);
		StdOut.println("\ninsert 93 after node " + node.item);
		DoubleNode.insertAfter(node, 93);
		list.print();
		
		StdOut.println("\ninsert 92 before node " + node.item);
		DoubleNode.insertBefore(node, 92);
		list.print();
		
		StdOut.println("\ndelete last node ");
		DoubleNode.removeLast(list);
		list.print();
		
		StdOut.println("\ndelete first node ");
		DoubleNode.removeFirst(list);
		list.print();
		
		StdOut.println("\ndelete node value of " + node.item);
		DoubleNode.remove(list, node);
		list.print();
	}
	// output
	/*
	 * 	print double double list
		0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9
		
		insert as first
		99 -> 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9
		
		insert as first
		98 -> 99 -> 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9
		
		insert as first
		97 -> 98 -> 99 -> 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9
		
		insert as last
		97 -> 98 -> 99 -> 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 96
		
		insert as last
		97 -> 98 -> 99 -> 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 96 -> 95
		
		insert as last
		97 -> 98 -> 99 -> 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 96 -> 95 -> 94
		
		insert 93 after node 8
		97 -> 98 -> 99 -> 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 93 -> 9 -> 96 -> 95 -> 94
		
		insert 92 before node 8
		97 -> 98 -> 99 -> 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 92 -> 8 -> 93 -> 9 -> 96 -> 95 -> 94
		
		delete last node 
		97 -> 98 -> 99 -> 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 92 -> 8 -> 93 -> 9 -> 96 -> 95
		
		delete first node 
		98 -> 99 -> 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 92 -> 8 -> 93 -> 9 -> 96 -> 95
		
		delete node value of 8
		98 -> 99 -> 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 92 -> 93 -> 9 -> 96 -> 95
	 */
}
