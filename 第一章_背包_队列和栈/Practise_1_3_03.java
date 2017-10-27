package 第一章_背包_队列和栈;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_3_03 {
	/*
	 * 数组实现能自动调整空间的栈
	 */
	static class ResizingArrayStack<Item> {
		@SuppressWarnings("unchecked")
		private Item[] items = (Item[])(new Object[1]);
		private int N;
		private boolean log = true;
		void push(Item item) {
			if (N == items.length)
				resize(items.length * 2);
			items[N++] = item;
		}
		Item pop() {
			if (isEmpty())
				throw new RuntimeException("attempt to pop from a empty stack");
			Item pop = items[--N];
			items[N] = null;
			if (N > 0 && N == items.length / 4)
				resize(items.length / 2);
			if (log)
				StdOut.print(pop + " ");
			return pop;
		}
		boolean isEmpty() {
			return N == 0;
		}
		@SuppressWarnings("unchecked")
		void resize(int adjustment) {
			Item[] tmp = (Item[])(new Object[adjustment]);
			for(int i = 0; i < N; i++)
				tmp[i] = items[i];
			items = tmp;
		}
		void clear() {
			setLog(false);
			while(!isEmpty())
				pop();
			setLog(true);
		}
		void setLog(boolean log) {
			this.log = log;
		}
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("=== Top ===\n");
			for(int i = N - 1; i >= 0; i--)
				sb.append(items[i] + "\n");
			sb.append("=== N : " + N + " size : " + items.length +  " ===\n");
			return sb.toString();
		}
	}
	/*
	 * 测试用例
	 * 
	 */
	public static void stackTest() {
		ResizingArrayStack<String> stack = new ResizingArrayStack<String>();
		stack.setLog(false);
		for(int i = 0; i < 10; i++)
			stack.push(Integer.toString(i));
		StdOut.println(stack);
		
		for(int i = 0; i < 5; i++)
			stack.pop();
		StdOut.println(stack);
		
		stack.clear();
		StdOut.println(stack);
		
		StdOut.println("pop new elem from a empty stack");
		stack.pop();
	}
	
	private static ResizingArrayStack<Integer> stack = new ResizingArrayStack<Integer>();
	/*
	 * a ✅
	 */
	public static void aSequenceIsPossible() {
		stack.clear();
		
		StdOut.print("a : ");
		stack.push(0);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		stack.push(5);
		stack.push(6);
		stack.push(7);
		stack.push(8);
		stack.push(9);
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		StdOut.println();
	}
	/*
	 * b ❌
	 */
	public static void bSequenceIsImpossible() {
		StdOut.println("b : " + "impossible");
	}
	/*
	 * c ✅
	 */
	public static void cSequenceIsPossible() {
		stack.clear();
		
		StdOut.print("c : ");
		stack.push(0);
		stack.push(1);
		stack.push(2);
		stack.pop();
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.pop();
		stack.push(6);
		stack.pop();
		stack.push(7);
		stack.pop();
		stack.pop();
		stack.push(8);
		stack.pop();
		stack.push(9);
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		
		StdOut.println();
	}
	
	/*
	 * d ✅
	 */
	public static void dSequenceIsPossible() {
		stack.clear();
		
		StdOut.print("c : ");
		stack.push(0);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		stack.push(5);
		stack.pop();
		stack.push(6);
		stack.pop();
		stack.push(7);
		stack.pop();
		stack.push(8);
		stack.pop();
		stack.push(9);
		stack.pop();
		
		StdOut.println();
	}
	/*
	 * e ✅
	 */
	public static void eSequenceIsPossible() {
		stack.clear();
		
		StdOut.print("e : ");
		stack.push(0);
		stack.push(1);
		stack.pop();
		stack.push(2);
		stack.pop();
		stack.push(3);
		stack.pop();
		stack.push(4);
		stack.pop();
		stack.push(5);
		stack.pop();
		stack.push(6);
		stack.pop();
		stack.push(7);
		stack.push(8);
		stack.push(9);
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();

		StdOut.println();
	}
	/*
	 * f ❌
	 */
	public static void fSequenceIsImpossible() {
		StdOut.println("b : " + "impossible");
	}
	/*
	 * g ❌
	 */
	public static void gSequenceIsImpossible() {
		StdOut.println("g : " + "impossible");
	}
	/*
	 * h ✅
	 */
	public static void hSequenceIsPossible() {
		stack.clear();
		
		StdOut.print("h : ");
		stack.push(0);
		stack.push(1);
		stack.push(2);
		stack.pop();
		stack.pop();
		stack.push(3);
		stack.push(4);
		stack.pop();
		stack.pop();
		stack.push(5);
		stack.push(6);
		stack.pop();
		stack.pop();
		stack.push(7);
		stack.push(8);
		stack.pop();
		stack.pop();
		stack.push(9);
		stack.pop();
		stack.pop();
		
		StdOut.println();
	}
	
	public static void main(String[] args) {
		aSequenceIsPossible();
		bSequenceIsImpossible();
		cSequenceIsPossible();
		dSequenceIsPossible();
		eSequenceIsPossible();
		fSequenceIsImpossible();
		gSequenceIsImpossible();
		hSequenceIsPossible();
	}
	// output :
	/*
	 * 	a : 4 3 2 1 0 9 8 7 6 5 
		b : impossible
		c : 2 5 6 7 4 8 9 3 1 0 
		c : 4 3 2 1 0 5 6 7 8 9 
		e : 1 2 3 4 5 6 9 8 7 0 
		b : impossible
		g : impossible
		h : 2 1 4 3 6 5 8 7 9 0 
	 */
}
