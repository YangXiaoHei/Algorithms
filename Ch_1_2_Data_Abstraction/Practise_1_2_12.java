package Ch_1_2_Data_Abstraction;

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
			days += (day - 1);
			return days % 7 + 1;
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
		public static void main(String[] args) {
			for(int i = 0; i < 100; i++)
				StdOut.println(SmartDate.randomDate());
		}
		// output :
		/*
		 * 	日期 : 1972-2-7 【周一】
			日期 : 1935-9-3 【周二】
			日期 : 1980-9-19 【周五】
			日期 : 1939-6-3 【周六】
			日期 : 1924-12-19 【周五】
			日期 : 2019-7-16 【周二】
			日期 : 1957-11-16 【周六】
			日期 : 1973-9-7 【周五】
			日期 : 1931-9-26 【周六】
			日期 : 1953-10-14 【周三】
			日期 : 2012-4-23 【周一】
			日期 : 1998-4-9 【周四】
			日期 : 2039-5-25 【周三】
			日期 : 2039-3-7 【周一】
			日期 : 1960-7-11 【周一】
			日期 : 1903-6-27 【周六】
			日期 : 1993-11-3 【周三】
			日期 : 2006-9-19 【周二】
			日期 : 1935-12-22 【周日】
			日期 : 1969-10-3 【周五】
			日期 : 1928-3-2 【周五】
			日期 : 1901-9-20 【周五】
			日期 : 1935-6-19 【周三】
			日期 : 1995-5-10 【周三】
			日期 : 2044-6-5 【周日】
			日期 : 2029-2-18 【周日】
			日期 : 1968-8-8 【周四】
			日期 : 2009-9-21 【周一】
			日期 : 2050-7-6 【周三】
			日期 : 2028-12-1 【周五】
			日期 : 2014-8-11 【周一】
			日期 : 2048-6-14 【周日】
			日期 : 2031-8-26 【周二】
			日期 : 1927-10-6 【周四】
			日期 : 1928-1-18 【周三】
			日期 : 1947-5-17 【周六】
			日期 : 2022-12-3 【周六】
			日期 : 1937-3-6 【周六】
			日期 : 1978-11-19 【周日】
			日期 : 1968-8-18 【周日】
			日期 : 2038-7-17 【周六】
			日期 : 2034-1-21 【周六】
			日期 : 1908-10-29 【周四】
			日期 : 1913-6-23 【周一】
			日期 : 2035-10-21 【周日】
			日期 : 1955-4-8 【周五】
			日期 : 1998-3-10 【周二】
			日期 : 1964-1-20 【周一】
			日期 : 1962-10-12 【周五】
			日期 : 1985-6-6 【周四】
			日期 : 1999-3-26 【周五】
			日期 : 1959-2-3 【周二】
			日期 : 1925-12-21 【周一】
			日期 : 2027-8-20 【周五】
			日期 : 1964-2-8 【周六】
			日期 : 2000-2-21 【周一】
			日期 : 1993-1-22 【周五】
			日期 : 1921-3-24 【周四】
			日期 : 1912-8-9 【周五】
			日期 : 2041-5-28 【周二】
			日期 : 1984-8-2 【周四】
			日期 : 2007-2-1 【周四】
			日期 : 1990-8-25 【周六】
			日期 : 2036-12-9 【周二】
			日期 : 2015-4-27 【周一】
			日期 : 1922-3-25 【周六】
			日期 : 1924-1-8 【周二】
			日期 : 1951-8-2 【周四】
			日期 : 1983-4-1 【周五】
			日期 : 1963-4-20 【周六】
			日期 : 1905-11-22 【周三】
			日期 : 1908-2-17 【周一】
			日期 : 1947-9-29 【周一】
			日期 : 1981-2-16 【周一】
			日期 : 1985-6-21 【周五】
			日期 : 1965-12-23 【周四】
			日期 : 1995-4-28 【周五】
			日期 : 2038-6-2 【周三】
			日期 : 1943-10-21 【周四】
			日期 : 1920-1-13 【周二】
			日期 : 1939-5-18 【周四】
			日期 : 2045-9-7 【周四】
			日期 : 2041-12-4 【周三】
			日期 : 1906-1-11 【周四】
			日期 : 2022-12-2 【周五】
			日期 : 1990-3-4 【周日】
			日期 : 1993-1-8 【周五】
			日期 : 1920-12-30 【周四】
			日期 : 1904-2-22 【周一】
			日期 : 2039-8-26 【周五】
			日期 : 1936-5-20 【周三】
			日期 : 1940-8-12 【周一】
			日期 : 1977-12-27 【周二】
			日期 : 1992-2-18 【周二】
			日期 : 1995-9-22 【周五】
			日期 : 1953-2-11 【周三】
			日期 : 1922-9-27 【周三】
			日期 : 2004-3-29 【周一】
			日期 : 1936-10-2 【周五】
			日期 : 1960-6-11 【周六】
		 */
	}
}
