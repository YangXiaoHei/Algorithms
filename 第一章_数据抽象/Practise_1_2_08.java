package 第一章_数据抽象;

import java.util.Arrays;

import edu.princeton.cs.algs4.*;

public class Practise_1_2_08 {
	public static int[] sourceArr(int N, int beg) {
		int[] a = new int[N];
		for(int i = 0; i < N; i++)
			a[i] = beg + i;
		return a;
	}
	public static void main(String[] args) {
		int[] a = sourceArr(5, 10);
		int[] b = sourceArr(5, 0);
		StdOut.println("a = " + Arrays.toString(a));
		StdOut.println("b = " + Arrays.toString(b));
		int[] t = a;
		a = b;
		b = t;
		StdOut.println("==========================");
		StdOut.println("a = " + Arrays.toString(a));
		StdOut.println("b = " + Arrays.toString(b));
	}
}
