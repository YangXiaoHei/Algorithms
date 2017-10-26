package 第一章_背包_队列和栈;

import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;

public class Practise_1_3_05 {
	/*
	 * 用数组实现长度可调整的栈
	 */
	@SuppressWarnings("unchecked")
	static class ResizingArrayStack<Item> implements Iterable<Item> {
		Item[] items = (Item[])(new Object[1]);
		private int N;
		void push(Item item) {
			if (N == items.length)
				resize(items.length * 2);
			items[N++] = item;
		}
		Item pop() {
			if (isEmpty())
				throw new RuntimeException("stack is empty!");
			Item old = items[--N];
			items[N] = null;
			if (N > 0 && N == items.length / 4)
				resize(items.length / 2);
			return old;
		}
		void resize(int size) {
			Item[] tmp = (Item[])(new Object[size]);
			for(int i = 0; i < N; i++)
				tmp[i] = items[i];
			items = tmp;
		}
		boolean isEmpty() {
			return N == 0;
		}
		public Iterator<Item> iterator() {
			return new Iterator<Item>() {
				private int current = N;
				public boolean hasNext() {
					return current > 0;
				}
				public Item next() {
					return items[--current];
				}
				public void remove() {
					throw new RuntimeException("不支持哦");
				}
			};
		}
	}
	/*
	 * 打印 N 的二进制表示
	 */
	public static void printBinaryString(long N) {
		ResizingArrayStack<Integer> stack = new ResizingArrayStack<Integer>();
		while(N > 0) {
			stack.push((int)(N % 2));
			N /= 2;
		}
		for(Integer i : stack) StdOut.print(i);
		StdOut.println();
	}
	public static void main(String[] args) {
		printBinaryString(50);
	}
	// output :
	/*
	 * 110010

	 */
}
