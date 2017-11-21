package 第一章_算法分析;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_4_14 {
    /*
     * 这里直接使用暴力解法则时间为 C(n, 4) = N(N - 1)(N - 2)(N - 3)/24 ~N^4
     * 通过改进最后一个循环，效率变为 ~N^3 * log(N)
     * 
     */
	public static int binarySearch(int key, int[] a) {
		int lo = 0, hi = a.length - 1, mid = 0;
		while (lo < hi) {
			mid = (int)Math.ceil((lo + hi) / 2.0);
			if (a[mid] < key)
			    lo = mid;
			else
			    hi = mid - 1;
 		}
		if (a[lo] == key) return lo;
		return ++lo == a.length || a[lo] != key ? -1 : lo;
	}
	
	static class FourSum {
		public static int count(int[] a) {
			int cnt = 0, N = a.length, index = 0;
			Arrays.sort(a);
			for (int i = 0; i < N; i++) 
				for (int j = i + 1; j < N; j++)
					for (int k = j + 1; k < N; k++)
						if ((index = binarySearch(-(a[i] + a[j] + a[k]), a)) > k) {
							cnt++;
							StdOut.printf("a[%d] = %d a[%d] = %d a[%d] = %d a[%d] = %d\n",
										i, a[i], j, a[j], k, a[k], index, a[index]);
						}
			return cnt;
		}
	}
	/*
	 * generate a sorted random array
	 */
	public static int[] sourceArr(int N) {
		int[] arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = StdRandom.uniform(-100, 100);
		Arrays.sort(arr);
		return arr;
	}
	/*
	 * print array
	 */
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++)
			if ((i + 1) % 10 == 0)
				StdOut.printf("%5d\n", arr[i]);
			else
				StdOut.printf("%5d", arr[i]);
		StdOut.println();
	}
	public static void main(String[] args) {
		int[] a = sourceArr(10);
		printArray(a);
		StdOut.println("four sum is " + FourSum.count(a));
	}
	// output
	/*
	 *     	-90  -87  -87  -43  -16   -9   34   35   50   68

			a[1] = -87 a[4] = -16 a[7] = 35 a[9] = 68
			a[2] = -87 a[4] = -16 a[7] = 35 a[9] = 68
			a[3] = -43 a[4] = -16 a[5] = -9 a[9] = 68
			four sum is 3
	 */
}
