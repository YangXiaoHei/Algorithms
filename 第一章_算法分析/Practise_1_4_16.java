package 第一章_算法分析;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_4_16 {
	/*
	 * 思路 : 
	 * 
	 * 首先我们将数组排序，这是一个 N * log(N) 的操作
	 * 然后我们采用如下策略遍历整个数组，用一个变量来记录最小距离，将其初始化为 Double.Max_Value
	 * 从 i = 1 开始，比较 a[i] 和 a[i - 1]
	 * 
	 * 如果相等，那么就忽略，让 i 递增
	 * 如果不相等，我们计算出 a[i] 和 a[i - 1] 的最小距离，判断是否小于之前的记录，若小于，那么就更新记录
	 * 
	 */
	public static void closestPair(double[] a) {
		if (a == null || a.length < 2)
			throw new IllegalArgumentException("a is null or elements count less than required");
		Arrays.sort(a);
		double n1 = 0, n2 = 0;
		double distance = Double.MAX_VALUE;
		for (int i = 1; i < a.length; i++) {
			if (a[i - 1] != a[i]) {
				double dis = Math.abs(a[i - 1] - a[i]);
				if (dis < distance) {
					distance = dis;
					n1 = a[i - 1]; 
					n2 = a[i];
				}
			} 
		}
		StdOut.printf("closest pair is %8.3f %8.3f", n1, n2);
	}
	/*
	 * 产生已排序随机数组
	 */
	public static double[] sourceArr(int N) {
		double[] arr = new double[N];
		for (int i = 0; i < N; i++)
			arr[i] = StdRandom.uniform(-100.0, 100.0);
		return arr;
	}
	/*
	 * 打印数组
	 */
	public static void printArray(double[] arr) {
		for (int i = 0; i < arr.length; i++)
			if ((i + 1) % 10 == 0)
				StdOut.printf("%8.3f\n", arr[i]);
			else
				StdOut.printf("%8.3f", arr[i]);
		StdOut.println();
	}
	public static void main(String[] args) {
		double[] arr = sourceArr(10);
		printArray(arr);
		closestPair(arr);
	}
	// output
	/*
	 *    63.609 -18.195 -58.362  41.024  10.381 -70.240 -28.405  11.502 -25.867  -9.362

		  closest pair is   10.381   11.502
	 */
}
