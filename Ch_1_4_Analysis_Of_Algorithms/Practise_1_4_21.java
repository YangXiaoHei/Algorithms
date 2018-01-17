package Ch_1_4_Analysis_Of_Algorithms;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_4_21 {
	static class StaticsSETofInts {
		private int[] a;
		StaticsSETofInts(int[] keys) {
			a = new int[keys.length];
			for (int i = 0; i < keys.length; i++)
				a[i] = keys[i];
			Arrays.sort(a);
		}
		boolean contains(int key) {
			return rank(key) != -1;
		}
		private int rank(int key) {
			int lo = 0, hi = a.length - 1;
			while (lo <= hi) {
				int mid = (lo + hi) / 2;
				if		(key < a[mid]) hi = mid - 1;
				else if (key > a[mid]) lo = mid + 1;
				else	return mid;
			}
			return -1;
		}
	}
	public static int[] sourceArr(int N) {
		Set<Integer> set = new HashSet<Integer>();
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			int a = StdRandom.uniform(0, 100);
			while (set.contains(a))
				a = StdRandom.uniform(0, 100);
			arr[i] = a;
		}
		return arr;
	}
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++)
			if ((i + 1) % 10 == 0)
				StdOut.printf("%3d\n", arr[i]);
			else
				StdOut.printf("%3d", arr[i]);
		StdOut.println();
	}
	public static void main(String[] args) {
		int[] arr = sourceArr(100);
		printArray(arr);
		StaticsSETofInts ints = new StaticsSETofInts(arr);
		int key = 5;
		if (ints.contains(key))
			StdOut.println("contain " + key);
		else
			StdOut.println("not exist");
	}
	// output
	/*
	 *   85 67 65 32 66 21 87 70 84 51
		 88 11 41 30 90 37 10 17 27 20
		 21 74 72 42  4 45 82 42 87 82
		 73 28  6 14 72 26 98 33 82 49
		 86 63 87 54 38 22 86 16 74 96
		 51 96 93 58 42 21 53 85 14 30
		 20 56 37 29 62 43 65 37 78 91
		 16 68 27 88 37 51 63 60 50 40
		 35 81 35 79 94 70 77 41 92 42
		 44 45 36 87 30 72 57 51  1  9
		
		not exist

	 */
}
