package 第一章_算法分析;

import edu.princeton.cs.algs4.*;

public class Practise_1_4_24 {
	/*
	 * 查找丢鸡蛋会摔碎的起始楼层
	 */
	static class ThrowingEggsFromABuilding {
		private static int brokenEggs; // 记录摔碎的鸡蛋数
		private static int throwTimes; // 记录丢鸡蛋次数
		public static int searchStorey_lgN(boolean[] building) {
			brokenEggs = 0;
			throwTimes = 0;
			int N = building.length;
			// 如果在顶层丢都不会碎，那就不用找了
			if (!building[N - 1]) return -1;
			int lo = 0, hi = N - 1, mid = 0;
			// lgN
			while (lo < hi) {
				mid = (lo + hi) / 2;
				throwTimes++;
				
				// 如果在 mid 层丢碎了，那么把查找上届缩小至 mid
				if ( building[mid]) { hi = mid; brokenEggs++; }
				
				// 如果在 mid 层没有碎，那么把查找上届缩小至 mid + 1
				if (!building[mid]) lo = mid + 1;
			}
			// 如果是因为 lo 越过了 hi 而跳出循环，说明没找到
			if (lo > hi) return -1;
			
			// 否则，lo 和 hi 相遇，此时便是起始楼层
			return hi;
		}

		public static int searchStorey_lgF(boolean[] building) {
			brokenEggs = 0;
			throwTimes = 0;
			int N = building.length;
			int k = 1;
			
			// 先用序列 2^k 逐层试探
			while (k < N && !building[k]) { throwTimes++; k *= 2; }
			
			//如果不是因为数组越界而跳出循环，说明摔碎了鸡蛋
			if (k < N) brokenEggs++;
			
			// 选取一个合适的查找上届
			k /= 2;
			int lo = k, hi = k * 2 >= N ? N - 1 : k * 2, mid = 0;
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
		boolean[] arr = sourceArr(1000000);
		int floor_lgN = ThrowingEggsFromABuilding.searchStorey_lgN(arr);
		StdOut.printf("方法一 : 丢鸡蛋从楼层 %d 开始会摔碎\n", floor_lgN + 1);
		StdOut.printf("方法一 : 扔了 %d 次，摔碎 %d 次\n", ThrowingEggsFromABuilding.throwTimes, ThrowingEggsFromABuilding.brokenEggs);
		
		int floor_lgF = ThrowingEggsFromABuilding.searchStorey_lgF(arr);
		StdOut.printf("方法二 : 丢鸡蛋从楼层 %d 开始会摔碎\n", floor_lgF + 1);
		StdOut.printf("方法二 : 扔了 %d 次，摔碎 %d 次\n", ThrowingEggsFromABuilding.throwTimes, ThrowingEggsFromABuilding.brokenEggs);
	}
	// output
	/*
	 *    
		方法一 : 丢鸡蛋从楼层 783011 开始会摔碎
		方法一 : 扔了 20 次，摔碎 11 次
		方法二 : 丢鸡蛋从楼层 783011 开始会摔碎
		方法二 : 扔了 39 次，摔碎 9 次
	 */
}
