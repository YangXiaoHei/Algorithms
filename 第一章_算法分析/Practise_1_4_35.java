package 第一章_算法分析;

public class Practise_1_4_35 {
	/*
	 * 基于链表
	 * 元素类型 : int
	 */
	static class Stack_List_Int {
		private class Node {
			int item;
			Node next;
		}
		private Node top = null;
		void push(int item) {
			/*
			 * 压入 N 个元素时
			 * 每压入一个元素，需要设置元素的下一个结点，同时需要用变量记录栈顶位置
			 * 总引用数量为 2 * N
			 * 
			 * 每压入一个元素，需要创建一个新的结点，故创建的对象为 N
			 */
			Node n = new Node();
			n.item = item;
			n.next = top;
			top = n;
		}
	}
	/*
	 * 基于链表
	 * 元素类型 : Integer
	 */
	static class Stack_List_Integer {
		private class Node {
			Integer item;
			Node next;
		}
		private Node top = null;
		void push(int item) {
			/*
			 * 每压入一个元素，需要设置结点的两个引用，同时需要记录栈顶位置
			 * 故压入 N 个元素，在设置引用时间上的浪费是 3 * N
			 * 
			 * 每压入一个元素，需要创建 结点对象 和 Integer 对象(自动装箱或显示创建)
			 * 故压入 N 个元素，浪费在创建对象上的时间是 2 * N
			 */
			Node n = new Node();
			n.next = top;
			n.item = new Integer(item);
			// = n.item = item;
			top = n;
		}
	}
	/*
	 * 基于大小可变的数组
	 */
	static class Stack_Array_Int {
		private int[] items = new int[1];
		private int size;
		void push(int item) {
			/* 
			 * 第一次扩容后容量为2,旧元素迁移 1 次
			 * 第二次扩容 4 -> 2
			 * 第三次扩容 8 -> 4
			 * 第四次扩容 16 -> 8
			 * 
			 * 压入 N 个元素过程中，造成的旧数组访问次数为
			 * 
			 * 1 + 2 + 4 + 8 + ... + N/2 < N
			 * 
			 * 同时每压入一个元素，不管是否扩容，都将新元素添加至数组,这也算作一次数据的引用设置
			 * 所以总的数据引用时间成本为 扩容造成的旧数组访问次数 + N --> ~2N
			 * 
			 * N 次 push 操作会造成最多 lgN 次扩容，
			 * 每次扩容都会创建一个新数组，因此创建对象的数量为 lgN
			 * 
			 */
			if (size == items.length)
				resize(2 * size);
			items[size++] = item;
		}
		void resize(int newSize) {
			int[] newItems = new int[newSize];
			for (int i = 0; i < size; i++)
				newItems[i] = items[i];
			// 1次
			items = newItems;
		}
	}
	
	
	
	public static void main(String[] args) {
		
	}
}
