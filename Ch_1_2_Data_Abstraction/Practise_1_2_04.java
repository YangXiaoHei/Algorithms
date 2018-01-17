package Ch_1_2_Data_Abstraction;

import edu.princeton.cs.algs4.*;

public class Practise_1_2_04 {
	public static void main(String[] args) {
		String string1 = "hello";
		String string2 = string1;
		string1 = "world";
		StdOut.println(string1);
		StdOut.println(string2);
	}
	// output : 
	/*
	 * 	world
		hello
	 */
}
