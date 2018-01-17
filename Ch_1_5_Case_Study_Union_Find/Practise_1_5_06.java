package Ch_1_5_Case_Study_Union_Find;

public class Practise_1_5_06 {
	public static void main(String[] args) {
		/*
		 * weighted-quick-union 和 quick-union 的 find 操作都需要进行遍历，从当前结点直到根结点
		 * 因此 find 操作的遍历深度正比于树深度，最坏情况也就是最大的树高度
		 * 
		 * weighted-quick-union 构造的最大树高度为 lgN
		 * quick-union 构造的最大树高度在最坏情况下为 N
		 * 
		 * 以下对 weighted-quick-union 进行分析
		 * 假设 find 操作每迭代一次执行 5 条机器指令
		 * 每执行一次 union 操作，进行两次查找跟结点操作, 则每次 union 执行 2 * lg10^9 * 5 = 299 条机器指令
		 * 对于 10^6 个连接，总共执行 299 * 10^6 条指令，即 2.99 * 10^8 条指令
		 * 所以可以得到总耗时不过 2.99 * 10^-1 = 0.299 秒
		 * 
		 */
	}
}
