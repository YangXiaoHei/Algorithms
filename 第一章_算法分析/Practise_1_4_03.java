package 第一章_算法分析;

import edu.princeton.cs.algs4.*;

public class Practise_1_4_03 {
	static class DoublingTest {
		public static double timeTrial(int N) {
			int MAX = 1000000;
			int[] a = new int[N];
			for (int i = 0; i < N; i++)
				a[i] = StdRandom.uniform(-MAX, MAX);
			Stopwatch timer = new Stopwatch();
			int cnt = ThreeSum.count(a);
			return timer.elapsedTime();
		}
		public static void drawN(int N) {
			StdDraw.setXscale(0, N);
			StdDraw.setYscale(0, 1);
			StdDraw.setPenRadius(.001);
			for (int i = 1; i < N; i++)
				StdDraw.point(i, timeTrial(i));
		}
		public static void drawLgN(int N) {
			StdDraw.setXscale(0, N);
			StdDraw.setYscale(-10, 10);
			StdDraw.setPenRadius(.01);
			for (int i = 0; i < N; i += 10) 
				StdDraw.point(i, 0);
			for (int i = 1; i < N; i += 100) {
				StdDraw.point(i, Math.log(timeTrial(i)));
			}
		}
	}
	public static void main(String[] args) {
		DoublingTest.drawN(3000);
		DoublingTest.drawLgN(8000);
	}
	// output : execute to see drawing
}
