package 第一章_基础编程模型;

import edu.princeton.cs.algs4.*;

public class Practise_1_1_06 {
	public static void main(String[] args) {
		
		// 打印斐波那契数列前15项
		int f = 0;
		int g = 1;
		for(int i = 0; i <= 15; i++) {
			StdOut.println(f);
			f = f + g;
			g = f - g;
		}
	}
}
