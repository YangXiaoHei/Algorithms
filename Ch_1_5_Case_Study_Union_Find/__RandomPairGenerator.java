package Ch_1_5_Case_Study_Union_Find;

import edu.princeton.cs.algs4.StdRandom;

public class __RandomPairGenerator implements __Generator {
	private int N;
	public __RandomPairGenerator(int N) { this.N = N; }
	public int[] nextPair() {
		int p = StdRandom.uniform(N);
		int q = StdRandom.uniform(N);
		while (q == p)
			q = StdRandom.uniform(N);
		return new int[] { p, q };
	}
}
