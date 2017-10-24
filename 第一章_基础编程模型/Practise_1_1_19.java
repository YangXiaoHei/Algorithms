package 第一章_基础编程模型;

import edu.princeton.cs.algs4.*;

public class Practise_1_1_19 {
	/*
	 * 递归实现
	 */
	public static long F(int N) {
		if(N == 0) return 0;
		if(N == 1) return 1;
		return F(N - 1) + F(N - 2);
	}
	/*
	 * 数组改进
	 */
	public static long F_modified(int N) {
		if (N < 0) return 0;
		if (N == 0) return 1;
		if (N == 1) return 1;
		long[] storage = new long[N + 1];
		storage[0] = 1;
		storage[1] = 1;
		for(int i = 2; i < N + 1; i++) {
			storage[i] = storage[i - 2] + storage[i - 1]; 
		}
		return storage[N];
	}
	/*
	 * 节省空间
	 */
	public static long F_fast(int N) {
		if (N == 0) return 1;
		long prev = 0, next = 1;
		while(N-- > 0) {
			long temp = next;
			next += prev;
			prev = temp;
		}
		return next;
	}
	public static void testThreeApproaches() {
		StdOut.println("============ 节省时间和空间 ==============");
		for(int i = 0; i < 50; i++)
			StdOut.println(F_fast(i));
		
		StdOut.println("============= 数组改进 =============");
		for(int i = 0; i < 50; i++)
			StdOut.println(F_modified(i));
		
		StdOut.println("============= 递归实现 =============");
		for(int i = 0; i < 50; i++)
			StdOut.println(F(i));
	}
	public static void main(String[] args) {
		testThreeApproaches();
	}
}
