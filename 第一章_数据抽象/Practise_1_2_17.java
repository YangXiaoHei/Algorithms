package 第一章_数据抽象;

import edu.princeton.cs.algs4.*;
public class Practise_1_2_17 {
	static long gcd(long p, long q) {
		if (q == 0) return p;
		return gcd(q, p % q);
	}
	/*
	 * x > 0 y > 0 x + y < 0 负溢出
	 * x < 0 y < 0 x + y > 0 正溢出
	 * x < 0 y > 0 不会溢出
	 * x > 0 y < 0 不会溢出
	 */
	static boolean addOverflow(long x, long y) {
		long r = x + y;
		return ((r ^ x) & (r ^ y)) < 0;
	}
	/*
	 * x < 0 y > 0 x - y > 0 负溢出
	 * x > 0 y < 0 x - y < 0 正溢出
	 * x > 0 y > 0 不会溢出
	 * x < 0 y < 0 不会溢出
	 */
	static boolean subOverflow(long x, long y) {
		long r = x - y;
		return ((r ^ x) & (x ^ y)) < 0;
	}
	/*
	 * 如果高 32 位都是 0， 不可能溢出
	 * 由于溢出的截断，r / y != x
	 * 排除特例
	 */
	static boolean mulOverflow(long x, long y) {
		if ((x | y) >>> 31 != 0) {
			x = Math.abs(x);
			y = Math.abs(y);
			long r = x * y;
			/*
			 * r / y != x 包含了 x = -1 y = Long.MinValue的情况
			 * 但没有包含 x = Long.MinValue y = -1 的情况
			 */
			return (y != 0 && (r / y != x)) || (x == Long.MIN_VALUE && y == -1);
		}
		return false;
	}
	static class Rational {
		private long num;
		private long deno;
		Rational(long num, long deno) {
			long factor = gcd(num, deno);
			this.num = num / factor;
			this.deno = deno / factor;
		}
		Rational plus(Rational b) {
			assert !mulOverflow(num, b.deno) : String.format("%d * %d overflow!", num, b.deno);
			assert !mulOverflow(deno, b.num) : String.format("%d * %d overflow!", deno, b.num);
			assert !addOverflow(num * b.deno, deno * b.num) : String.format("%d + %d overflow!", num * b.deno, deno * b.num);
			assert !mulOverflow(deno, b.deno) : String.format("%d * %d overflow!", deno, b.deno);
			return new Rational(num * b.deno + deno * b.num, deno * b.deno);
		}
		Rational minus(Rational b) {
			assert !mulOverflow(num, b.deno) : String.format("%d * %d overflow!", num, b.deno);
			assert !mulOverflow(deno, b.num) : String.format("%d * %d overflow!", deno, b.num);
			assert !subOverflow(num * b.deno, deno * b.num) : String.format("%d - %d overflow!", num * b.deno, deno * b.num);
			assert !mulOverflow(deno, b.deno) : String.format("%d * %d overflow!", deno, b.deno);
			return new Rational(num * b.deno - deno * b.num, deno * b.deno);
		}
		Rational times(Rational b) {
			assert !mulOverflow(num, b.num) : String.format("%d * %d overflow!", num, b.num);
			assert !mulOverflow(deno, b.deno) : String.format("%d * %d overflow!", deno, b.deno);
			return new Rational(num * b.num, deno * b.deno);
		}
		Rational divides(Rational b) {
			assert !mulOverflow(num, b.deno) : String.format("%d * %d overflow!", num, b.deno);
			assert !mulOverflow(deno, b.num) : String.format("%d * %d overflow!", deno, b.num);
			return new Rational(num * b.deno, deno * b.num);
		}
		static Rational randomRational() {
			return new Rational(StdRandom.uniform(1, 10), StdRandom.uniform(1, 10));
		}
		public String toString() {
			String s = String.format("【%d/%d】", Math.abs(num), Math.abs(deno));
			if (deno == 1)
				s = String.format("【%d】", num);
			if (deno < 0 || num < 0)
				s = "-" + s;
			return s;
		}
		static void printPlus(Rational r1, Rational r2) {
			StdOut.println(r1 + " + " + r2 + " = " +  r1.plus(r2));
		}
		static void printMinus(Rational r1, Rational r2) {
			StdOut.println(r1 + " - " + r2 + " = " +   r1.minus(r2));
		}
		static void printTimes(Rational r1, Rational r2) {
			StdOut.println(r1 + " * " + r2 + " = " +   r1.times(r2));
		}
		static void printDivides(Rational r1, Rational r2) {
			StdOut.println(r1 + " / " + r2 + " = " +   r1.divides(r2));
		}
	}
	public static void main(String[] args) {
		StdOut.println(new Rational(Long.MAX_VALUE, 1).plus(new Rational(1, 1)));
		StdOut.println(new Rational(Long.MAX_VALUE, 1).minus(new Rational(1, 1)));
		StdOut.println(new Rational(Long.MAX_VALUE, 1).plus(new Rational(Long.MAX_VALUE, 1)));
		StdOut.println(new Rational(Long.MAX_VALUE, 1).divides(new Rational(Long.MAX_VALUE, 1)));
	}
	// output : 
	// assert handler exception will be thrown
}
