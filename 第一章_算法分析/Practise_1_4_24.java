package 第一章_算法分析;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Practise_1_4_24 {
	/*
	 * 查找丢鸡蛋会碎的起始楼层
	 */
	static class ThrowingEggsFromABuilding {
		public static int searchStorey(boolean[] building) {
			int N = building.length;
			if (!building[N - 1]) return -1;
			int lo = 0, hi = N - 1, mid = 0;
			while (lo < hi) {
				mid = (lo + hi) / 2;
				if ( building[mid]) hi = mid;
				if (!building[mid]) lo = mid + 1;
			}
			if (lo > hi) return -1;
			return hi;
		}
	}
	/*
	 * 获取一个随机楼层
	 */
	public static boolean[] sourceArr(int N) {
		int leftCount = StdRandom.uniform(0, N + 1);
		boolean[] arr = new boolean[N];
		for (int i = 0; i < leftCount; i++)
			arr[i] = false;
		for (int i = leftCount; i < N; i++)
			arr[i] = true;
		return arr;
	}
	/*
	 * 打印楼层
	 */
	public static void printArray(boolean[] arr) {
		for (int i = 0; i < arr.length; i++)
			StdOut.printf("%4d ", i + 1);
		StdOut.println();
		for (int i = 0; i < arr.length; i++)
			StdOut.print(arr[i] + " ");
		StdOut.println();
	}
	
	public static void main(String[] args) {
		boolean[] arr = sourceArr(5);
		printArray(arr);
		int floor = ThrowingEggsFromABuilding.searchStorey(arr);
		StdOut.printf("丢鸡蛋从楼层 %d 开始会摔碎", floor + 1);
	}
	// output
	/*
	 *    1    2    3    4    5 
		false false false true true 
		丢鸡蛋从楼层 4 开始会摔碎
	 */
}
