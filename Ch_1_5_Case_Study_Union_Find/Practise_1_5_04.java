package Ch_1_5_Case_Study_Union_Find;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_5_04 {
	static class UF {
		private int[] id;
		private int count;
		private int[] sz;
		private int accessTimes;
		private int accessTotalTimes;
		UF(int N) {
			id = new int[N];
			sz = new int[N];
			for (int i = 0; i < N; i++) {
				id[i] = i;
				sz[i] = 1;
			}
			count = N;
		}
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
				accessTimes += 2;
				if (sz[pRoot] < sz[qRoot]) {
					id[pRoot] = qRoot;
					sz[qRoot] += sz[pRoot];
				} else {
					id[qRoot] = pRoot;
					sz[pRoot] += sz[qRoot];
				}
				accessTimes += 4;
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
			int i = 0;
			StringBuilder sb = new StringBuilder();
			sb.append("--------------------------\n");
			sb.append("[id]:\t");
			while (i < id.length) sb.append(" " + i++);
			sb.append("\n\t"); i = 0;
			while (i < id.length) sb.append(" " + id[i++]);
			sb.append("\n[sz]:\t"); i = 0;
			while (i < sz.length) sb.append(" " + sz[i++]);
			sb.append("\n--------------------------\n");
			return sb.toString();
		}
	}
	/*
	 * 对照实验
	 */
	public static void controlExperiment() {
		StdOut.println("========  对照实验 ========");
		UF uf = new UF(10);
		uf.union(4, 3);
		uf.union(3, 8);
		uf.union(6, 5);
		uf.union(9, 4);
		uf.union(2, 1);
		uf.union(8, 9);
		uf.union(5, 0);
		uf.union(7, 2);
		uf.union(6, 1);
		uf.union(1, 0);
		uf.union(6, 7);
		StdOut.println("==========================");
	}
	public static void theWorstConditionExperiment() {
		StdOut.println("========  最坏情况 ========");
		UF uf = new UF(10);
		uf.union(0, 1);
		uf.union(2, 3);
		uf.union(4, 5);
		uf.union(6, 7);
		uf.union(0, 2);
		uf.union(4, 6);
		uf.union(0, 4);
		StdOut.println("==========================");
	}
	public static void main(String[] args) {
		controlExperiment();
		theWorstConditionExperiment();
	}
	// output
	/*
	 * 	========  对照实验 ========
		连接 4 3 访问数组 : 8 次  总共访问数组 : 8 次
		--------------------------
		[id]:	 0 1 2 3 4 5 6 7 8 9
			 	 0 1 2 4 4 5 6 7 8 9
		[sz]:	 1 1 1 1 2 1 1 1 1 1
		--------------------------
		
		连接 3 8 访问数组 : 10 次  总共访问数组 : 18 次
		--------------------------
		[id]:	 0 1 2 3 4 5 6 7 8 9
			 	 0 1 2 4 4 5 6 7 4 9
		[sz]:	 1 1 1 1 3 1 1 1 1 1
		--------------------------
		
		连接 6 5 访问数组 : 8 次  总共访问数组 : 26 次
		--------------------------
		[id]:	 0 1 2 3 4 5 6 7 8 9
			 	 0 1 2 4 4 6 6 7 4 9
		[sz]:	 1 1 1 1 3 1 2 1 1 1
		--------------------------
		
		连接 9 4 访问数组 : 8 次  总共访问数组 : 34 次
		--------------------------
		[id]:	 0 1 2 3 4 5 6 7 8 9
			 	 0 1 2 4 4 6 6 7 4 4
		[sz]:	 1 1 1 1 4 1 2 1 1 1
		--------------------------
		
		连接 2 1 访问数组 : 8 次  总共访问数组 : 42 次
		--------------------------
		[id]:	 0 1 2 3 4 5 6 7 8 9
			 	 0 2 2 4 4 6 6 7 4 4
		[sz]:	 1 1 2 1 4 1 2 1 1 1
		--------------------------
		
		8 9 已连通 访问数组 : 6 次  总共访问数组 : 48 次
		--------------------------
		[id]:	 0 1 2 3 4 5 6 7 8 9
			 	 0 2 2 4 4 6 6 7 4 4
		[sz]:	 1 1 2 1 4 1 2 1 1 1
		--------------------------
		
		连接 5 0 访问数组 : 10 次  总共访问数组 : 58 次
		--------------------------
		[id]:	 0 1 2 3 4 5 6 7 8 9
			 	 6 2 2 4 4 6 6 7 4 4
		[sz]:	 1 1 2 1 4 1 3 1 1 1
		--------------------------
		
		连接 7 2 访问数组 : 8 次  总共访问数组 : 66 次
		--------------------------
		[id]:	 0 1 2 3 4 5 6 7 8 9
			 	 6 2 2 4 4 6 6 2 4 4
		[sz]:	 1 1 3 1 4 1 3 1 1 1
		--------------------------
		
		连接 6 1 访问数组 : 10 次  总共访问数组 : 76 次
		--------------------------
		[id]:	 0 1 2 3 4 5 6 7 8 9
			 	 6 2 6 4 4 6 6 2 4 4
		[sz]:	 1 1 3 1 4 1 6 1 1 1
		--------------------------
		
		1 0 已连通 访问数组 : 8 次  总共访问数组 : 84 次
		--------------------------
		[id]:	 0 1 2 3 4 5 6 7 8 9
			 	 6 2 6 4 4 6 6 2 4 4
		[sz]:	 1 1 3 1 4 1 6 1 1 1
		--------------------------
		
		6 7 已连通 访问数组 : 6 次  总共访问数组 : 90 次
		--------------------------
		[id]:	 0 1 2 3 4 5 6 7 8 9
			 	 6 2 6 4 4 6 6 2 4 4
		[sz]:	 1 1 3 1 4 1 6 1 1 1
		--------------------------
		
		==========================
		========  最坏情况 ========
		连接 0 1 访问数组 : 8 次  总共访问数组 : 8 次
		--------------------------
		[id]:	 0 1 2 3 4 5 6 7 8 9
			 	 0 0 2 3 4 5 6 7 8 9
		[sz]:	 2 1 1 1 1 1 1 1 1 1
		--------------------------
		
		连接 2 3 访问数组 : 8 次  总共访问数组 : 16 次
		--------------------------
		[id]:	 0 1 2 3 4 5 6 7 8 9
			 	 0 0 2 2 4 5 6 7 8 9
		[sz]:	 2 1 2 1 1 1 1 1 1 1
		--------------------------
		
		连接 4 5 访问数组 : 8 次  总共访问数组 : 24 次
		--------------------------
		[id]:	 0 1 2 3 4 5 6 7 8 9
			 	 0 0 2 2 4 4 6 7 8 9
		[sz]:	 2 1 2 1 2 1 1 1 1 1
		--------------------------
		
		连接 6 7 访问数组 : 8 次  总共访问数组 : 32 次
		--------------------------
		[id]:	 0 1 2 3 4 5 6 7 8 9
			 	 0 0 2 2 4 4 6 6 8 9
		[sz]:	 2 1 2 1 2 1 2 1 1 1
		--------------------------
		
		连接 0 2 访问数组 : 8 次  总共访问数组 : 40 次
		--------------------------
		[id]:	 0 1 2 3 4 5 6 7 8 9
			 	 0 0 0 2 4 4 6 6 8 9
		[sz]:	 4 1 2 1 2 1 2 1 1 1
		--------------------------
		
		连接 4 6 访问数组 : 8 次  总共访问数组 : 48 次
		--------------------------
		[id]:	 0 1 2 3 4 5 6 7 8 9
			 	 0 0 0 2 4 4 4 6 8 9
		[sz]:	 4 1 2 1 4 1 2 1 1 1
		--------------------------
		
		连接 0 4 访问数组 : 8 次  总共访问数组 : 56 次
		--------------------------
		[id]:	 0 1 2 3 4 5 6 7 8 9
			 	 0 0 0 2 0 4 4 6 8 9
		[sz]:	 8 1 2 1 4 1 2 1 1 1
		--------------------------
		
		==========================

	 */
}
