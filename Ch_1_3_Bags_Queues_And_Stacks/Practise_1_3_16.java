package Ch_1_3_Bags_Queues_And_Stacks;

import java.util.regex.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_3_16 {
    /*
     * 思路 :
     * 
     * 没啥好说的
     */
	static class Date {
		private final int day;
		private final int month;
		private final int year;
		Date(String dateString) {
			String[] strs = dateString.split("/");
			if (strs.length < 3)
				throw new RuntimeException("incorrect date format");
			if (!Pattern.compile("\\d{1,2}").matcher(strs[0]).find() ||
				!Pattern.compile("\\d{1,2}").matcher(strs[1]).find() ||
				!Pattern.compile("\\d{1,4}").matcher(strs[2]).find())
				throw new RuntimeException("invalid month or day or year" + dateString);
			month = Integer.parseInt(strs[0]);
			day = Integer.parseInt(strs[1]);
			year = Integer.parseInt(strs[2]);
		}
		public String toString() {
			return String.format("%d年%d月%d日", year, month, day);
		}
	}
	/*
	 * 读取 Date
	 */
	public static Date[] readDates(String name) {
		In in = new In(name);
		Practise_1_3_15.Queue<Date> queue = new Practise_1_3_15.Queue<Date>();
		while(!in.isEmpty()) 
			queue.enqueue(new Date(in.readString()));
		int N = queue.size();
		Date[] a = new Date[N];
		for(int i = 0; i < N; i++)
			a[i] = queue.dequeue();
		return a;
	}
	
	public static void main(String[] args) {
		for(Date date : readDates("/Users/bot/Desktop/dateTest.txt")) 
			StdOut.println(date);
	}
	// file content 
	/*	
	 * 	3/24/1994
		9/1/1994
		10/12/2003
		12/1/2017
		10/28/2017
	 */
	
	// output 
	/*
	 * 	1994年3月24日
		1994年9月1日
		2003年10月12日
		2017年12月1日
		2017年10月28日
	 */
}
