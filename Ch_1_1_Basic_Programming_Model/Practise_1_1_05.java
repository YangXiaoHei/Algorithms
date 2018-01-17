package Ch_1_1_Basic_Programming_Model;

import edu.princeton.cs.algs4.*;

public class Practise_1_1_05 {
	public static void main(String[] args) {
		double a = StdRandom.uniform(1.0, 1000.0),
			   b = StdRandom.uniform(1.0, 1000.0);
		if((a > 0 && a < 1) && (b > 0 && b < 1))
			StdOut.println("true");
		else
			StdOut.println("false");
	}
	// output :
	/*
	 * false

	 */
}
