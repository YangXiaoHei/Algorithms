package 第一章_基础编程模型;

import edu.princeton.cs.algs4.*;
import java.util.*;

public class Practise_1_1_23 {
	public static void practise_23(char c) {
		if (c != '+' && c != '-')
			throw new RuntimeException("not supported argument");
		
		int[] whiteList = new In(/* enter your file full path */).readAllInts();
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
}
