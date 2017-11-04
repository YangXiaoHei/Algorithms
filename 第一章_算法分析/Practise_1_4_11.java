package 第一章_算法分析;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_4_11 {
	static class StaticSETofInts {
		private int[] numbers;
		StaticSETofInts (int[] a) { numbers = a; }
		boolean contains(int key) {
			int lo = 0, hi = numbers.length;
			while (lo <= hi) {
				int mid = (lo + hi) / 2;
				if 		(numbers[mid] > key) hi = mid - 1;
				else if (numbers[mid] < key) lo = mid + 1;
				else	return true;
			}
			return false;
		}
		int howMany(int key) {
			int leftRank = minimumRank(key);
			if (leftRank < 0) return 0;
			int rightRank = maximumRank(key);
			return rightRank - leftRank + 1;
		}
		int minimumRank(int key) {
			int lo = 0, hi = numbers.length, mid = 0;
			while (lo <= hi) {
				mid = (lo + hi) / 2;
				if 		(numbers[mid] > key) hi = mid - 1;
				else if (numbers[mid] < key) lo = mid + 1;
				else if (lo != hi) { lo = 0; hi = mid; }
				else	break;
			}
			return numbers[mid] == key ? mid : -1;
		}
		int maximumRank(int key) {
			int lo = 0, hi = numbers.length - 1, mid = 0;
			while (lo <= hi) {
				mid = (lo + hi) / 2;
				if 		(numbers[mid] > key) hi = mid - 1;
				else if (numbers[mid] < key) lo = mid + 1;
				else if (mid == numbers.length - 1) break;
				else if (numbers[mid + 1] != key) break;
				else 	{ lo = mid + 1; hi = numbers.length - 1; }
			}
			return numbers[mid] == key ? mid : -1;
		}
	}
	public static int[] sourceArr(int N) {
		int[] arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = StdRandom.uniform(0, 20);
		Arrays.sort(arr);
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
		StaticSETofInts set = new StaticSETofInts(arr);
		int key = 5;
		StdOut.printf("the number of key %d is %d", key, set.howMany(key));
	}
	// output
	/*
	 * 	  0  0  0  0  0  0  1  1  1  1
		  1  1  2  2  2  3  3  3  3  3
		  3  4  4  4  4  4  4  5  5  5
		  5  6  6  6  6  6  7  7  7  7
		  7  8  8  8  8  9  9  9  9 10
		 10 10 10 11 11 11 11 11 11 12
		 12 12 13 13 13 13 13 13 13 14
		 14 14 14 14 14 14 14 15 15 15
		 16 16 16 16 17 17 17 17 17 17
		 18 18 18 18 18 18 19 19 19 19

		the number of key 5 is 4
	 */
}
