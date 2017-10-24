package 第一章_基础编程模型;

import edu.princeton.cs.algs4.*;
import java.util.*;

public class Practise_1_1_22 {
	private static int depath = 0;
	/*
	 * 随机数组
	 */
	public static int[] sourceArr(int N) {
		int[] arr = new int[N];
		for(int i = 0; i < N; i++)
			arr[i] = StdRandom.uniform(N);
		Arrays.sort(arr);
		return arr;
	}
	/*
	 * 测试用例
	 */
	public static void practise_1_1_22() {
		int[] arr = sourceArr(10000);
		int key = StdRandom.uniform(10000);
		StdOut.println("index = " + binarySearch(key, arr) + " 递归深度 = " + depath);
	}
	
	/*
	 * 二分查找入口
	 */
	public static int binarySearch(int key, int a[]) {
		return recursiveRank(key, a, 0, a.length - 1);
	}
	
	/*
	 * 递归的二分查找
	 */
	public static int recursiveRank(int key, int a[], int lo, int hi) {
		int mid = (lo + hi) / 2;
		depath++;
		while(lo <= hi) {
			if(key < a[mid]) return recursiveRank(key, a, lo, mid - 1);
			if(key > a[mid]) return recursiveRank(key, a, mid + 1, hi);
			return mid;
		}
		return -1;
	}
	public static void main(String[] args) {
		practise_1_1_22();
	}
}
