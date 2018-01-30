package Ch_1_5_Case_Study_Union_Find;

import Tool.ArrayGenerator.RandomPair;
import edu.princeton.cs.algs4.StdOut;

public class Practise_1_5_07 {
	static class QuickUnionUF {
		private int[] id;
		private int count;
		QuickUnionUF(int N) {
			id = new int[N];
			for (int i = 0; i < N; i++) 
				id[i] = i;
			count = N;
		}
		boolean connected(int p, int q) { return find(p) == find(q); }
		int find(int p) {
			while (p != id[p])
				p = id[p];
			return p;
		}
		void union(int p, int q) {
			int pRoot = find(p);
			int qRoot = find(q);
			if (pRoot == qRoot) {
				StdOut.printf("%d %d 已连通\n\n", p, q);
				return;
			}
			id[pRoot] = qRoot;
			count--;
			StdOut.println(this);
			
		}
		public String toString() {
			StringBuilder sb = new StringBuilder();
			int i = 0;
			sb.append("-----------------------------\n");
			sb.append("索引:\t");
			while (i < id.length) sb.append(" " + i++);
			sb.append("\n\t"); i = 0;
			while (i < id.length) sb.append(" " + id[i++]);
			sb.append("        连通分量 : " + count);
			sb.append("\n-----------------------------\n");
			return sb.toString();
		}
	}
	
	static class QuickFindUF {
		private int[] id;
		private int count;
		QuickFindUF(int N) {
			id = new int[N];
			for (int i = 0; i < N; i++)
				id[i] = i;
			count = N;
		}
		int find(int p) { return id[p]; }
		boolean connected(int p, int q) { return find(p) == find(q); }
		void union(int p, int q) {
			int pID = id[p];
			int qID = id[q];
			if (pID == qID)  {
				StdOut.printf("%d %d 已连通\n\n", p, q);
				return;
			}
			for (int i = 0; i < id.length; i++)
				if (id[i] == pID) id[i] = qID;
			count--;
			StdOut.println(this);
		}
		public String toString() {
			StringBuilder sb = new StringBuilder();
			int i = 0;
			sb.append("-----------------------------\n");
			sb.append("索引:\t");
			while (i < id.length) sb.append(" " + i++);
			sb.append("\n\t"); i = 0;
			while (i < id.length) sb.append(" " + id[i++]);
			sb.append("        连通分量 : " + count);
			sb.append("\n-----------------------------\n");
			return sb.toString();
		}
	}
	public static void main(String[] args) {
		StdOut.println("quick-union\n");
		QuickUnionUF qu = new QuickUnionUF(10);
		RandomPair gen = new RandomPair(10);
		for (int i = 0; i < 20; i++) {
			int[] pair = gen.nextPair();
 			StdOut.printf("连接 %d %d\n", pair[0], pair[1]);
			qu.union(pair[0], pair[1]);
		}
		
		
		StdOut.println("quick-find\n");
		QuickFindUF qf = new QuickFindUF(10);
		for (int i = 0; i < 20; i++) {
			int[] pair = gen.nextPair();
 			StdOut.printf("连接 %d %d\n", pair[0], pair[1]);
 			qf.union(pair[0], pair[1]);
		}
	}
	// output
	/*
	 * 
	 * quick-union
	 * 	连接 8 0
		-----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 0 1 2 3 4 5 6 7 0 9        连通分量 : 9
		-----------------------------
		
		连接 6 0
		-----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 0 1 2 3 4 5 0 7 0 9        连通分量 : 8
		-----------------------------
		
		连接 3 8
		-----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 0 1 2 0 4 5 0 7 0 9        连通分量 : 7
		-----------------------------
		
		连接 2 3
		-----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 0 1 0 0 4 5 0 7 0 9        连通分量 : 6
		-----------------------------
		
		连接 3 5
		-----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 5 1 0 0 4 5 0 7 0 9        连通分量 : 5
		-----------------------------
		
		连接 1 7
		-----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 5 7 0 0 4 5 0 7 0 9        连通分量 : 4
		-----------------------------
		
		连接 7 6
		-----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 5 7 0 0 4 5 0 5 0 9        连通分量 : 3
		-----------------------------
		
		连接 5 7
		5 7 已连通
		
		连接 5 2
		5 2 已连通
		
		连接 3 0
		3 0 已连通
		
		连接 2 6
		2 6 已连通
		
		连接 1 8
		1 8 已连通
		
		连接 8 4
		-----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 5 7 0 0 4 4 0 5 0 9        连通分量 : 2
		-----------------------------
		
		连接 7 0
		7 0 已连通
		
		连接 9 8
		-----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 5 7 0 0 4 4 0 5 0 4        连通分量 : 1
		-----------------------------
		
		连接 6 4
		6 4 已连通
		
		连接 8 9
		8 9 已连通
		
		连接 4 8
		4 8 已连通
		
		连接 5 1
		5 1 已连通
		
		连接 0 2
		0 2 已连通
		
		
		
		
		
		
		quick-find

		连接 6 4
		-----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 0 1 2 3 4 5 4 7 8 9        连通分量 : 9
		-----------------------------
		
		连接 8 6
		-----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 0 1 2 3 4 5 4 7 4 9        连通分量 : 8
		-----------------------------
		
		连接 1 2
		-----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 0 2 2 3 4 5 4 7 4 9        连通分量 : 7
		-----------------------------
		
		连接 7 1
		-----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 0 2 2 3 4 5 4 2 4 9        连通分量 : 6
		-----------------------------
		
		连接 2 6
		-----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 0 4 4 3 4 5 4 4 4 9        连通分量 : 5
		-----------------------------
		
		连接 7 9
		-----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 0 9 9 3 9 5 9 9 9 9        连通分量 : 4
		-----------------------------
		
		连接 6 2
		6 2 已连通
		
		连接 2 5
		-----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 0 5 5 3 5 5 5 5 5 5        连通分量 : 3
		-----------------------------
		
		连接 1 2
		1 2 已连通
		
		连接 5 6
		5 6 已连通
		
		连接 8 7
		8 7 已连通
		
		连接 4 1
		4 1 已连通
		
		连接 3 0
		-----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 0 5 5 0 5 5 5 5 5 5        连通分量 : 2
		-----------------------------
		
		连接 3 0
		3 0 已连通
		
		连接 0 8
		-----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 5 5 5 5 5 5 5 5 5 5        连通分量 : 1
		-----------------------------
		
		连接 8 5
		8 5 已连通
		
		连接 2 4
		2 4 已连通
		
		连接 8 5
		8 5 已连通
		
		连接 2 0
		2 0 已连通
		
		连接 7 6
		7 6 已连通


	 */
}
