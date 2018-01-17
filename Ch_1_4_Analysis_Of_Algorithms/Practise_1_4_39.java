package Ch_1_4_Analysis_Of_Algorithms;

import edu.princeton.cs.algs4.*;

public class Practise_1_4_39 {
	static class ThreeSum {
		static int count(int[] a) {
			int N = a.length;
			int cnt = 0;
			for (int i = 0; i < N; i++)
				for (int j = i + 1; j < N; j++)
					for (int k = j + 1; k < N; k++)
						if (a[i] + a[j] + a[i] == 0)
							cnt++;
			return cnt;
		}
	}
	static class DoublingRatio {
		static double timeTrial(int N, int M) {
			int[] arr = new int[N];
			for (int i = 0; i < N; i++)
				arr[i] = StdRandom.uniform(-10000, 10000);
			double averageTime = 0;
			for (int i = 0; i < M; i++) {
				Stopwatch timer = new Stopwatch();
				int cnt = ThreeSum.count(arr);
				averageTime += timer.elapsedTime();
			}
			return averageTime / M;
		}
		public static void main(String[] args) {
			StdOut.printf("N = 10, M = 100 TotalTime : %f\n",
					DoublingRatio.timeTrial(10, 100));
			StdOut.printf("N = 100, M = 100 TotalTime : %f\n",
					DoublingRatio.timeTrial(100, 100));
			StdOut.printf("N = 1000, M = 100 TotalTime : %f\n", 
					DoublingRatio.timeTrial(1000, 100));
		}
	}
	public static void main(String[] args) {
		DoublingRatio.main(null);
	}
	// output
	/*
	 * 
	 *  第一次运行 :
	 *  
	 *  N = 10, M = 100 TotalTime : 0.000020
		N = 100, M = 100 TotalTime : 0.000080
		N = 1000, M = 100 TotalTime : 0.056960
		
		第二次运行 :
		N = 10, M = 100 TotalTime : 0.000020
		N = 100, M = 100 TotalTime : 0.000140
		N = 1000, M = 100 TotalTime : 0.057600
		
		第三次运行 :
		N = 10, M = 100 TotalTime : 0.000000
		N = 100, M = 100 TotalTime : 0.000090
		N = 1000, M = 100 TotalTime : 0.042590

		第四次运行 :
		N = 10, M = 100 TotalTime : 0.000020
		N = 100, M = 100 TotalTime : 0.000080
		N = 1000, M = 100 TotalTime : 0.042220

	 */
}
