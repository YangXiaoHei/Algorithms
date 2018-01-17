package Ch_1_2_Data_Abstraction;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;;

public class Practise_1_2_09 {
	static class Counter {
		private int count;
		private String id;
		public Counter(String id) { this.id = id; }
		public String toString() {
			return id + " " + count;
		}
		public void increment() { count++; }
		public int tally() { return count; }
	}
	public static int rank(int key, int[] arr, Counter counter) {
		int lo = 0, hi = arr.length - 1;
		while(lo <= hi) {
			int mid = (lo + hi) / 2;
			counter.increment();
			if			(key < arr[mid]) hi = mid - 1;
			else if		(key > arr[mid]) lo = mid + 1;
			else			return mid;
		}
		return -1;
	}
	public static int[] sourceArr(int N) {
		int[] arr = new int[N];
		for(int i = 0; i < N; i++)
			arr[i] = StdRandom.uniform(0, N);
		Arrays.sort(arr);
		return arr;
	}
	public static void main(String[] args) {
		int N = 1000000;
		Counter counter = new Counter("BinarySearch check times");
		int[] arr = sourceArr(N);
		int key = StdRandom.uniform(0, N);
		rank(key, arr, counter);
		StdOut.println("在长度为 " + N + " 的数组中查找 " + key);
		StdOut.println(counter);
	}
	// output : 
	/*
	 * 	在长度为 1000000 的数组中查找 626760
		BinarySearch check times 20
	 */
}
