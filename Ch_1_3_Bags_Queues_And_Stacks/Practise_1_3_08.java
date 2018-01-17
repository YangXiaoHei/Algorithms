package Ch_1_3_Bags_Queues_And_Stacks;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_3_08 {
    /*
     * 思路 :
     * 
     * 栈中数组初始容量为 1
     * push it
     * push was 数组扩容为 2
     * 
     * ======
     * was
     * it
     * ======
     * 
     * push the 数组扩容为 4
     * 
     * ======
     * 
     * the
     * was
     * it
     * ======
     * 
     * push best
     * pop best
     * 
     * 栈内容为
     * 
     * ======
     * 
     * the
     * was
     * it
     * ======
     * 
     * push of
     * push times 数组扩容为 8
     * 
     * ======
     * 
     * 
     * 
     * times
     * of
     * the
     * was
     * it
     * ======
     * 
     * pop times
     * pop of
     * pop the 数组缩容为 4
     * 
     * ======
     * 
     *
     * was
     * it
     * ======
     * 
     * push it
     * push was
     * pop was
     * 
     * ======
     * 
     * it
     * was
     * it
     * ======
     * 
     * push the
     * pop the
     * pop it
     * 
     * ======
     * 
     * 
     * was
     * it
     * ======
     */
	static class DoublingStackOfStrings {
		String[] items = new String[1];
		private int size;
		void resize(int size) {
			String[] tmp = new String[size];
			for(int i = 0; i < this.size; i++)
				tmp[i] = items[i];
			items = tmp;
		}
		void push(String s) {
			if (size == items.length)
				resize(size * 2);
			items[size++] = s;
		}
		String pop() {
			if(isEmpty())
				throw new RuntimeException("pop from a empty stack");
			String old = items[--size];
			items[size] = null;
			if (size > 0 && size == items.length / 4)
				resize(items.length / 2);
			return old;
		}
		boolean isEmpty() {
			return size == 0;
		}
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("size = " + size + " length : " + items.length + "\n");
			for(int i = 0; i < size; i++)
				sb.append(items[i] + " ");
			return sb.toString();
		}
	}
	public static void main(String[] args) {
		DoublingStackOfStrings stack = new DoublingStackOfStrings();
		/*
		 * it 入栈
		 * 扩容为 2
		 * was 入栈
		 * was 弹出
		 * the 入栈
		 * 扩容为 4
		 * best 入栈
		 * best 弹出
		 * of 入栈
		 * times 入栈
		 * times 弹出
		 * of 弹出
		 * the 弹出
		 * 缩容为2
		 * it 入栈
		 * 扩容为 4
		 * was 入栈
		 * was 弹出
		 * the 入栈
		 * the 弹出
		 * it 弹出
		 * 缩容为 2
		 */
		for(String s : "it was - the best - of times - - - it was - the - -".split(" ")) {
			if (s.equals("-"))
				stack.pop();
			else
				stack.push(s);
		}
		StdOut.println(stack);
	}
	// output :
	/*
	 * size = 1 length : 2
		it 
	 */
}
