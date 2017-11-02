package 第一章_算法分析;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_4_02 {
	static class ThreeSum {
		public static int count(int[] a) {
			int N = a.length;
			int cnt = 0;
			for (int i = 0; i < N; i++)
				for (int j = i + 1; j < N; j++)
					for (int k = j + 1; k < N; k++)
						if ((long)(a[i] + a[j] + a[k]) == 0)
							cnt++;
			return cnt;
		}
	}
	public static void main(String[] args) {
		int[] arr = new int[] { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, 1};
		StdOut.println(ThreeSum.count(arr));
	}
}
