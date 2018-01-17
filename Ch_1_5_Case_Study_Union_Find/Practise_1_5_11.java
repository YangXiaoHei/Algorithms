package Ch_1_5_Case_Study_Union_Find;

import edu.princeton.cs.algs4.StdOut;

public class Practise_1_5_11 {
	static class QuickFind {
		private int[] id;
		private int accessTimes;
		private int accessTotalTimes;
		QuickFind(int N) {
			id = new int[N];
			for (int i = 0; i < N; i++)
				id[i] = i;
		}
		int find(int p) { return id[p]; }
		void union(int p, int q) {
			accessTimes = 0;
			int pID = find(p);
			int qID = find(q);
			accessTimes += 2;
			if (pID == qID)  {
				accessTotalTimes += accessTimes;
				return;
			}
			for (int i = 0; i < id.length; i++) {
				accessTimes++;
				if (pID == id[i]) {
					accessTimes++;
					id[i] = qID;
				}
			}
			accessTotalTimes += accessTimes;
		}
		public int totalTimes() { return accessTotalTimes; }
	}
	/*
	 * 略微改善了性能，减少了一些数组访问的次数
	 * 
	 * 不加权的算法每次都将所有以 pID 为根结点的触点修改为 qID
	 * 而加权算法只有会将小树移接到大树，在每次碰到同样的选择时，加权算法进入 if 分支的机会更少
	 * 但因为加权算法增加了一定的尺寸数组访问成本，因此只有当数据量大一些时，在更多测试下
	 * 才能展现出算法在访问数组成本上的改善
	 */
	static class WeightedQuickFind {
		private int[] id;
		private int[] size;
		private int count;
		private int accessTimes;
		private int accessTotalTimes;
		WeightedQuickFind(int N) {
			id = new int[N];
			size = new int[N];
			for (int i = 0; i < N; i++) {
				id[i] = i;
				size[i] = 1;
			}
		}
		int find(int p) { return id[p]; }
		boolean connected(int p, int q) { return find(p) == find(q); }
		void union(int p, int q) {
			accessTimes = 0;
			int pID = find(p);
			int qID = find(q);
			accessTimes += 2;
			if (pID == qID) {
				accessTotalTimes += accessTimes;
				return;
			}
			
			// 区分规模大小
			int larger = size[pID] > size[qID] ? pID : qID;
			int smaller = larger == pID ? qID : pID; 
			
			accessTimes += 2;
			
			for (int i = 0; i < id.length; i++) {
				accessTimes ++;
				// 把规模小的根结点统一改成规模大的根结点
				if (id[i] == smaller) {
					accessTimes += 4;
					id[i] = larger;
					size[larger] += size[i];
				}
			}
			accessTotalTimes += accessTimes;
		}
		public int totalTimes() { return accessTotalTimes; }
	}
	public static void main(String[] args) {
		int N = 10000;
		Text_Generator gen = new Text_RandomPairGenerator(N);
		QuickFind qf = new QuickFind(N);
		WeightedQuickFind wqf = new WeightedQuickFind(N);
		for (int i = 0; i < N; i++) {
			int[] pair = gen.nextPair();
			qf.union(pair[0], pair[1]);
			wqf.union(pair[0], pair[1]);
		}
		StdOut.printf("不加权 :\t%d\n", qf.totalTimes());
		StdOut.printf("加权 :\t%d\n", wqf.totalTimes());
	}
	// output
	/*
	 * 	不加权 :	89449539
		加权 :	83888180

	 */
}
