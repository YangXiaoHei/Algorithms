package Ch_1_4_Analysis_Of_Algorithms;

import edu.princeton.cs.algs4.*;

public class Practise_1_4_37 {
	static class FixedCapacityStackOfInts {
		private int[] items;
		private int capacity;
		private int size;
		FixedCapacityStackOfInts(int cap) {
			items = new int[cap];
			capacity = cap;
		}
		boolean isEmpty() { return size == 0; }
		boolean isFull() { return size == capacity; }
		void push(int item) {
			if (isFull())
				throw new RuntimeException("stack is full");
			items[size++] = item;
		}
		int pop() {
			if (isEmpty())
				throw new RuntimeException("pop from a empty stack");
			return items[--size];
		}
	}
	static class FixedCapacityStackOfGeneric<T> {
		private T[] items;
		private int capacity;
		private int size;
		@SuppressWarnings("unchecked")
		FixedCapacityStackOfGeneric(int cap) {
			items = (T[])new Object[cap];
			capacity = cap;
		}
		boolean isEmpty() { return size == 0; }
		boolean isFull() { return size == capacity; }
		void push(T item) {
			if (isFull())
				throw new RuntimeException("stack is full");
			items[size++] = item;
		}
		T pop() {
			if (isEmpty())
				throw new RuntimeException("pop from a empty stack");
			return items[--size];
		}
	}
	
	static class DoublingRatio {
		static double timeTrial_int(int N) {
			FixedCapacityStackOfInts ints = new FixedCapacityStackOfInts(N);
			Stopwatch timer = new Stopwatch();
			for (int i = 0; i < N; i++) {
				ints.push(i);
			}
			while (!ints.isEmpty()) {
				int a = ints.pop(); 
			}
			return timer.elapsedTime();
		}
		static double timerTiral_Generic(int N) {
			FixedCapacityStackOfGeneric<Integer> ints =
					new FixedCapacityStackOfGeneric<Integer>(N);
			Stopwatch timer = new Stopwatch();
			for (int i = 0; i < N; i++) {
				ints.push(i);
			}
			while (!ints.isEmpty()) {
			  int a = ints.pop();
			}
			return timer.elapsedTime();
		}
	}
	public static void main(String[] args) {
		StdOut.printf("基本类型操作耗时 : %f\n", 
				DoublingRatio.timeTrial_int(10000000));
		StdOut.printf("自动装箱拆箱操作耗时 : %f\n", 
				DoublingRatio.timerTiral_Generic(10000000));
	}
	// output
	/*
	 * 	基本类型操作耗时 : 0.038000
		自动装箱拆箱操作耗时 : 3.019000

	 */
}
