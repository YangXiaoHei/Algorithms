package 第一章_基础编程模型;

import edu.princeton.cs.algs4.*;

public class Practise_1_1_07 {
	/*
	 * 1.1.7  平方根 （牛顿迭代法）
	 */
	public static double practise_1_1_07_a(double c) {
		double t = c;
		while(Math.abs(t - c / t) > .0001) 
			t = (c / t + t) / 2.0;
		return t;
	}
	public static void practise_1_1_07_a_draw() {
		StdDraw.setXscale(0, 100);
		StdDraw.setYscale(0, 15);
		StdDraw.setPenRadius(.001);
		for(double t = 1; t <= 100; t += 0.01) {
			double result = practise_1_1_07_a(t);
			StdDraw.point(t, result);
		}
	}
	/*
	 * 1.1.7 b 
	 * 0 + 1 + 2 + 3 + 4 + ... + 1000 等差数列求和
	 */
	public static void practise_1_1_07_b() {
		int sum = 0;
		for(int i = 1; i < 1000; i++) 
			for(int j = 0; j < i; j++) 
				sum++;
		StdOut.println(sum);
	}
	/*
	 * 1.1.7 c 
	 * (log2(1000)向下取整 + 1) * 1000  
	 */
	public static void practise_1_1_07_c() {
		int sum = 0;
		for(int i = 1; i < 1000; i *= 2) 
			for(int j = 0; j < 1000; j++)
				sum++;
		StdOut.println(sum);
	}
	public static void main(String[] args) {
//		practise_1_1_07_a_draw();
		practise_1_1_07_b();
		practise_1_1_07_c();
		
	}
}
