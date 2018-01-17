package Ch_1_1_Basic_Programming_Model;

import edu.princeton.cs.algs4.*;

public class Practise_1_1_20 {
	/*
	 * 方法一
	 */
	public static double lnN(int N) {
		return Math.log(recursive(N));
	}
	public static double recursive(double N) {
		if (N == 1 || N == 0) return 1;
		return recursive(N - 1) * N;
	}
	/*
	 * 方法二
	 */
	public static double lnN2(int N) {
		if (N == 1 || N == 0) return 0;
		return lnN2(N - 1) + Math.log(N);
	}
	public static void main(String[] args) {
		int N = 10;
		for(int i = 0; i < N; i++) 
			StdOut.println(lnN(i));
		
		StdOut.println();
		for (int i = 0; i < N; i++)
			StdOut.println(lnN2(i));
	}
	// output :
	/*
	 * 	0.0
		0.0
		0.6931471805599453
		1.791759469228055
		3.1780538303479458
		4.787491742782046
		6.579251212010101
		8.525161361065415
		10.60460290274525
		12.801827480081469
		
		0.0
		0.0
		0.6931471805599453
		1.791759469228055
		3.1780538303479453
		4.787491742782046
		6.579251212010101
		8.525161361065415
		10.60460290274525
		12.80182748008147

	 */
}
