package 第一章_基础编程模型;

import edu.princeton.cs.algs4.*;

public class Practise_1_1_09 {
	public static void main(String[] args) {
		int N = StdRandom.uniform(10000);
		String s = "";
		for(int n = N; n > 0; n /= 2) 
			s = (n % 2) + s;
		System.out.println(s);
	}
}
