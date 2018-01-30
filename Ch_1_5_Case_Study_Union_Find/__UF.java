package Ch_1_5_Case_Study_Union_Find;

import Tool.ArrayGenerator.RandomPair;
import edu.princeton.cs.algs4.StdOut;

public abstract class __UF {
	 public __UF(int N) {
		 id = new int[N];
			count = N;
		for (int i = 0; i < N; i++)
			id[i] = i;
	 }
	 protected int id[];
	 protected int count;
	 abstract boolean connected(int p, int q);
	 abstract int find(int p);
	 abstract void union(int p, int q);
	 public int maxTreeDepth() { return -1; }
	 public String toString() {
		 StringBuilder sb = new StringBuilder();
			sb.append("--------------------------------\n");
			sb.append("索引 :\t");
			for (int i = 0; i < id.length; i++)
				sb.append(i + " ");
			sb.append("\n\t");
			for (int i : id)
				sb.append(i + " ");
			sb.append(String.format("      连通分量 : %d\n", count));
			sb.append("--------------------------------\n");
			return sb.toString();
	 }
	 public static void test(__UF uf, int N, int pairCount) {
	     RandomPair gen = new RandomPair(N);
			StdOut.println(uf);
			for (int i = 0; i < pairCount; i++) {
				int[] pair = gen.nextPair();
				if (uf.connected(pair[0], pair[1])) 
					StdOut.printf("%d  %d 已连通\n", pair[0], pair[1]);
				else {
					StdOut.printf("连接 %d %d\n", pair[0], pair[1]);
					uf.union(pair[0], pair[1]);
					StdOut.println(uf);
				}
			}
			if (uf.maxTreeDepth() <= 0) return;
			StdOut.printf("最大树高度 : %d\n", uf.maxTreeDepth());
	 }
}