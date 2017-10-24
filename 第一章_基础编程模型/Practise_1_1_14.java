package 第一章_基础编程模型;

import edu.princeton.cs.algs4.*;

public class Practise_1_1_14 {
	public static void main(String[] args) {
		/*
		 * 不使用 Math 
		 */
		long N = StdRandom.uniform(1000000);
		long sum = 1;
		int count = 0;
		while(true) {
			sum *= 2;
			if(sum >= N) break;
			count++;
		}
		StdOut.println("N = " + N + " result = " + count);
	}
}
