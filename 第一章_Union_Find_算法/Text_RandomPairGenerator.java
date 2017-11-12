package 第一章_Union_Find_算法;

import edu.princeton.cs.algs4.StdRandom;

public class Text_RandomPairGenerator implements Text_Generator {
	private int N;
	public Text_RandomPairGenerator(int N) { this.N = N; }
	public int[] nextPair() {
		int p = StdRandom.uniform(N);
		int q = StdRandom.uniform(N);
		while (q == p)
			q = StdRandom.uniform(N);
		return new int[] { p, q };
	}
}
