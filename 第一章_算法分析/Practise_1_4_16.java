package 第一章_算法分析;

import edu.princeton.cs.algs4.*;

public class Practise_1_4_16 {
	/*
	 * 寻找局部最小值
	 */
	public static int localMinimum(int[] a) {
		int lo = 0, hi = a.length - 1, mid = 0;
		while (hi - lo > 1) {
			mid = (lo + hi) / 2;
			if (a[mid] > a[mid - 1] && a[mid] < a[mid + 1]) break;
			if (a[mid - 1] < a[mid + 1])
				hi = mid;
			else
				lo = mid;
		}
		return hi - lo == 1 ? -1 : mid;
	}
	/*
	 * 产生已排序随机数组
	 */
	public static int[] sourceArr(int N) {
		int[] arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = StdRandom.uniform(0, 100);
		return arr;
	}
	/*
	 * 打印数组
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
		int[] arr = sourceArr(10);
		printArray(arr);
		StdOut.println("局部最小值 : " + localMinimum(arr));
	}
	// output
	/*
	 *  62  4 43 65 14 11 17 55 54 62

		局部最小值 : 6
		
		54 27 19 53  2 82 66 51 37 86

		局部最小值 : -1

		79 79 83 39 43 45  7 87 21 72

		局部最小值 : 4
	 */
}
