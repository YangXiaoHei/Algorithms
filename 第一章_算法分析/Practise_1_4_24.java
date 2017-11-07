package 第一章_算法分析;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Practise_1_4_24 {
	/*
	 * 查找丢鸡蛋会碎的起始楼层
	 */
	static class ThrowingEggsFromABuilding {
		private static int brokenEggs;
		private static int throwTimes;
		public static int searchStorey(boolean[] building) {
			brokenEggs = 0;
			throwTimes = 0;
			int N = building.length;
			if (!building[N - 1]) return -1;
			int lo = 0, hi = N - 1, mid = 0;
			while (lo < hi) {
				mid = (lo + hi) / 2;
				throwTimes++;
				if ( building[mid]) { hi = mid; brokenEggs++; }
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
		int leftCount = StdRandom.uniform(1, N);
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
		boolean[] arr = sourceArr(100);
		printArray(arr);
		int floor = ThrowingEggsFromABuilding.searchStorey(arr);
		StdOut.printf("丢鸡蛋从楼层 %d 开始会摔碎\n", floor + 1);
		StdOut.printf("扔了 %d 次，摔碎 %d 次", ThrowingEggsFromABuilding.throwTimes, ThrowingEggsFromABuilding.brokenEggs);
	}
	// output
	/*
	 *    
		false false false false false false false false false false false 
		false false false false false false false false false false false 
		false false false false false false false false false false false 
		false false false false false false false false false false false
		false false false false false false false false false false false 
		false false false false false false false false false false false 
		false false false false false false false false false false false 
		false false false false false false false true true true true true 
		true true true true true true true true true true true 
		丢鸡蛋从楼层 85 开始会摔碎
		扔了 6 次，摔碎 2 次
	 */
}
