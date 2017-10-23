package 第一章_数据抽象;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_2_12 {
	static class DateFormatInvalidException extends Exception {
		DateFormatInvalidException(String s) { super(s); } 
	}
	static class SmartDate {
		private final int month;
		private final int day;
		private final int year;
		/*
		 * 构造器
		 */
		SmartDate(int month, int day, int year) {
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
			// 日期 : 【周%s】
			return String.format("%d-%d-%d ", year, month, day, weekdayString(dayOfTheWeek()));
		}
		public static void main(String[] args) {
			for(int i = 0; i < 12; i++)
				StdOut.println(SmartDate.randomDate());
		}
	}
}
