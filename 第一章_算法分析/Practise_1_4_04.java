package 第一章_算法分析;

import java.util.Arrays;

public class Practise_1_4_04 {
	static class BinarySearch {
		public static int rank(int k, int[] a) {
			int lo = 0, hi = a.length - 1;
			while (lo <= hi) {
				int mid = (lo + hi) / 2;
				if 		(a[mid] > k) hi = mid - 1;
				else if (a[mid] < k) lo = mid + 1;
				else	return mid;
			}
			return -1;
		}
	}
	static class TwoSum {
		public static int count(int[] a) {
			/*
			 * t0
			 */
			int cnt = 0;
			/*
			 * t1
			 */
			int N = a.length;
			/*
			 *  N * t2
			 */
			for (int i = 0; i < N; i++)
				/*
				 * N + N - 1 + N - 2 + ... + 1 = N(N + 1) / 2
				 * 
				 * t3 * N(N + 1) / 2
				 */
				for (int j = i + 1; j < N; j++)
					if (a[i] + a[j] == 0)
						/*
						 * x * t4
						 */
						cnt++;
			/*
			 * total : t0 + t1 + N * t2 + t3 * (N^2 + N) / 2 + x * t4
			 */
			return cnt;
		}
	}
	static class TwoSumFast {
		public static int count(int[] a) {
			/*
			 * t0
			 */
			int cnt = 0;
			/*
			 * t1
			 */
			int N = a.length;
			/*
			 * N * log(N) * t2
			 */
			Arrays.sort(a);
			/*
			 * N * t3
			 */
			for (int i = 0; i < N; i++)
				/*
				 * N * log(N) * t4
				 */
				if (BinarySearch.rank(-a[i], a) > i)
					/*
					 * x * t5
					 */
					cnt++;
			/*
			 * total : t0 + t1 + N * log(N) * t2 + N * t3 + N * log(N) * t4 + x * t5
			 */
			return cnt;
		}
	}
	public static void main(String[] args) {
		
	}
}
