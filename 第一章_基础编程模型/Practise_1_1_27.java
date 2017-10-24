package 第一章_基础编程模型;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_1_27 {
	private static int recursive_count = 0;
	/*
	 * 递归调用次数
	 */
	public static void recursiveTest() {
		double p = 0.8;
		for(int N = 1; N < 10; N++)
			for(int k = 0; k <= N; k++) {
				binomial(N, k, p);
				StdOut.println("N = " + N + "  k = " + k);
				StdOut.println("递归调用次数: " + recursive_count);
				StdOut.println("上限次数 : " + (long)(Math.pow(2, N + 2) - 1));
				StdOut.println("========================\n");
				recursive_count = 0;
			}
	}
	
	/*
	 * 递归实现
	 */
	public static double binomial(int N, int k, double p) {
		recursive_count++;
		if (N == 0 && k == 0)  return 1.0;
		if (N < 0 || k < 0)  return 0.0;
		double rel = (1.0 - p) * binomial(N - 1, k, p)  + p * binomial(N - 1, k - 1, p);
		return rel;
	}
	/*
	 * 数组实现
	 * 
	 * @link https://yangxiaohei.github.io/2017/10/21/Algorithms4-习题-1-1-27-详解/
	 */
	public static double binomial_modified(int N, int k, double p) {
		if(N < 0 || k < 0) return 0.0;
		
		// 分配数组容量
		double[][] container = new double[N + 1][];
		for(int i = 0; i < N + 1; i++)
			container[i] = new double[k + 1];
		
		// (0, 0)
		container[0][0] = 1.0;
		
		// (N, 0)
		for(int i = 1; i < N + 1; i++)
			container[i][0] = (1.0 - p) * container[i - 1][0];
		// (0, k)
		for(int i = 1; i < k + 1; i++)
			container[0][i] = 0.0;
		// (N, k)
		for(int i = 1; i < N + 1; i++)
			for(int j = 1; j < k + 1; j++)
				container[i][j] = (1.0 - p) * container[i - 1][j] + p * container[i - 1][j - 1];
		return container[N][k];
	}
	public static void practise_1_1_27() {
		double p = 0.8;
		int N = 100, k = 25;
		StdOut.println(binomial_modified(N, k, p));
		StdOut.println(binomial(N, k, p));
	}
	public static void main(String[] args) {
		practise_1_1_27();
	}
	// output :
	/*
	 * 3.461350681450802E-32
	 */
}
