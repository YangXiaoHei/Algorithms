package 第一章_算法分析;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_4_07 {
	static class ThreeSum {
		public static int count(int a[]) {
			int cnt = 0;
			int count = 0;
			int N = a.length;
			/*
			 * following analyzation ignore the every first increment and every last comparasion
			 */
			for (int i = 0; i < N; i++)
				/*
				 * N times comparison, N times increment
				 * total 2 * N
				 */
				
				for (int j = i + 1; j < N; j++)
					/*
					 * C(n, 2) times comparison, C(n, 2) times increment
					 * 
					 * total N * (N - 1)
					 */
					
					for (int k = j + 1; k < N; k++) 
						/*
						 * C(n, 3) times comparison, C(n, 3) times increment
						 * 
						 * total N * (N - 1) * (N - 2) / 3
						 */
						if (a[i] + a[j] + a[k] == 0)
							/*
							 * C(n, 3) * 2 times addition, C(n, 3) times equality method
							 * 
							 * total N * (N - 1) * (N - 2) / 2
							 */
							cnt++;
			StdOut.println(count);
			/*
			 * total : 
			 * 2 * N + N * (N - 1) + N * (N - 1) * (N - 2) / 3 + N * (N - 1) * (N - 2) / 2
			 * 
			 * ~ N^3
			 */
			return cnt;
		}
	}
	public static void main(String[] args) {
		int[] arr = new int[4];
		ThreeSum.count(arr);
	}
}
