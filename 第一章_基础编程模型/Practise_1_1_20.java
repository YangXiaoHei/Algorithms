package 第一章_基础编程模型;

import edu.princeton.cs.algs4.*;

public class Practise_1_1_20 {
	public static double recursiveln(double N) {
		if(N <= 0) return Double.MAX_VALUE;
		if(N == 1) return 0;
		return Math.log(N) + Math.log(N - 1);
	}
	public static void main(String[] args) {
		int N = 10;
		for(int i = 0; i < N; i++)
			StdOut.println(recursiveln(i));
	}
}
