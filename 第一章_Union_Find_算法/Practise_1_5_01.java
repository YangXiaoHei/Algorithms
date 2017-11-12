package 第一章_Union_Find_算法;

import edu.princeton.cs.algs4.*;

public class Practise_1_5_01 {
	static class UF {
		private int accessTotalTimes;
		private int[] id = new int[10];
		{
			for (int i = 0; i < id.length; i++)
				id[i] = i;
		}
		int find(int p) { return id[p]; }
		void union(int p, int q) {
			int accessTimes = 0;
			int pID = find(p);
			int qID = find(q);
			accessTimes += 2;
			if (pID != qID) {
				for (int i = 0; i < id.length; i++) {
					accessTimes++;
					if (id[i] == pID) {
						id[i] = qID;
						accessTimes++;
					}
				}
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
	 * 	连接 9 0 访问数组 : 13 次  总共访问数组 : 13 次
		----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 0 1 2 3 4 5 6 7 8 0
		----------------------------
		
		连接 3 4 访问数组 : 13 次  总共访问数组 : 26 次
		----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 0 1 2 4 4 5 6 7 8 0
		----------------------------
		
		连接 5 8 访问数组 : 13 次  总共访问数组 : 39 次
		----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 0 1 2 4 4 8 6 7 8 0
		----------------------------
		
		连接 7 2 访问数组 : 13 次  总共访问数组 : 52 次
		----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 0 1 2 4 4 8 6 2 8 0
		----------------------------
		
		连接 2 1 访问数组 : 14 次  总共访问数组 : 66 次
		----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 0 1 1 4 4 8 6 1 8 0
		----------------------------
		
		连接 5 7 访问数组 : 14 次  总共访问数组 : 80 次
		----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 0 1 1 4 4 1 6 1 1 0
		----------------------------
		
		连接 0 3 访问数组 : 14 次  总共访问数组 : 94 次
		----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 4 1 1 4 4 1 6 1 1 4
		----------------------------
		
		连接 4 2 访问数组 : 16 次  总共访问数组 : 110 次
		----------------------------
		索引:	 0 1 2 3 4 5 6 7 8 9
			 	 1 1 1 1 1 1 6 1 1 1
		----------------------------

	 */
}
