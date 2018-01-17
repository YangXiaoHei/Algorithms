package Ch_1_1_Basic_Programming_Model;

import edu.princeton.cs.algs4.*;

public class Practise_1_1_26 {
	public static void main(String[] args) {
		/*
		 *  a 和 b, c 比较后，最大的元素已经被冒泡到末位，
		 *  再比较 b, c 可得到最后按升序排列的序列
		 */
		int a = StdRandom.uniform(100),
			b = StdRandom.uniform(100),
			c = StdRandom.uniform(100);
		StdOut.println("a = " + a + " b = " + b + " c = " + c);
		if (a > b) { int t = a; a = b; b = t; }
		if (a > c) { int t = a; a = c; c = t; }
		if (b > c) { int t = b; b = c; c = t; }
		StdOut.println("after compare and swap");
		StdOut.println("a = " + a + " b = " + b + " c = " + c);
	}
	// output :
	/*
	 * 	a = 39 b = 67 c = 45
		after compare and swap
		a = 39 b = 45 c = 67

	 */
}
