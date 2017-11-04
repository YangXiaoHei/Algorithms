package 第一章_算法分析;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_4_10 {
	static class BinarySearchModified {
		/*
		 *  in the worst situation total time will close to O(N)
		 */
		public static int rankFoolish(int key, int[] array) {
			int lo = 0, hi = array.length - 1, mid = 0;
			while (lo <= hi) {
				mid = (lo + hi) / 2;
				if 		(array[mid] > key) hi = mid - 1;
				else if (array[mid] < key) lo = mid + 1;
				else	break;
			}
			if (array[mid] != key) return -1;
			while (mid >= 0 && array[mid] == key) mid--;
			return mid + 1;
		}
		/*
		 * continue to search until the middle index equals to right side index
		 * 
		 * in that way, we can guarantee O(klogN) in the worst situation
		 */
		public static int rank(int key, int[] array) {
			int lo = 0, hi = array.length - 1, mid = 0;
			while (lo <= hi) {
				mid = (lo + hi) / 2;
				if 		(array[mid] > key) {  hi = mid - 1; }
				else if (array[mid] < key) {  lo = mid + 1; }
				else if (lo != hi)	{ lo = 0; hi = mid; }
				else 	 break; 
			}
			return array[mid] == key ? mid : -1;
		}
		
		public static int[] sourceArr(int N) {
			int[] arr = new int[N];
			for (int i = 0; i < N; i++)
				arr[i] = StdRandom.uniform(0, 10);
			Arrays.sort(arr);
			return arr;
		}
		public static void printArray(int[] arr) {
			for (int i = 0; i < arr.length; i++)
				if ((i + 1) % 10 == 0)
					StdOut.print(arr[i] + "\n");
				else
					StdOut.print(arr[i] + " ");
			StdOut.println();
		}
		public static void test(int key, int N) {
			int[] arr = sourceArr(N);
			printArray(arr);
			StdOut.println("minimum index of " + key + " is " + rank(key, arr));
		}
	}
	public static void main(String[] args) {
		BinarySearchModified.test(3, 100);
	}
	// output
	/*
	 *  0 0 0 0 0 0 0 0 0 0
		0 0 0 1 1 1 1 1 1 1
		1 1 1 1 1 2 2 2 2 2
		2 2 2 2 3 3 3 3 3 3
		3 3 3 3 4 4 4 4 4 4
		4 4 4 4 4 4 5 5 5 5
		5 5 5 5 6 6 6 6 6 6
		6 6 7 7 7 7 7 7 7 7
		7 7 7 7 8 8 8 8 8 8
		9 9 9 9 9 9 9 9 9 9
		
		minimum index of 3 is 34
	 */
}
