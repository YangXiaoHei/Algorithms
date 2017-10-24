package 第一章_数据抽象;

import java.util.regex.Pattern;

import edu.princeton.cs.algs4.StdOut;
import 第一章_数据抽象.Practise_1_2_13.Transaction;

public class Practise_1_2_14 {
	static class Transaction {
		private final String who;
		private final String when;
		private final String amount;
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
		}
		String who() { return who; }
		String when() { return when; }
		String amount() { return amount; }
		public String toString() {
			return String.format("Name : %s\nDate : %s\nAmount : %s", who, when, amount);
		}
		public boolean equals(Object o) {
			if (o == null) return false;
			if (o == this) return true;
			if (o.getClass() != Transaction.class) return false;
			Transaction that = (Transaction)o;
			if (who.equals(that.who) && when.equals(that.when) &&
				Double.parseDouble(amount) == Double.parseDouble(that.amount))
				return true;
			return false;
		}
	}
	public static void main(String[] args) {
		Transaction 
		t1 = new Transaction("YangXiaoHei 	1917-7-11 	 65790.24945351756"),
		t2 = new Transaction("YangXiaoHei 	1917-7-11 	 65790.24945351756"),
		t3 = new Transaction("HELLO 			1994-4-2		 52602.30867099687"),
		t4 = new Transaction("HELLO 			1994-4-2 	105788.62370665546"),
		t5 = null,
		t6 = new Transaction("YangXiaoHei 	1994-5-9 	 65790.24945351756"),
		t7 = new Transaction("HELLO 			1994-4-2 	105788.62370665546"),
		t8 = new Transaction("HELLO 			1994-4-2 	105788.6");
		StdOut.println(t1.equals(t2));
		StdOut.println(t3.equals(t4));
		StdOut.println(t1.equals(t5));
		StdOut.println(t1.equals(t6));
		StdOut.println(t1.equals(t1));
		StdOut.println(t7.equals(t8));
	}
	// output :
	/*
	 * 	true
		false
		false
		false
		true
		false
	 */
}
