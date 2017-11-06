package 第一章_算法分析;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_4_23 {
	/*
	 * 二分查找
	 */
	public static int binarySearch(double[] a, double key) {
		return binarySearchRecur(a, key, 0, a.length - 1);
	}
	public static int binarySearchRecur(double[] a, double key, int lo, int hi) {
		int mid = (lo + hi) / 2;
		int N = a.length;
		double threshold = 1.0 / (N * N); 
		while (lo <= hi) {
			double differ = Math.abs(a[mid] - key);
			if 		(differ <= threshold) return mid;
			else if (a[mid] > key) return binarySearchRecur(a, key, lo, hi - 1);
			else 	return binarySearchRecur(a, key, lo + 1, hi);
		}
		return -1;
    }
	/*
	 * 产生已排序随机数组
	 */
	public static double[] sourceArr(int N) {
		double[] arr = new double[N];
		for (int i = 0; i < N; i++) {
			int p = StdRandom.uniform(1, 10);
			int q = StdRandom.uniform(1, 20);
			while (q <= p) 
				q = StdRandom.uniform(1, 20);
			arr[i] = p * 1.0 / q;
		}
			
		Arrays.sort(arr);
		return arr;
	}
	/*
	 * 打印数组
	 */
	public static void printArray(double[] arr) {
		for (int i = 0; i < arr.length; i++)
			if ((i + 1) % 10 == 0)
				StdOut.printf("%2.3f\n", arr[i]);
			else
				StdOut.printf("%2.3f ", arr[i]);
		StdOut.println();
	}
	public static void main(String[] args) {
		double[] arr = sourceArr(10);
		printArray(arr);
		StdOut.println("result is " + binarySearch(arr, 4.0 / 7));
	}
	// output
	/*
	 * 0.083 0.111 0.231 0.250 0.429 0.462 0.500 0.556 0.571 0.800

		result is 8

	 */
}
