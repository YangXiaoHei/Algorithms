package Ch_1_1_Basic_Programming_Model;

import edu.princeton.cs.algs4.*;
import java.util.*;

public class Practise_1_1_23 {
	public static void practise_23(char c) {
		if (c != '+' && c != '-')
			throw new RuntimeException("not supported argument");
		
		int[] whiteList = new In("/Users/bot/Desktop/algs4-data/largeW.txt").readAllInts();
		Arrays.sort(whiteList);
		while(!StdIn.isEmpty()) {
			int number = StdIn.readInt();
			if(Practise_1_1_22.binarySearch(number, whiteList) < 0) {
				if(c == '+')
					StdOut.println(number + " 不在白名单哪");
			} else {
				if(c == '-')
					StdOut.println(number + " 在白名单内");
			}
		}
	}
	public static void main(String[] args) {
		practise_23('+');
	}
	// output : 
	/*
	 * 	4
		4 不在白名单哪
		5
		5 不在白名单哪
		6
		7
		8
		8 不在白名单哪
		10
		11
		11 不在白名单哪
		12
		23
		34
		34
		45
		45 不在白名单哪
	 */
}
