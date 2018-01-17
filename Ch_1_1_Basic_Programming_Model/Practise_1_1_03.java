package Ch_1_1_Basic_Programming_Model;

import edu.princeton.cs.algs4.*;

public class Practise_1_1_03 {
	public static void main(String[] args) {
		if (args.length < 3) 
			throw new RuntimeException("arguments amount less than required!");
		int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[1]);
		int c = Integer.parseInt(args[2]);
		if(a == b && b == c) 
			StdOut.println("equal");
		else
			StdOut.println("not equal");
	}
	// output : configure the envionment variables and execute to see output
	/*
	 * equal
	 */
}
