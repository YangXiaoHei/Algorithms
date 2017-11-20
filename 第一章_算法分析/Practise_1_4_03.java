package 第一章_算法分析;

import edu.princeton.cs.algs4.*;
/*
 * 思路 :
 * 
 * ThreeSum 是一个 O(n^3) 的算法，因此倍率实验得出的值应该最终会稳定在 8
 * 因此我们如果对运行时间取对数，会得到一条斜率为 3 的直线
 * 
 */
public class Practise_1_4_03 {
	static class DoublingTest {
		public static double timeTrial(int N) {
			int MAX = 1000000;
			int[] a = new int[N];
			for (int i = 0; i < N; i++)
				a[i] = StdRandom.uniform(-MAX, MAX);
			Stopwatch timer = new Stopwatch();
			ThreeSum.count(a);
			return timer.elapsedTime();
		}
		public static void drawStd(int N) {
			StdDraw.setXscale(0, N);
			StdDraw.setYscale(0, 1);
			StdDraw.setPenRadius(.001);
			for (int i = 1; i < N; i++)
				StdDraw.point(i, timeTrial(i));
		}
		public static void drawLgN(int N) {
			StdDraw.setXscale(0, N);
			StdDraw.setYscale(-10, 10);
			StdDraw.setPenRadius(.001);
			for (int i = 1; i < N; i++) {
				StdDraw.point(i, Math.log(timeTrial(i)));
			}
		}
	}
	public static void main(String[] args) {
		DoublingTest.drawStd(3000);
		DoublingTest.drawLgN(3000);
	}
	// output : execute to see drawing
}
