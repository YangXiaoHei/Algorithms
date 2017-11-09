package 第一章_算法分析;


public class Practise_1_4_36 {
	/*
	 * 基于链表
	 * 元素类型 : int
	 * 
	 * 16 字节的对象开销
	 * 
	 * 每 push 一个元素，增加一个结点，所以就增加 32 字节
	 * 
	 * 由于链表对象本身有 16 字节的对象开销，还需要维护一个指向栈顶的指针
	 * 所以 push N 个元素后的空间成本为 16 + 8 + 32N ~32N
	 */
	static class Stack_List_Int {
		/*
		 * 每个结点 4 + 8 = 12字节 
		 * + 4 字节的填充字节
		 * 总共是 16 字节
		 * 
		 * + 16 字节的对象开销
		 * 总共是 32 字节
		 */
		static class Node {
			int item;
			Node next;
		}
		/*
		 * 8字节的引用
		 */
		private Node top = null;
		void push(int item) {
			Node n = new Node();
			n.item = item;
			n.next = top;
			top = n;
		}
	}
	/*
	 * 基于链表
	 * 元素类型 : Integer
	 * 
	 * 总空间大小为 16 + 8 + 56N ~ 56N
	 */
	static class Stack_List_Integer {
		/*
		 * 16 字节对象开销
		 * 8 字节 Integer 对象引用
		 * 8 字节结点引用
		 * 4 字节 int
		 * 16 字节对象开销
		 * 4 字节填充
		 */
		static class Node {
			Integer item;
			Node next;
		}
		private Node top = null;
		void push(int item) {
			Node n = new Node();
			n.next = top;
			n.item = new Integer(item);
			// = n.item = item;
			top = n;
		}
	}
	/*
	 * 基于大小可变的数组
	 * 
	 * 
	 * 如果 N 个元素不扩容，那么最少是 4N 的空间
	 * 如果 N 是2的幂次方，那么最大扩容量为 4 + 8 + ... + 2N = 4N - 4 ~16N
	 */
	static class Stack_Array_Int {
		private int[] items = new int[1];
		private int size;
		void push(int item) {
			
			if (size == items.length)
				resize(2 * size);
			items[size++] = item;
		}
		void resize(int newSize) {
			int[] newItems = new int[newSize];
			for (int i = 0; i < size; i++)
				newItems[i] = items[i];
			items = newItems;
		}
	}
	
	/*
	 * 每个Integer对象有 同时有4字节的value，16字节对象开销，4字节填充，8字节对象引用，
	 * 总共是32字节，扩容分析同上
	 * 所以空间成本为 ~32N 到 ~56N （4 * N * 8 + 24 * N）
	 */
	static class Stack_Array_Integer {
		private Integer[] items = new Integer[1];
		private int size;
		void push(int item) {
			if (size == items.length)
				resize(2 * size);
			items[size++] = item;
		}
		void resize(int newSize) {
			Integer[] newItems = new Integer[newSize];
			for (int i = 0; i < size; i++)
				newItems[i] = items[i];
			items = newItems;
		}
	}
}
