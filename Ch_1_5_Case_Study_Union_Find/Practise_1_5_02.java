package Ch_1_5_Case_Study_Union_Find;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_5_02 {
	static class UF {
		private int[] id = new int[10];
		{
			for (int i = 0; i < id.length; i++)
				id[i] = i;
		}
		private int accessTimes;
		private int accessTotalTimes;
		int find(int p) {
			while (true) {
				accessTimes++;
				if (p != id[p]) {
					accessTimes++;
					p = id[p];
				} else 
					break;
			}
			return p;
		}
		void union(int p, int q) {
			accessTimes = 0;
			int pRoot = find(p);
			int qRoot = find(q);
			if (pRoot != qRoot) {
				id[pRoot] = qRoot;
				accessTimes++;
				accessTotalTimes += accessTimes;
				StdOut.printf("连接 %d %d 访问数组 : %d 次  总共访问数组 : %d 次\n", 
						p, q, accessTimes, accessTotalTimes);
				StdOut.println(this);
			} else {
				accessTotalTimes += accessTimes;
				StdOut.printf("%d %d 已连通 访问数组 : %d 次  总共访问数组 : %d 次\n", 
						p, q, accessTimes, accessTotalTimes);
				StdOut.println(this);
			}
		}
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("-----------------------------\n");
			sb.append("索引:\t");
			int i = 0;
			while (i < id.length) sb.append(" " + i++);
			sb.append("\n\t"); i = 0;
			while (i < id.length) sb.append(" " + id[i++]);
			sb.append("\n---------------------------\n");
			return sb.toString();
		}
	}
	public static void main(String[] args) {
		UF uf = new UF();
		uf.union(9, 0);
		uf.union(3, 4);
		uf.union(5, 8);
		uf.union(7, 2);
		uf.union(2, 1);
		uf.union(5, 7);
		uf.union(0, 3);
		uf.union(4, 2);
	}
	// output
	/*
	 * 	连接 9 0 访问数组 : 3 次  总共访问数组 : 3 次
		----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 0 1 2 3 4 5 6 7 8 0
		----------------------------
		
		连接 3 4 访问数组 : 3 次  总共访问数组 : 6 次
		----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 0 1 2 4 4 5 6 7 8 0
		----------------------------
		
		连接 5 8 访问数组 : 3 次  总共访问数组 : 9 次
		----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 0 1 2 4 4 8 6 7 8 0
		----------------------------
		
		连接 7 2 访问数组 : 3 次  总共访问数组 : 12 次
		----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 0 1 2 4 4 8 6 2 8 0
		----------------------------
		
		连接 2 1 访问数组 : 3 次  总共访问数组 : 15 次
		----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 0 1 1 4 4 8 6 2 8 0
		----------------------------
		
		连接 5 7 访问数组 : 9 次  总共访问数组 : 24 次
		----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 0 1 1 4 4 8 6 2 1 0
		----------------------------
		
		连接 0 3 访问数组 : 5 次  总共访问数组 : 29 次
		----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 4 1 1 4 4 8 6 2 1 0
		----------------------------
		
		连接 4 2 访问数组 : 5 次  总共访问数组 : 34 次
		----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 4 1 1 4 1 8 6 2 1 0
		----------------------------

	 */
}
