package 第一章_算法分析;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Practise_1_4_08 {
	static class EqualPairCount {
		public static int count(int[] a) {
			int cnt = 0;
			int N = a.length;
			for (int i = 0; i < N; i++)
				for (int j = i + 1; j < N; j++)
					if (a[i] == a[j]) {
						cnt++;
//						StdOut.println(String.format("i = %d j = %d", i, j));
					}
			return cnt;
		}
	}
	static class EqualPairCountFast {
		public static int count(int[] a) {
			// cut meaningless situation
			if (a == null || a.length < 2)
				return 0;
			// avoid to destorying the original array
			int[] arr = new int[a.length];
			System.arraycopy(a, 0, arr, 0 , a.length);
			Arrays.sort(arr);
			int cnt = 0, i = 1;
			while (i < arr.length) {
				if (arr[i - 1] != arr[i]) i++;
				else { cnt++; i += 2; }
			}
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
		int[] arr = sourceArr(10);
		StdOut.println(Arrays.toString(arr));
		StdOut.println(EqualPairCount.count(arr));
		StdOut.println(EqualPairCountFast.count(arr));
	}
	public static void main(String[] args) {
		testTwoApproaches();
	}
}
