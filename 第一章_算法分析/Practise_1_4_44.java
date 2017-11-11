package 第一章_算法分析;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_4_44 {
	static int totalBeforeDuplicationAppear(int N) {
		Set<Integer> set = new HashSet<Integer>();
		int i;
		while (true) {
			i = StdRandom.uniform(N);
			if (set.contains(i)) 
				return set.size();
			else 
				set.add(i);
		}
	}
	public static void main(String[] args) {
		int N = 10000, loop = 1000;
		int average = 0;
		for (int i = 0; i < loop; i++) 
			average += totalBeforeDuplicationAppear(N);
		StdOut.printf("理论值 : %f  实验值 : %f\n",
			Math.sqrt(Math.PI * N / 2), average * 1.0 / loop);
	}
	// output
	/*
	 * 理论值 : 125.331414  实验值 : 125.462000
	 */
}
