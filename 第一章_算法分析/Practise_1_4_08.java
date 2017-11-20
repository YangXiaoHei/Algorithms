package 第一章_算法分析;

import java.util.*;
import edu.princeton.cs.algs4.*;
/*
 * 思路 :
 * 
 * 暴力解法很简单，查找 C(n, 2) 个组合，并对每对进行比较
 * 改善后的线性对数级别，首先我们对数组进行排序，根据 JDK 源码的注释，排序算法的时耗为 O(N * log(N))
 * 然后我们进行一轮遍历，遍历时做下列事情
 * 
 * 判断 arr[pre] 和 arr[cur] 是否相等，如果相等，就把 cur++ 一直到 arr[pre] != arr[cur]
 * 此时相等的整数对数量为 C(cur - prev, 2)
 * 如果不相等，就把 pre 和 cur 同时 ++, 因此进行一轮遍历的时耗为 O(N)
 * 总时耗为 O(N * log(N) + O(N) ~ O(N * log(N))
 */
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
		    StdRandom.uniform();
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
