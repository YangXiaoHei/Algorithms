package 第一章_基础编程模型;

import edu.princeton.cs.algs4.*;

public class Practise_1_1_08 {
	public static void main(String[] args) {
		/*
		 * 两个字节的 char
		 */
		StdOut.println('b'); 
		/*
		 * char 被提升为 int
		 */
		StdOut.println('b' + 'c'); 
		/*
		 * char 被提升至 int，然后再截断为 char
		 */
		StdOut.println((char)('a' + 4));  
	}
}
