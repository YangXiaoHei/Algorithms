package 第一章_数据抽象;

import edu.princeton.cs.algs4.*;

public class Practise_1_2_06 {
	public static boolean isCircularRotation(String s, String t) {
		return s.length() == t.length() && (s + s).contains(t);
	}
	
	public static void main(String[] args) {
		StdOut.println(isCircularRotation("12345", "45123"));
	}
}
