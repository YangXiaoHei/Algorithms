package Ch_1_5_Case_Study_Union_Find;

public class Practise_1_5_05 {
	public static void main(String[] args) {
		/*
		 * 对于 quick-find  算法，
		 * 每进行一次 union 操作，都要遍历所有的触点，以便将两个两通分量中所有触点都统一成同一个树根
		 * 
		 * 对于每个连接， 迭代 10^9 次，总共执行 10^10 条指令，对于 10^6 个连接，总共执行 10^16 次方条指令
		 * 每秒执行 10^9 条指令，执行完 10^16 次方条指令需要 10^7 秒
		 * 合计 10^7 / (60 * 60 * 24) = 115.74074 天
		 */
	}
}
