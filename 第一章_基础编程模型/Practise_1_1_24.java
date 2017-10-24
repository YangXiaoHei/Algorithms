package 第一章_基础编程模型;

import edu.princeton.cs.algs4.*;

public class Practise_1_1_24 {
	/*
	 * 最大公约数递归版
	 */
	public static int Euclid(int p, int q) {
		StdOut.println("p = " + p + " q = " + q);
		if(q == 0) return p;
		return Euclid(q,  p % q);
	}
	public static void main(String[] args) {
		StdOut.println(Euclid(1111111, 1234567));
	}
	// output :
	/*
	 * 	p = 1111111 q = 1234567
		p = 1234567 q = 1111111
		p = 1111111 q = 123456
		p = 123456 q = 7
		p = 7 q = 4
		p = 4 q = 3
		p = 3 q = 1
		p = 1 q = 0
		1

	 */
}
