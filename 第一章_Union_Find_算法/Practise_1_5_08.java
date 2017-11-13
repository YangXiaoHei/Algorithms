package 第一章_Union_Find_算法;

public class Practise_1_5_08 {
    /*
     *  public void union(int p, int q)
    	{
	       	if (connected(p, q)) return;
	       
	       // Rename p’s component to q’s name.
	        
	        // 这段代码的问题在于每次用于判断是否出于相同连通分量的 id[p] 会在循环过程中被改变
	        // 导致本该是相同连通分量的两个触点判断失败，忽略了更改相同连通分量中的另一个触点
	       	for (int i = 0; i < id.length; i++)
	           if (id[i] == id[p]) id[i] = id[q];
			count--; 
		}
		
		0 1 2 3 4 5 6 7 8 9
		0 1 2 3 4 5 6 7 8 9
		
		此时 1 3 相连
		0 1 2 3 4 5 6 7 8 9
		0 3 2 3 4 5 6 7 8 9
		
		在连接 1 4 时，本来应该把 索引为 1 3 4 的值都改为 4，
		但因为 id[p] 的值被改了，在对比 id[3] 时进行了错误的判断，本来应该进入的 if 分支进不去了
		0 1 2 3 4 5 6 7 8 9
		0 4 2 3 4 5 6 7 8 9
		
     */
	public static void main(String[] args) {
		
	}
}
