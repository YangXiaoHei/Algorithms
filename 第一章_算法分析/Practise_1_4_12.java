package 第一章_算法分析;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_4_12 {
	/*
	 * find common element within O(N)
	 */
	public static int countCommon(int[] a, int[] b) {
		if (a == null || b == null) 
			throw new NullPointerException();
		int i = 0, j = 0, cnt = 0;
		while (i < a.length && j < b.length) {
			 if 	 (a[i] > b[j]) j++;
			 else if (a[i] < b[j]) i++;
			 else	 { cnt++; i++; j++; }
		}
		return cnt;
	}
	/*
	 * generate a sorted random array
	 */
	public static int[] sourceArr(int N) {
		int[] arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = StdRandom.uniform(0, 100);
		Arrays.sort(arr);
		return arr;
	}
	/*
	 * print array
	 */
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++)
			if ((i + 1) % 10 == 0)
				StdOut.printf("%3d\n", arr[i]);
			else
				StdOut.printf("%3d", arr[i]);
		StdOut.println();
	}
	public static void main(String[] args) {
		int[] a = sourceArr(100);
		int[] b = sourceArr(100);
		printArray(a);
		printArray(b);
		StdOut.println("common count is " + countCommon(a, b));
	}
	// output
	/*
	 *    0  3  4  4  4  5  5  6  6  7
		  7  7  8  9 11 11 12 13 14 19
		 20 20 22 24 27 27 27 27 28 29
		 29 30 31 32 33 34 35 36 38 39
		 40 40 41 41 42 42 43 44 45 48
		 49 50 50 51 53 54 56 57 57 59
		 60 63 63 63 64 67 68 70 71 72
		 73 74 74 75 76 77 80 80 83 85
		 85 85 86 86 86 88 89 89 91 91
		 93 94 94 95 95 96 97 97 98 99
		
		  0  0  2  3  4  4  6  9 10 11
		 12 13 14 14 20 20 21 22 22 24
		 26 26 27 28 30 31 34 34 35 35
		 38 41 42 42 43 43 44 45 47 47
		 48 49 50 50 52 52 53 53 54 55
		 56 56 56 58 61 63 64 64 64 65
		 65 65 66 66 66 66 68 69 69 73
		 74 74 75 76 76 76 77 78 78 78
		 81 81 81 82 83 83 84 86 87 88
		 88 89 90 91 91 91 93 94 99 99
		
		 common count is 52
	 */
}
