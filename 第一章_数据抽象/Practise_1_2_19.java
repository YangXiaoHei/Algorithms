package 第一章_数据抽象;

import java.util.regex.*;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import 第一章_数据抽象.Practise_1_2_12.DateFormatInvalidException;
import 第一章_数据抽象.Practise_1_2_12.SmartDate;
import 第一章_数据抽象.Practise_1_2_14.Transaction;
public class Practise_1_2_19 {
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
			
			StdOut.println(this + "\n");
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
	static class Date {
		static class DateFormatInvalidException extends Exception {
			DateFormatInvalidException(String s) { super(s); } 
		}
		private final int month;
		private final int day;
		private final int year;
		Date(String dateString) {
			String[] date = dateString.split("[-\\\\/]");
			if (date.length < 3) {
				DateFormatInvalidException e = new DateFormatInvalidException("date string invalid " + dateString);
				throw new RuntimeException(e);
			}
			
			int year, month, day;
			if (!Pattern.compile("\\d{1,2}").matcher(date[0]).find()) {
				DateFormatInvalidException e = new DateFormatInvalidException("invalid month " + date[0]);
				throw new RuntimeException(e);
			}
			if (!Pattern.compile("\\d{1,2}").matcher(date[1]).find()){
				DateFormatInvalidException e = new DateFormatInvalidException("invalid day" + date[1]);
				throw new RuntimeException(e);
			}
			if (!Pattern.compile("\\d{1,4}").matcher(date[2]).find()) {
				DateFormatInvalidException e = new DateFormatInvalidException("invalid year " + date[2]);
				throw new RuntimeException(e);
			}
			year = Integer.parseInt(date[2]);
			month = Integer.parseInt(date[0]);
			day = Integer.parseInt(date[1]);
			
			if (year < 0) {
				DateFormatInvalidException e = new DateFormatInvalidException("year should not be negtive number");
				throw new RuntimeException(e);
			}
			switch (month) {
				case 1 :
				case 3 :
				case 5 :
				case 7 :
				case 8 :
				case 10 :
				case 12 : {
					if (day < 1 || day > 31) {
						DateFormatInvalidException e = new DateFormatInvalidException("the date " + month + "/" + day + "is not exist");
						throw new RuntimeException(e);
					}
				} break;
				case 4 :
				case 6 :
				case 9 :
				case 11 : {
					if (day < 1 || day > 30) {
						DateFormatInvalidException e = new DateFormatInvalidException("the date " + month + "/" + day + "is not exist");
						throw new RuntimeException(e);
					}
				} break;
				case 2 : {
					/* 
					 *  if  (year能被4整除  and  不能被100整除)  or  year能被400整除
					 *  		闰年
					 *  else
					 *  		平年
					 */
					boolean invalid = false;
					// 闰年日期应该在 [1, 29]
					if (isLeapYear(year)) {
						if (day > 29 || day < 1) invalid = true;
					} else
						// 平年日期应该在 [1, 28]
						if (day > 28 || day < 1) invalid = true;
					if (invalid) {
						DateFormatInvalidException e = new DateFormatInvalidException("the date " + year + "/" + month + "/" + day + "is not exist");
						throw new RuntimeException(e);
					}	
				} break;
				default : {
					DateFormatInvalidException e = new DateFormatInvalidException("the date " + month + "/" + day + "is not exist");
					throw new RuntimeException(e);
				} 
			}
			this.month = month;
			this.day = day;
			this.year = year;
		}
		/*
		 * 是否是闰年
		 */
		 static boolean isLeapYear(int year) {
			return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
		}
		
		/*
		 * 取出日期
		 */
		int day() { return day; }
		/*
		 * 取出月份
		 */
		int month() { return month; }
		/*
		 * 取出年份
		 */
		int year() { return year; }
		
		/*
		 * 返回一个每个月天数的数组
		 */
		private static int[] daysPerMonth(int ye) {
			if (ye < 1) {
				DateFormatInvalidException e = new DateFormatInvalidException("the year " + ye + "is not exist");
				throw new RuntimeException(e);
			}
			int[] daysPerMonth = new int[12];
			for(int i = 1; i <= 12; i++) {
				switch(i) {
				case 1 :
				case 3 :
				case 5 :
				case 7 :
				case 8 :
				case 10 :
				case 12 : {
					daysPerMonth[i - 1] = 31;
				} break;
				case 2 : {
					daysPerMonth[i - 1] = isLeapYear(ye) ? 29 : 28;
				} break;
				default : {
					daysPerMonth[i - 1] = 30;
				} break;
				}
			}
			return daysPerMonth;
		}
		/*
		 * 返回一年的总天数
		 */
		private static int totalDaysOfYear(int ye) {
			if (ye < 1) {
				DateFormatInvalidException e = new DateFormatInvalidException("the year " + ye + "is not exist");
				throw new RuntimeException(e);
			}
			int[] daysPerMonth = daysPerMonth(ye);
			int sum = 0;
			for(int i = 0; i < 12; i++)
				sum += daysPerMonth[i];
			return sum;
		}
		/*
		 * 返回该日期是星期几
		 */
		int dayOfTheWeek() {
			int days = 0;
			for(int i = 1; i < year; i++) 
				days += totalDaysOfYear(i);
			int[] daysPerMonth = daysPerMonth(year);
			for(int i = 1; i < month; i++)
				days += daysPerMonth[i - 1];
			days += day - 1;
			int dayOfTheWeek = (days + 1 - 1) % 7;
			return dayOfTheWeek + 1;
		}
		/*
		 * 获取一个合法的随机日期
		 */
		static SmartDate randomDate() {
			// 范围小一点好验证
			int year = StdRandom.uniform(1900, 2051);
			int month = StdRandom.uniform(1, 13);
			int[] daysPerMonth = daysPerMonth(year);
			int day = StdRandom.uniform(1, daysPerMonth[month - 1]);
			return new SmartDate(month, day, year);
		}
		
		/*
		 * 获取星期几的惯用语
		 */
		String weekdayString(int weekday) {
			switch(weekday) {
			case 1 : return "一";
			case 2 : return "二";
			case 3 : return "三";
			case 4 : return "四";
			case 5 : return "五";
			case 6 : return "六";
			case 7 : return "日";
			}
			DateFormatInvalidException e = new DateFormatInvalidException("the weekday " + weekday + "is not exist");
			throw new RuntimeException(e);
		}
		public String toString() {
			return String.format("日期 : %d-%d-%d 【周%s】", year, month, day, weekdayString(dayOfTheWeek()));
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
		
		StdOut.println("===================================================");
		
		for(int i = 0; i < 10; i++)
			StdOut.println(SmartDate.randomDate());
	}
}
