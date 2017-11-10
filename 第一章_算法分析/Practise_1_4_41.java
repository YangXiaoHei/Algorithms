package 第一章_算法分析;

import java.util.Arrays;

import edu.princeton.cs.algs4.*;

public class Practise_1_4_41 {
	static class TwoSum {
		static int count(int[] a) {
			int N = a.length;
			int cnt = 0;
			for (int i = 0; i < N; i++)
				for (int j = i + 1; j < N; j++)
					if (a[i] + a[j] == 0)
						cnt++;
			return cnt;
		}
	}
	static class ThreeSum {
		static int count(int[] a) {
			int N = a.length;
			int cnt = 0;
			for (int i = 0; i < N; i++)
				for (int j = i + 1; j < N; j++)
					for (int k = j + 1; k < N; k++)
						if (a[i] + a[j] + a[k] == 0)
							cnt++;
			return cnt;
		}
	}
	static class BinarySearch {
		static int rank(int key, int[] arr) {
			int lo = 0, hi = arr.length - 1, mid = 0;
			while (lo <= hi) {
				mid = (lo + hi) / 2;
				if 		(arr[mid] < key) lo = mid + 1;
				else if (arr[mid] > key) hi = mid - 1;
				else	return mid;
			}
			return -1;
		}
	}
	static class TwoSumFast {
		static int count(int[] a) {
			Arrays.sort(a);
			int N = a.length;
			int cnt = 0;
			for (int i = 0; i < N; i++)
				if (BinarySearch.rank(-a[i], a) > 0)
					cnt++;
			return cnt;
		}
	}
	static class ThreeSumFast {
		static int count(int[] a) {
			Arrays.sort(a);
			int N = a.length;
			int cnt = 0;
			for (int i = 0; i < N; i++)
				for (int j = i + 1; j < N; j++)
					if (BinarySearch.rank(-(a[i] + a[j]), a) > 0)
						cnt++;
			return cnt;
		}
	}
	
	static int[] sourceArr(int N) {
		int[] arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = StdRandom.uniform(-10000, 10000);
		return arr;
	}
	static int[] sourceArrSorted(int N) {
		int[] arr = sourceArr(N);
		Arrays.sort(arr);
		return arr;
	}
	
	static double timeTrial_Two(int N, int M) {
		int[] arr = sourceArr(N);
		double average = 0;
		for (int i = 0; i < M; i++) {
			Stopwatch timer = new Stopwatch();
			int cnt = TwoSum.count(arr);
			average += timer.elapsedTime();
		}
		return average / M;
	}
	static double timeTrial_TwoFast(int N, int M) {
		int[] arr = sourceArrSorted(N);
		double average = 0;
		for (int i = 0; i < M; i++) {
			Stopwatch timer = new Stopwatch();
			int cnt = TwoSumFast.count(arr);
			average += timer.elapsedTime();
		}
		return average / M;
	}

	static double timeTrial_Three(int N, int M) {
		int[] arr = sourceArr(N);
		double average = 0;
		for (int i = 0; i < M; i++) {
			Stopwatch timer = new Stopwatch();
			int cnt = ThreeSum.count(arr);
			average += timer.elapsedTime();
		}
		return average / M;
	}

	static double timeTrial_ThreeFast(int N, int M) {
		int[] arr = sourceArrSorted(N);
		double average = 0;
		for (int i = 0; i < M; i++) {
			Stopwatch timer = new Stopwatch();
			int cnt = ThreeSumFast.count(arr);
			average += timer.elapsedTime();
		}
		return average / M;
	}
	
