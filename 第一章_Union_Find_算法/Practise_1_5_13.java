package 第一章_Union_Find_算法;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_5_13 {
	static class CompressedWeightedQuickUnion {
		private int[] id;
		private int[] sz;
		CompressedWeightedQuickUnion(int N) {
			id = new int[N];
			sz = new int[N];
			for (int i = 0; i < N; i++) {
				id[i] = i;
				sz[i] = 1;
			}
		}
		int find(int p) {
			int root = p;
			while (root != id[root])
				root = id[root];
			while (id[p] != root) {
				int parent = id[p];
				sz[parent] -= sz[p];
				id[p] = root;
				p = parent;
			}
			return root;
		}
		void union(int p, int q) {
			int pRoot = find(p);
			int qRoot = find(q);
			if (pRoot == qRoot) return;
			if (sz[pRoot] < sz[qRoot]) {
				id[pRoot] = qRoot;
				sz[qRoot] += sz[pRoot];
			} else {
				id[qRoot] = pRoot;
				sz[pRoot] += sz[qRoot];
			}
		}
		int maxTreeDepth() {
			int depth = 0;
			for (int i = 0; i < id.length; i++) {
				int tmp = 0;
				int p = i;
				while (p != id[p]) {
					tmp++;
					p = id[p];
				}
				if (tmp > depth) depth = tmp;
			}
			return depth;
		}
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("--------------------------\n");
			sb.append("索引:\t");
			int i = 0;
			while (i < id.length) sb.append(" " + i++);
			sb.append("\n\t"); i = 0;
			while (i < id.length) sb.append(" " + id[i++]);
			sb.append("\n规模:\t"); i = 0;
			while (i < id.length) sb.append(" " + sz[i++]);
			sb.append("\n\t最大树高 " + maxTreeDepth()); 
			sb.append("\n--------------------------\n");
			return sb.toString();
		}
	}
	public static void main(String[] args) {
		int N = 100000000;
		Stopwatch timer = new Stopwatch();
		Text_Generator gen = new Text_RandomPairGenerator(N);
		CompressedWeightedQuickUnion cqu = new CompressedWeightedQuickUnion(N);
		for (int i = 0; i < N; i++) {
			int[] pair = gen.nextPair();
			cqu.union(pair[0], pair[1]);
		}
		StdOut.printf("执行完毕，耗时为 %.3f 秒\n", timer.elapsedTime());
		StdOut.printf("树高为 : %d\n", cqu.maxTreeDepth());
	}
	// output
	/*
	 * 	执行完毕，耗时为 28.939 秒
		树高为 : 5
	 */
	// output
	/*
	 * 	执行完毕，耗时为 29.665 秒
		树高为 : 5
	 */
	// output
	/*
	 * 执行完毕，耗时为 30.644 秒
	   树高为 : 5
	 */
	// output
	/*
	 * 	执行完毕，耗时为 29.594 秒
		树高为 : 5
	 */
}
