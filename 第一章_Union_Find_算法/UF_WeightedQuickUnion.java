package 第一章_Union_Find_算法;

public class UF_WeightedQuickUnion extends UF {
	private int[] sz;
	public UF_WeightedQuickUnion(int N) { super(N); 
		sz = new int[N]; 
		for (int i = 0; i < N; i++)
			sz[i] = 1;
	}
	public boolean connected(int p, int q) { return find(p) == find(q); }
	public int find(int p) {
		while (p != id[p]) p = id[p];
		return p;
	}
	public void union(int p, int q) {
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
		count--;
	}
	public int maxTreeDepth() {
		int depth = 0;
		for (int i = 0; i < id.length; i++) {
			int tmp = 0, p = i;
			while (p != id[p]) {
				p = id[p];
				tmp++;
			}
			if (tmp > depth) depth = tmp;
		}
		return depth;
	}
	public static void main(String[] args) {
		int N = 10, pairCount = 20;
		UF.test(new UF_WeightedQuickUnion(N), N, pairCount);
	}
	// output
	/*
	 * 	--------------------------------
		索引 :	0 1 2 3 4 5 6 7 8 9 
				0 1 2 3 4 5 6 7 8 9       连通分量 : 10
		--------------------------------
		
		连接 8 7
		--------------------------------
		索引 :	0 1 2 3 4 5 6 7 8 9 
				0 1 2 3 4 5 6 8 8 9       连通分量 : 9
		--------------------------------
		
		连接 8 3
		--------------------------------
		索引 :	0 1 2 3 4 5 6 7 8 9 
				0 1 2 8 4 5 6 8 8 9       连通分量 : 8
		--------------------------------
		
		连接 7 2
		--------------------------------
		索引 :	0 1 2 3 4 5 6 7 8 9 
				0 1 8 8 4 5 6 8 8 9       连通分量 : 7
		--------------------------------
		
		连接 0 2
		--------------------------------
		索引 :	0 1 2 3 4 5 6 7 8 9 
				8 1 8 8 4 5 6 8 8 9       连通分量 : 6
		--------------------------------
		
		连接 1 7
		--------------------------------
		索引 :	0 1 2 3 4 5 6 7 8 9 
				8 8 8 8 4 5 6 8 8 9       连通分量 : 5
		--------------------------------
		
		连接 9 4
		--------------------------------
		索引 :	0 1 2 3 4 5 6 7 8 9 
				8 8 8 8 9 5 6 8 8 9       连通分量 : 4
		--------------------------------
		
		1  3 已连通
		1  0 已连通
		8  2 已连通
		连接 5 2
		--------------------------------
		索引 :	0 1 2 3 4 5 6 7 8 9 
				8 8 8 8 9 8 6 8 8 9       连通分量 : 3
		--------------------------------
		
		5  8 已连通
		连接 3 6
		--------------------------------
		索引 :	0 1 2 3 4 5 6 7 8 9 
				8 8 8 8 9 8 8 8 8 9       连通分量 : 2
		--------------------------------
		
		6  2 已连通
		连接 4 5
		--------------------------------
		索引 :	0 1 2 3 4 5 6 7 8 9 
				8 8 8 8 9 8 8 8 8 8       连通分量 : 1
		--------------------------------
		
		5  4 已连通
		8  2 已连通
		6  7 已连通
		2  9 已连通
		9  1 已连通
		1  8 已连通
		最大树高度 : 2
	 */
	
}