	/*
	 * 测试 TwoSum
	 * 
	 * output :
	 * 	TwoSum : 规模 : 100 用时 : 0.000100 倍率 : Infinity
		TwoSum : 规模 : 200 用时 : 0.000100 倍率 : 1.000000
		TwoSum : 规模 : 400 用时 : 0.000200 倍率 : 2.000000
		TwoSum : 规模 : 800 用时 : 0.000550 倍率 : 2.750000
		TwoSum : 规模 : 1600 用时 : 0.000500 倍率 : 0.909091
		TwoSum : 规模 : 3200 用时 : 0.002000 倍率 : 4.000000
		TwoSum : 规模 : 6400 用时 : 0.007350 倍率 : 3.675000
		TwoSum : 规模 : 12800 用时 : 0.028350 倍率 : 3.857143
		TwoSum : 规模 : 25600 用时 : 0.105000 倍率 : 3.703704
		TwoSum : 规模 : 51200 用时 : 0.433600 倍率 : 4.129524
		TwoSum : 规模 : 102400 用时 : 1.705500 倍率 : 3.933349
		TwoSum : 规模 : 204800 用时 : 6.813850 倍率 : 3.995221
	 */
	static void doubleRatioTwo() {
		double prev = 0;
		for (int i = 100, j = 0; j < 12; i += i, j++) {
			double cur = timeTrial_Two(i, 20);
			StdOut.printf("TwoSum : 规模 : %d 用时 : %f 倍率 : %f\n", 
					i, cur, cur / prev);
			prev = cur;
		}
	}
	
	/*
	 * output
	 *	TwoSum : 规模 : 100 用时 : 0.000200 倍率 : Infinity
		TwoSum : 规模 : 200 用时 : 0.000100 倍率 : 0.500000
		TwoSum : 规模 : 400 用时 : 0.000100 倍率 : 1.000000
		TwoSum : 规模 : 800 用时 : 0.000150 倍率 : 1.500000
		TwoSum : 规模 : 1600 用时 : 0.000100 倍率 : 0.666667
		TwoSum : 规模 : 3200 用时 : 0.000350 倍率 : 3.500000
		TwoSum : 规模 : 6400 用时 : 0.000400 倍率 : 1.142857
		TwoSum : 规模 : 12800 用时 : 0.001350 倍率 : 3.375000
		TwoSum : 规模 : 25600 用时 : 0.003700 倍率 : 2.740741
		TwoSum : 规模 : 51200 用时 : 0.004250 倍率 : 1.148649
		TwoSum : 规模 : 102400 用时 : 0.005900 倍率 : 1.388235
		TwoSum : 规模 : 204800 用时 : 0.011300 倍率 : 1.915254
		TwoSum : 规模 : 409600 用时 : 0.020850 倍率 : 1.845133
		TwoSum : 规模 : 819200 用时 : 0.038700 倍率 : 1.856115
		TwoSum : 规模 : 1638400 用时 : 0.074200 倍率 : 1.917313
		TwoSum : 规模 : 3276800 用时 : 0.148450 倍率 : 2.000674
		TwoSum : 规模 : 6553600 用时 : 0.304350 倍率 : 2.050185
		TwoSum : 规模 : 13107200 用时 : 0.614500 倍率 : 2.019057
		TwoSum : 规模 : 26214400 用时 : 1.221250 倍率 : 1.987388
		TwoSum : 规模 : 52428800 用时 : 2.470150 倍率 : 2.022641
		TwoSum : 规模 : 104857600 用时 : 4.807600 倍率 : 1.946279
	 */
	static void doubleRatioTwoFast() {
		double prev = 0;
		for (int i = 100, j = 0; j < 21; i += i, j++) {
			double cur = timeTrial_TwoFast(i, 20);
			StdOut.printf("TwoSum : 规模 : %d 用时 : %f 倍率 : %f\n", 
					i, cur, cur / prev);
			prev = cur;
		}
	}
	
	
	static void doubleRatioThree() {
		double prev = 0;
		for (int i = 100, j = 0; j < 7; i += i, j++) {
			double cur = timeTrial_Three(i, 10);
			StdOut.printf("TwoSum : 规模 : %d 用时 : %f 倍率 : %f\n", 
					i, cur, cur / prev);
			prev = cur;
		}
	}
	
	
	public static void main(String[] args) {
		doubleRatioThree();
		
	}
}
