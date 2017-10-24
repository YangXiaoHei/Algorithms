package 第一章_基础编程模型;

import edu.princeton.cs.algs4.*;

public class Practise_1_1_18 {
	public static int mystery(int a, int b) {
		/*
		 * 返回 a * b
		 */
		if(b == 0) return 0;
		if(b % 2 == 0) return mystery(a + a, b / 2);
		return mystery(a + a, b / 2) + a;
	}
	public static int mystery_2(int a, int b) {
		/*
		 * 返回 pow(a,b)
		 */
		if(b == 0) return 1;
		if(b % 2 == 0) return mystery_2(a * a, b / 2);
		return mystery_2(a * a, b / 2) * a;
	}
	public static void main(String[] args) {
		StdOut.println(mystery(2, 25) + "  " + mystery(3, 11));
		StdOut.println(mystery_2(2, 25) + "  " + mystery_2(3, 11));
	}
	// output :
	/*
	 * 	50  33
		33554432  177147

	 */
}
