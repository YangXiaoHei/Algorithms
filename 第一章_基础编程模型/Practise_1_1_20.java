package 第一章_基础编程模型;

import edu.princeton.cs.algs4.*;

public class Practise_1_1_20 {
	/*
	 * ln(N!)
	 */
	public static double lnN(int N) {
		return Math.log(recursive(N));
	}
	public static double recursive(double N) {
		if (N == 1 || N == 0) return 1;
		return recursive(N - 1) * N;
	}
	public static void main(String[] args) {
		int N = 10;
		for(int i = 0; i < N; i++)
			StdOut.println(lnN(i));
	}
}
