package 第一章_算法分析;

import edu.princeton.cs.algs4.*;

public class Practise_1_4_09 {
	/*
	 * ThreeSum Doubling ratio experiment
	 */
	static class ThreeSum {
		public static int count(int[] a) {
			int cnt = 0;
			int N = a.length;
			for (int i = 0; i < N; i++)
				for (int j = i + 1; j < N; j++)
					for (int k = j + 1; k < N; k++)
						if (a[i] + a[j] + a[k] == 0)
							cnt++;
			return cnt;
		}
		public static double timeTrial(int N) {
			Stopwatch timer = new Stopwatch();
			int[] arr = new int[N];
			for (int i = 0; i < N; i++)
				arr[i] = StdRandom.uniform(-10000, 10000);
			ThreeSum.count(arr);
			return timer.elapsedTime();
		}
		public static void doublingRatioExperiment() {
			double prev = timeTrial(100);
			for (int N = 200; true; N += N) {
				double time = timeTrial(N);
				StdOut.printf("%6d %7.1f", N, time);
				StdOut.printf("%5.1f\n", time / prev);
				prev = time;
			}
		}
	}
	public static void main(String[] args) {
		/*
		 * 
		 * 	   T(N0) -> T
		 * T(2 * N0) -> 2^b * T
		 * T(2^2 * N0) -> 2^(2b) * T
		 * T(2^3 * N0) -> 2^(3b) * T
		 * T(2^r * N0) -> 2^(r * b) * T
		 * 
		 * T(r) -> 2^(lgN/N0 * b) * T
		 * T(r) -> 2^lg((N/N0)^b) * T
		 * T(r) -> (N/N0)^b * T
		 * 
		 * so we can draw a conclusion total time will be 2^(lgN/N0 * b) * T
		 * 
		 * for example
		 * 
		 * N0 = 4000 T0 = 3.7
		 * N = 16000 T ~ 2^6 * 3.7 = 236.8 ~ 240.3
		 * 
		 */
	}
	// output 
	/*
	 * 
	 * 	Doubling ratio experiments
	 * 
	 * 
	 *     250     0.0  1.0
		   500     0.0  1.3
		  1000     0.1  7.1
		  2000     0.5  8.9
		  4000     3.7  7.2
		  8000    29.6  8.0
		 16000   240.3  8.1


		   250     0.0  1.5
		   500     0.0  0.7
		  1000     0.1  9.9
		  2000     0.8  6.6
		  4000     6.3  8.0
		  8000    46.5  7.4
		 16000   336.4  7.2
		 
		   200     0.0  1.0
		   400     0.0  1.2
		   800     0.1  7.4
		  1600     0.5  9.0
		  3200     3.5  7.4
		  6400    28.9  8.3
		 12800   223.8  7.7

	 */
}
