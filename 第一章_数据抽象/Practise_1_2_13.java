package 第一章_数据抽象;

import java.util.regex.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_2_13 {
	static class Transaction {
		private final String who;
		private final String when;
		private final String amount;
		private final String transaction;
		Transaction(String transaction) {
			String[] result = transaction.split("\\s+");
			if (result.length != 3)
				throw new RuntimeException("input" + transaction + "invalid");
			
			who = result[0];
			if (Pattern.compile("[^a-zA-Z'·]").matcher(who).find())
				throw new RuntimeException("invalid name " + who);
			
			when = result[1];
			if (!Pattern.compile("\\d{1,4}[-\\\\]\\d{1,2}[-\\\\]\\d{1,2}").matcher(when).find())
				throw new RuntimeException("invalid date " + when);
			
			amount = result[2];
			if (!Pattern.compile("[1-9]\\d*(\\.\\d+)?").matcher(amount).find())
				throw new RuntimeException("invalid amount " + when);
			
			this.transaction = transaction;
			
			StdOut.println(this);
			StdOut.println("==========================");
		}
		String who() { return who; }
		String when() { return when; }
		String amount() { return amount; }
		public String toString() {
			return String.format("Name : %s\nDate : %s\nAmount : %s", who, when, amount);
		}
	}
	public static void main(String[] args) {
		Transaction t1 = new Transaction("YangXiaoHei 	1917-7-11 	 65790.24945351756");
		Transaction t2 = new Transaction("J·D 		2025-6-14 	70885.58168089352");
		Transaction t3 = new Transaction("Catherine 		1994-4-2		 52602.30867099687");
		Transaction t4 = new Transaction("Alice 			1976-7-24 	105788.62370665546");
		Transaction t5 = new Transaction("Kate 			1915-4-17 	29015.984428605854");
		Transaction t6 = new Transaction("George 		2038-8-4 	33877.85594303126");
		Transaction t7 = new Transaction("Charlotter 	2026-8-19 	189861.76085109965");
		Transaction t8 = new Transaction("Gracie 		1969-11-29 	660894.8400754596");
		Transaction t9 = new Transaction("Harley 		1982-4-21 	642172.8703476848");
		Transaction t10 = new Transaction("Quinn 		1908-3-19 	278283.0001846314");
		Transaction t11 = new Transaction("River 		1911-7-25 	176579.30568000895");
		Transaction t12 = new Transaction("Muhammed 		2018-12-13 	563445.0935486191");
	}
}
