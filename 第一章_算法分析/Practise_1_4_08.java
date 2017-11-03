package 第一章_算法分析;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_4_08 {
	/*
	 * O(N^2)
	 */
	static class EqualPairCount {
		public static long count(int[] a) {
			Stopwatch timer = new Stopwatch();
			long cnt = 0;
			int N = a.length;
			for (int i = 0; i < N; i++)
				for (int j = i + 1; j < N; j++)
					if (a[i] == a[j]) 
						cnt++;
			StdOut.println(String.format("Slow way result : "
					+ "%d\n total time : %f\n================", 
					 cnt, timer.elapsedTime()));
			return cnt;
		}
	}
	/*
	 * O(N * log(N))
	 */
	static class EqualPairCountFast {
		public static long count(int[] a) {
			if (a == null || a.length < 2)
				return 0;
			Stopwatch timer = new Stopwatch();
			int[] arr = a;
			Arrays.sort(arr);
			long cnt = 0;
			int i = 1, pre = 0, cur = 1;
			while (i < arr.length) {
				pre = i - 1; cur = i;
				if (arr[pre] != arr[cur]) i++;
				else {
					while (i < arr.length && arr[cur] == arr[i]) i++;
					int equalCount = i - pre;
					cnt += equalCount  * (equalCount - 1) / 2;
				}
			}
			StdOut.println(String.format("Fast way result : %d \n total time : %f\n================", 
					cnt, timer.elapsedTime()));
			return cnt;
		}
	}
	public static int[] sourceArr(int N) {
		int[] arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = StdRandom.uniform(0, 10);
		return arr;
	}
	public static void testTwoApproaches() {
		int[] arr = sourceArr(200000);
		EqualPairCountFast.count(arr);
		EqualPairCount.count(arr);
	}
	public static void main(String[] args) {
		testTwoApproaches();
	}
	// output 
	/*
	 * 	 Fast way result : 2000009757 
		 total time : 0.017000
		 ================
		 Slow way result : 2000009757
		 total time : 7.493000
		 ================

	 */
}
