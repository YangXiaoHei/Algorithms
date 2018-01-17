package Ch_1_4_Analysis_Of_Algorithms;

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
	 *	TwoSumFast : 规模 : 100 用时 : 0.000200 倍率 : Infinity
		TwoSumFast : 规模 : 200 用时 : 0.000100 倍率 : 0.500000
		TwoSumFast : 规模 : 400 用时 : 0.000100 倍率 : 1.000000
		TwoSumFast : 规模 : 800 用时 : 0.000150 倍率 : 1.500000
		TwoSumFast : 规模 : 1600 用时 : 0.000100 倍率 : 0.666667
		TwoSumFast : 规模 : 3200 用时 : 0.000350 倍率 : 3.500000
		TwoSumFast : 规模 : 6400 用时 : 0.000400 倍率 : 1.142857
		TwoSumFast : 规模 : 12800 用时 : 0.001350 倍率 : 3.375000
		TwoSumFast : 规模 : 25600 用时 : 0.003700 倍率 : 2.740741
		TwoSumFast : 规模 : 51200 用时 : 0.004250 倍率 : 1.148649
		TwoSumFast : 规模 : 102400 用时 : 0.005900 倍率 : 1.388235
		TwoSumFast : 规模 : 204800 用时 : 0.011300 倍率 : 1.915254
		TwoSumFast : 规模 : 409600 用时 : 0.020850 倍率 : 1.845133
		TwoSumFast : 规模 : 819200 用时 : 0.038700 倍率 : 1.856115
		TwoSumFast : 规模 : 1638400 用时 : 0.074200 倍率 : 1.917313
		TwoSumFast : 规模 : 3276800 用时 : 0.148450 倍率 : 2.000674
		TwoSumFast : 规模 : 6553600 用时 : 0.304350 倍率 : 2.050185
		TwoSumFast : 规模 : 13107200 用时 : 0.614500 倍率 : 2.019057
		TwoSumFast : 规模 : 26214400 用时 : 1.221250 倍率 : 1.987388
		TwoSumFast : 规模 : 52428800 用时 : 2.470150 倍率 : 2.022641
		TwoSumFast : 规模 : 104857600 用时 : 4.807600 倍率 : 1.946279
	 */
	static void doubleRatioTwoFast() {
		double prev = 0;
		for (int i = 100, j = 0; j < 21; i += i, j++) {
			double cur = timeTrial_TwoFast(i, 20);
			StdOut.printf("TwoSumFast : 规模 : %d 用时 : %f 倍率 : %f\n", 
					i, cur, cur / prev);
			prev = cur;
		}
	}
	
	/*
	 * output
	 * 	ThreeSum : 规模 : 100 用时 : 0.001300 倍率 : Infinity
		ThreeSum : 规模 : 200 用时 : 0.000500 倍率 : 0.384615
		ThreeSum : 规模 : 400 用时 : 0.004300 倍率 : 8.600000
		ThreeSum : 规模 : 800 用时 : 0.034800 倍率 : 8.093023
		ThreeSum : 规模 : 1600 用时 : 0.230100 倍率 : 6.612069
		ThreeSum : 规模 : 3200 用时 : 1.792300 倍率 : 7.789222
		ThreeSum : 规模 : 6400 用时 : 14.256600 倍率 : 7.954360
	 */
	static void doubleRatioThree() {
		double prev = 0;
		for (int i = 100, j = 0; j < 7; i += i, j++) {
			double cur = timeTrial_Three(i, 10);
			StdOut.printf("ThreeSum : 规模 : %d 用时 : %f 倍率 : %f\n", 
					i, cur, cur / prev);
			prev = cur;
		}
	}
	
	/*
	 * output
	 * 
	 * 	ThreeSumFast : 规模 : 100 用时 : 0.000350 倍率 : Infinity
		ThreeSumFast : 规模 : 200 用时 : 0.000750 倍率 : 2.142857
		ThreeSumFast : 规模 : 400 用时 : 0.003300 倍率 : 4.400000
		ThreeSumFast : 规模 : 800 用时 : 0.013350 倍率 : 4.045455
		ThreeSumFast : 规模 : 1600 用时 : 0.058250 倍率 : 4.363296
		ThreeSumFast : 规模 : 3200 用时 : 0.247950 倍率 : 4.256652
		ThreeSumFast : 规模 : 6400 用时 : 1.051350 倍率 : 4.240169
		ThreeSumFast : 规模 : 12800 用时 : 4.363550 倍率 : 4.150426
		ThreeSumFast : 规模 : 25600 用时 : 17.622100 倍率 : 4.038478
	 */
	static void doubleRatioThreeFast() {
		double prev = 0;
		for (int i = 100, j = 0; j < 9; i += i, j++) {
			double cur = timeTrial_ThreeFast(i, 20);
			StdOut.printf("ThreeSumFast : 规模 : %d 用时 : %f 倍率 : %f\n", 
					i, cur, cur / prev);
			prev = cur;
		}
	}

	public static void main(String[] args) {
		/*
		 * 已知规模为 N0 时，用时为 T0, 求规模为 N 时用时是多少 ?
		 * 
		 * N0 -> T0
		 * T(2 * N0) -> 2^b * T0
		 * T(2^2 * N0) -> 2^2b * T0
		 * T(2^3 * N0) -> 2^3b * T0
		 * T(2^r * N0) -> 2^rb * T0
		 * T(r) -> 2^(lg(N/N0) * b) * T0
		 * T(r) -> (N/N0)^b * T0
		 * 
		 * 对于 TwoSum b = 2
		 * N0 = 204800 时 T0 = 6.813850s
		 * 所以当 N = 1000000 时 T = 4.8828125^2 * 6.813850 = 162.454844秒 ~ 2.7分钟
		 * 
		 * 对于 TwoSumFast b = 1
		 * N0 = 102400 时 T0 = 0.005900
		 * 所以当 N = 1000000 时 T = 9.765625^1 * 0.005900 = 0.05761719秒
		 * 
		 * 对于 ThreeSum b = 3
		 * N0 = 6400 时 T0 = 14.256600秒
		 * 所以当 N = 1000000 时 T = 156.25^3 * 14.256600 = 54384613秒 ~ 629.45154天 ~ 2年
		 * 
		 * 对于 ThreeSumFast b = 2
		 * N0 = 25600 时 T0 = 17.622100
		 * 所以当 N = 1000000 时 T = 39.0625^2 * 17.622100 = 26889.1907秒 ~ 7.47 小时
		 */
	}
}
