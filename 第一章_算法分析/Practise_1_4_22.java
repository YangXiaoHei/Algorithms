package 第一章_算法分析;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_4_22 {
	
	
	
	public static long fibo(int k) {
		long prev = 1, next = 1;
		while (--k > 0) {
			next = next + prev;
			prev = next - prev;
		}
		return next;
	}
	
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++)
			StdOut.println(fibo(i));
	}
	static class FibonacciSearch {
		
	}
}
