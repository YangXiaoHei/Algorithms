package Ch_1_4_Analysis_Of_Algorithms;

import edu.princeton.cs.algs4.*;

public class Practise_1_4_43 {
	interface Stack<T> {
		void push(T item);
		T pop();
		boolean isEmpty();
		int size();
		void clear();
	}
	/*
	 * 基于大小可变的数组
	 */
	static class ResizingArrayStack<T> implements Stack<T> {
		@SuppressWarnings("unchecked")
		private T[] items = (T[])new Object[1];
		private int size;
		@SuppressWarnings("unchecked")
		private void resize(int newSize) {
			T[] newItems = (T[])new Object[newSize];
			for (int i = 0; i < size; i++)
				newItems[i] = items[i];
			items = newItems;
		}
		public void push(T item) {
			if (size == items.length)
				resize(size * 2);
			items[size++] = item;
		}
		public T pop() {
			if (isEmpty())
				throw new RuntimeException("pop from a empty stack");
			T del = items[--size];
			items[size] = null;
			return del;
		}
		public void clear() {
			while (!isEmpty())
				pop();
		}
		public boolean isEmpty() { return size == 0; }
		public int size() { return size; }
	}
	
	/*
	 * 基于单向链表
	 */
	static class LinkedListStack<T> implements Stack<T> {
		private class Node {
			T item;
			Node next;
			Node(T item, Node next) { this.item = item; this.next = next; }
		}
		private int size;
		private Node top = null;
		public void push(T item) {
			size++;
			top = new Node(item, top);
		}
		public T pop() {
			if (isEmpty())
				throw new RuntimeException("pop from a empty stack");
			size--;
			T del = top.item;
			top.item = null;
			top = top.next;
			return del;
		}
		public void clear() {
			while (!isEmpty())
				pop();
		}
		public boolean isEmpty() { return size == 0; }
		public int size() { return size; }
	}
	static int[] sourceArr(int N) {
		int[] arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = StdRandom.uniform(-10000, 10000);
		return arr;
	}
	static class DoublingRatio {
		static double pushTest(Stack<Integer> stack, int N) {
			Stopwatch timer = new Stopwatch();
			for (int i : sourceArr(N))
				stack.push(i);
			return timer.elapsedTime();
		}
		static double popTest(Stack<Integer> stack, int N) {
			for (int i : sourceArr(N))
				stack.push(i);
			Stopwatch timer = new Stopwatch();
			while(!stack.isEmpty())
				stack.pop();
			return timer.elapsedTime();
		}
		static void pushComparasion() {
			Stack<Integer> stack1 = new ResizingArrayStack<Integer>(),
						   stack2 = new LinkedListStack<Integer>();
			for (int i = 200000, j = 0; j < 8; i += i, j++) {
				double arr = pushTest(stack1, i);
				double list = pushTest(stack2, i);
				StdOut.printf("【PUSH】规模 : %d 数组耗时 : %.3f 链表耗时 : %.3f 链表 / 数组 = %f\n", 
						i, arr,  list, list / arr);
				stack1.clear();
				stack2.clear();
			}
		}
		static void popComparasion() {
			Stack<Integer> stack1 = new ResizingArrayStack<Integer>(),
					       stack2 = new LinkedListStack<Integer>();
			for (int i = 200000, j = 0; j < 8; i += i, j++) {
				double arr = popTest(stack1, i);
				double list = popTest(stack2, i);
				StdOut.printf("【POP】规模 : %d 数组耗时 : %.3f 链表耗时 : %.3f  链表 / 数组 = %f\n", 
						i, arr,  list, list / arr);
			}
		}
	}
	public static void main(String[] args) {
		DoublingRatio.pushComparasion();
		DoublingRatio.popComparasion();
	}
	// output
	/*
	 * 
	 * 
	 * 	【PUSH】规模 : 200000 数组耗时 : 0.029 链表耗时 : 0.023 链表 / 数组 = 0.793103
		【PUSH】规模 : 400000 数组耗时 : 0.015 链表耗时 : 0.049 链表 / 数组 = 3.266667
		【PUSH】规模 : 800000 数组耗时 : 0.019 链表耗时 : 0.055 链表 / 数组 = 2.894737
		【PUSH】规模 : 1600000 数组耗时 : 0.609 链表耗时 : 1.144 链表 / 数组 = 1.878489
		【PUSH】规模 : 3200000 数组耗时 : 0.075 链表耗时 : 3.402 链表 / 数组 = 45.360000
		【PUSH】规模 : 6400000 数组耗时 : 0.412 链表耗时 : 1.801 链表 / 数组 = 4.371359
		【PUSH】规模 : 12800000 数组耗时 : 1.242 链表耗时 : 7.836 链表 / 数组 = 6.309179
		【PUSH】规模 : 25600000 数组耗时 : 9.230 链表耗时 : 29.225 链表 / 数组 = 3.166306
		
		【POP】规模 : 200000 数组耗时 : 0.004 链表耗时 : 0.010  链表 / 数组 = 2.500000
		【POP】规模 : 400000 数组耗时 : 0.000 链表耗时 : 0.011  链表 / 数组 = Infinity
		【POP】规模 : 800000 数组耗时 : 0.001 链表耗时 : 0.004  链表 / 数组 = 4.000000
		【POP】规模 : 1600000 数组耗时 : 0.001 链表耗时 : 0.010  链表 / 数组 = 10.000000
		【POP】规模 : 3200000 数组耗时 : 0.003 链表耗时 : 0.016  链表 / 数组 = 5.333333
		【POP】规模 : 6400000 数组耗时 : 0.006 链表耗时 : 0.037  链表 / 数组 = 6.166667
		【POP】规模 : 12800000 数组耗时 : 0.012 链表耗时 : 0.074  链表 / 数组 = 6.166667
		【POP】规模 : 25600000 数组耗时 : 0.025 链表耗时 : 0.217  链表 / 数组 = 8.680000
	 */
}
