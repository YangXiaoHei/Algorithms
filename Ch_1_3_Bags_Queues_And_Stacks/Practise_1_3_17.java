package Ch_1_3_Bags_Queues_And_Stacks;

import java.util.*;
import java.util.regex.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_3_17 {
    /*
     * 思路 :
     * 
     * 没啥好说的
     */
	static class Transaction {
		private final String who;
		private final String when;
		private final String amount;
		Transaction(String deal) {
			String[] strs = deal.split("\\s+");
			if (strs.length < 3)
				throw new RuntimeException("less arguments than reqiured");
			if (Pattern.compile("[^a-zA-Z']").matcher(strs[0]).find())
				throw new RuntimeException("name string format error");
			if (!Pattern.compile("\\d{1,4}[-/]\\d{1,2}[-/]\\d{1,2}").matcher(strs[1]).find())
				throw new RuntimeException("date format error");
			if (!Pattern.compile("[1-9]\\d*(\\.\\d+)?").matcher(strs[2]).find())
				throw new RuntimeException("number format error");
			who = strs[0];
			when = strs[1];
			amount = strs[2];
		}
		public String toString() {
			return String.format("【姓名 : %5s, 日期 : %5s, 金额 : %5s】", who, when, amount);
		}
	}
	/*
	 * 读取交易订单
	 */
	public static Transaction[] readTransactions(String name) {
		In in = new In(name);
		Practise_1_3_15.Queue<Transaction> queue = new Practise_1_3_15.Queue<Transaction>();
		while(!in.isEmpty()) 
			queue.enqueue(new Transaction(in.readLine()));
		int N = queue.size();
		Transaction[] arr = new Transaction[N];
		for(int i = 0; i < N; i++)
			arr[i] = queue.dequeue();
		return arr;
	}
	public static void main(String[] args) {
		for(Transaction t : readTransactions("/Users/bot/Desktop/transactionTest.txt")) 
			StdOut.println(t);
	}
	// file content 
	/*
	 * 	YangXiaoHei 	1917-7-11 	65790.24945351756
		Catherine  	1994-4-2 	52602.30867099687
		Alice 	1976-7-24  	105788.62370665546
		Kate	 1915-4-17 	29015.984428605854
		George 	2038-8-4  	33877.85594303126
	 */
	// output 
	/*
	 * 	【姓名 : YangXiaoHei, 日期 : 1917-7-11, 金额 : 65790.24945351756】
		【姓名 : Catherine, 日期 : 1994-4-2, 金额 : 52602.30867099687】
		【姓名 : Alice, 日期 : 1976-7-24, 金额 : 105788.62370665546】
		【姓名 :  Kate, 日期 : 1915-4-17, 金额 : 29015.984428605854】
		【姓名 : George, 日期 : 2038-8-4, 金额 : 33877.85594303126】

	 */
}
