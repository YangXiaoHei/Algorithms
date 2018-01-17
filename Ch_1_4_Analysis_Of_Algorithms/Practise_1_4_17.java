package Ch_1_4_Analysis_Of_Algorithms;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_4_17 {
	/*
	 *  最遥远的一对
	 *  
	 *  思路 :
	 *  
	 *  最遥远的一对一定是由最大值和最小值构成的，找出最大最小即可
	 *  
	 *  
	 */
	public static void fairestPair(double[] a) {
		double max = Double.MIN_VALUE;
		double min = Double.MAX_VALUE;
		for (int i = 0; i < a.length; i++) {
			if (a[i] < min) min = a[i];
			if (a[i] > max) max = a[i];
		}
		StdOut.printf("fairest Pair : %8.3f %8.3f", max, min);
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
		fairestPair(arr);
	}
	// output
	/*
	 *  -25.864  47.567   0.725 -99.259 -91.011 -65.103 -13.016  75.591 -50.611 -74.727

		fairest Pair :   75.591  -99.259
	 */
}
