package 第一章_基础编程模型;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Practise_1_1_34 {
	/*
	 * 打印出最大和最小值
	 * 
	 * 只需要固定的变量就能实现
	 */
	private static int max = Integer.MIN_VALUE;
	private static int min = Integer.MAX_VALUE;
	public static void MaxMin(int n) {
		if(n > max) StdOut.println("最大值为 : " + (max = n));
		if(n < min) StdOut.println("最小值为 : " + (min = n));
	}
	
	/*
	 * 打印出所有数的中位数
	 * 
	 * 需要用数组把 N 个输入都保存起来
	 */
	public static void median(int[] arr) {
		int[] newArr = new int[arr.length];
		System.arraycopy(arr, 0, newArr, 0, arr.length);
		Arrays.sort(newArr);
		if(newArr.length % 2 == 0) 
			StdOut.println("中位数 : " + 
					(newArr[newArr.length / 2] + newArr[newArr.length / 2 - 1]) / 2);
		else
			StdOut.println("中位数 : " + newArr[newArr.length / 2]);
	}
	
	/*
	 * 打印出第 k 小的数，k 小于100
	 * 
	 * 只需要固定的变量和固定大小的数组
	 */
	private static int k = 3;
	private static int left = k;
	private static int[] arr = new int[k];
	public static int max(int[] arr) {
		int max = arr[0];
		for(int i = 1; i < arr.length; i++)
			if(max < arr[i])
				max = arr[i];
		return max;
	}
	public static void theKthMin(int n) {
		int max = 0;
		int maxIndex = 0;
		if(--left >= 0) {
			arr[left] = n;
			max = max(arr);
		} else {
			maxIndex = 0;
			max = arr[0];
			for(int i = 1; i < arr.length; i++)
				if(max < arr[i]) {
					max = arr[i];
					maxIndex = i;
				}
			if(n < max)
				arr[maxIndex] = n;
			max = max(arr);
		}
		StdOut.println("第 " + k + " 小的元素为 " + max);
	}
	
	/*
	 * 打印出所有数的平方和
	 * 
	 * 可以用固定的变量实现
	 */
	private static double squareSum = 0;
	public static void squareTotalSum(double n) {
		squareSum += n * n;
		StdOut.println("平方和为 : " + squareSum);
	}
	
	/*
	 * 打印出 N 个数的平均值
	 * 
	 * 可以用固定的变量实现
	 */
	public static int averageCount = 0;
	public static double averageSum = 0;
	public static void average(double n) {
		averageSum += n;
		averageCount++;
		StdOut.println("平均值为 : " + averageSum / averageCount);
	}
	
	/*
	 * 打印出大于平均值的数的百分比
	 * 
	 * 需要用数组把 N 个输入都保存起来
	 */
	public static void percentage(double[] a) {
		double sum = 0;
		for(int i = 0; i < a.length; i++)
			sum += a[i];
		double average = sum / a.length;
		int count = 0;
		for(int i = 0; i < a.length; i++)
			if(a[i] > average)
				count++;
		StdOut.println("大于平均数的百分比 : " + count / a.length);	
	}
	
	/*
	 * 将 N 个数按升序打印
	 * 
	 * 需要用数组把 N 个输入都保存起来
	 */
	public static void ascendOrder(double[] a) {
		Arrays.sort(a);
		StdOut.println(Arrays.toString(a));
	}
	
	/*
	 * 将 N 个数按照随机顺序打印
	 * 
	 * 啥意思啊😂，那就按照读入的顺序打印，不也是一种随机吗？难道不是吗？
	 */
	public static void randomPrint(double d) {
		StdOut.println(d);
	}
	/*
	 * 将 N 个数按照随机顺序打印
	 */
	public static void randomPrint(double[] arr) {
		boolean[] printed = new boolean[arr.length];
		int index = 0;
		int count = arr.length;
		while(--count >= 0) {
			while(printed[index] == true)
				index = StdRandom.uniform(0, arr.length);
			StdOut.print(arr[index] + " ");
			printed[index] = true;
		}
		StdOut.println();
	}
	public static void main(String[] args) {
	}
}
