package Ch_1_4_Analysis_Of_Algorithms;

import java.util.*;

import edu.princeton.cs.algs4.*;

public class Practise_1_4_45 {
	static int totalAfterCollectedAll(int N) {
		Set<Integer> set = new HashSet<Integer>();
		int count = 0;
		while (!containAll(set, N)) {
			set.add(StdRandom.uniform(N));
			count++;
		}
		return count;
	}
	static boolean containAll(Set<Integer> set, int N) {
		for (int i = 0; i < N; i++)
			if (!set.contains(i)) return false;
		return true;
	}
	public static void main(String[] args) {
		int loops = 100;
		
		for (int N = 1000; N < 10000; N += 1000) {
			double average = 0;
			for (int i = 0; i < loops; i++)
				average += totalAfterCollectedAll(N);
			average /= loops;
			StdOut.printf("实验值 : %f  N = %d, Hn = %f\n", average, N, average / N);
		}
		
	}
	// output
	/*
	 * 	实验值 : 7369.700000  N = 1000, Hn = 7.369700
		实验值 : 16877.040000  N = 2000, Hn = 8.438520
		实验值 : 25673.370000  N = 3000, Hn = 8.557790
		实验值 : 36134.300000  N = 4000, Hn = 9.033575
		实验值 : 46122.880000  N = 5000, Hn = 9.224576
		实验值 : 55376.150000  N = 6000, Hn = 9.229358
		实验值 : 64615.000000  N = 7000, Hn = 9.230714
		实验值 : 76428.180000  N = 8000, Hn = 9.553523
		实验值 : 88905.980000  N = 9000, Hn = 9.878442
	 * 
	 */
}
