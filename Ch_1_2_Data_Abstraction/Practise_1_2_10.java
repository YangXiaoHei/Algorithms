package Ch_1_2_Data_Abstraction;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_2_10 {
	
	static class VisualCounter {
		/*
		 * 操作最大次数
		 */
		private final int N;
		/*
		 * 计数器最大绝对值
		 */
		private final int max;
		private int value;
		private int opCount;
		public VisualCounter(int N, int max) {
			this.N = N;
			this.max = Math.abs(max);
			
			StdDraw.setXscale(0, N);
			StdDraw.setYscale(-max - 10, max + 10);
			StdDraw.setPenRadius(0.01);
			
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.line(0, max / 2, N + 1, max / 2);
			StdDraw.line(0, -max / 2 , N + 1, -max / 2);
			
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.setPenRadius(.001);
		}
		public void increment() { 
			if (opCount < N) {
				opCount++;
				if (value < max)
					value++; 
			}
			StdDraw.point(opCount, value);
		}
		public void decrement() { 
			if (opCount < N) {
				opCount++;
				if (value > -max)
					value--;
			}
			StdDraw.point(opCount, value);
		}
		public String toString() {
			return "VisualCounter " + value;
		}
	}
	
	public static void main(String[] args) {
		int N = 10000;
		int max = 100;
		VisualCounter counter = new VisualCounter(N, max);
		/*
		 * 让计数器一半概率递增，一半概率递减
		 */
		double incrementProbability = 0.5;
		for(int i = 0; i < N; i++) 
			if (StdRandom.bernoulli(incrementProbability))
				counter.increment();
			else
				counter.decrement();
	}
	// output : execute to see graphic drawing
}
