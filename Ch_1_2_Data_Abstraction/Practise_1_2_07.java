package Ch_1_2_Data_Abstraction;

import edu.princeton.cs.algs4.*;

public class Practise_1_2_07 {
	/*
	 * 得到原字符串的逆序
	 */
	public static String mystery(String s) {
		int N = s.length();
		if (N <= 1) return s;
		String a = s.substring(0, N / 2);
		String b = s.substring(N / 2, N);
		return mystery(b) + mystery(a);
	}
	public static void main(String[] args) {
		StdOut.println(mystery("123456"));
	}
	// output :
	/*
	 * 654321
	 */
}
