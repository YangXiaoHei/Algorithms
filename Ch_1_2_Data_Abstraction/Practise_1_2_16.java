package Ch_1_2_Data_Abstraction;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Practise_1_2_16 {
	static long gcd(long p, long q) {
		if (q == 0) return p;
		return gcd(q, p % q);
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
			return new Rational(num * b.deno + deno * b.num, deno * b.deno);
		}
		Rational minus(Rational b) {
			return new Rational(num * b.deno - deno * b.num, deno * b.deno);
		}
		Rational times(Rational b) {
			return new Rational(num * b.num, deno * b.deno);
		}
		Rational divides(Rational b) {
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
		for(int i = 0; i < 8; i++)
			Rational.printPlus(Rational.randomRational(), 
								Rational.randomRational());
		for(int i = 0; i < 8; i++)
			Rational.printMinus(Rational.randomRational(), 
								Rational.randomRational());
		for(int i = 0; i < 8; i++)
			Rational.printTimes(Rational.randomRational(), 
								Rational.randomRational());
		for(int i = 0; i < 8; i++)
			Rational.printDivides(Rational.randomRational(), 
								Rational.randomRational());
	}
	// output :
	/*
	 * 	【2】 + 【2】 = 【4】
		【2】 + 【4】 = 【6】
		【3/2】 + 【2】 = 【7/2】
		【3/2】 + 【1/6】 = 【5/3】
		【1/4】 + 【1/3】 = 【7/12】
		【3/2】 + 【1/2】 = 【2】
		【4】 + 【1/8】 = 【33/8】
		【7/5】 + 【1】 = 【12/5】
		【9】 - 【9/2】 = 【9/2】
		【4/9】 - 【1/8】 = 【23/72】
		【7/6】 - 【8】 = -【41/6】
		【9/2】 - 【1】 = 【7/2】
		【5/7】 - 【2/3】 = 【1/21】
		【7】 - 【7】 = 【0】
		【1】 - 【1/2】 = 【1/2】
		【5/7】 - 【5/3】 = -【20/21】
		【5/3】 * 【7/8】 = 【35/24】
		【1】 * 【1/3】 = 【1/3】
		【2/3】 * 【2/3】 = 【4/9】
		【2】 * 【1/7】 = 【2/7】
		【1】 * 【8/7】 = 【8/7】
		【2】 * 【2/9】 = 【4/9】
		【1】 * 【1】 = 【1】
		【7/2】 * 【3】 = 【21/2】
		【3/4】 / 【1/4】 = 【3】
		【3/2】 / 【1】 = 【3/2】
		【1】 / 【4】 = 【1/4】
		【7/5】 / 【1/6】 = 【42/5】
		【3/5】 / 【1/4】 = 【12/5】
		【3/2】 / 【2】 = 【3/4】
		【2】 / 【3】 = 【2/3】
		【4/5】 / 【4/7】 = 【7/5】
	 */
}
