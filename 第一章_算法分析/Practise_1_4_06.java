package 第一章_算法分析;

import edu.princeton.cs.algs4.*;

public class Practise_1_4_06 {
	/*
	 * a
	 */
	public static void a(int N) {
		int sum = 0;
		/*
		 *  N + N/2 + N/4 + N/8 + ... ~2N
		 */
		for (int n = N; n > 0; n /= 2)
			for (int i = 0; i < n; i++)
				sum++;
	}
	/*
	 * b
	 */
	public static void b(int N) {
		int sum = 0;
		/*
		 * 1 + 2 + 4 + 8 + ... N ~ 2N
		 */
		for (int i = 1; i < N; i *= 2)
			for (int j = 0; j < i; j++)
				sum++;
	}
	/*
	 * c
	 */
	public static void c(int N) {
		int sum = 0;
		/*
		 * 1 * N + 2 * N + 4 * N + ... N * N
		 * = N *(1 + 2 + 4 + ...N) ~ N * log(N)
		 */
		for (int i = 1; i < N; i *= 2)
			for (int j = 0; j < N; j++)
				sum++;
	}
}
