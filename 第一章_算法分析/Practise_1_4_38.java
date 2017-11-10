package 第一章_算法分析;

import edu.princeton.cs.algs4.*;

public class Practise_1_4_38 {
	static int[] souceArr(int N) {
		int[] arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = StdRandom.uniform(-1000, 1000);
		return arr;
	}
	static class NaiveThreeSum {
		static int count(int[] a) {
		 int N = a.length;
		 int cnt = 0;
	     for (int i = 0; i < N; i++)
	         for (int j = 0; j < N; j++)
	            for (int k = 0; k < N; k++)
	               if (i < j && j < k)
	                  if (a[i] + a[j] + a[k] == 0)
	                     cnt++;
		     return cnt;
		}
	}
	static class BruteForceThreeSum {
		static int count(int[] a) {
			int N = a.length;
			int cnt = 0;
			for (int i = 0; i < N; i++)
				for (int j = i + 1; j < N; j++)
					for (int k = j + 1; k < N; k++)
						if (a[i] + a[j] + a[k] == 0)
							cnt++;
			return cnt;
		}
	}
	static class DoublingRatioTest {
		static double timeTrial_Naive(int[] arr) {
			Stopwatch timer = new Stopwatch();
			int cnt = NaiveThreeSum.count(arr);
			return timer.elapsedTime();
		}
		static double timeTrial_BruteForce(int[] arr) {
			Stopwatch timer = new Stopwatch();
			int cnt = NaiveThreeSum.count(arr);
			return timer.elapsedTime();
		}
	}
	public static void main(String[] args) {
		double naivePrev = 1.0, bruteForcePrev = 1.0;
		for (int i = 200, j = 0; j < 7; j++, i += i) {
			int[] arr = souceArr(i);
			double naiveTime = DoublingRatioTest.timeTrial_Naive(arr);
			double bruteForceTime = DoublingRatioTest.timeTrial_Naive(arr);
			double naiveRatio = naiveTime / naivePrev;
			double bruteForceRatio = bruteForceTime / bruteForcePrev;
			naivePrev = naiveTime;
			bruteForcePrev = bruteForceTime;
			StdOut.printf("Naive --> N = %d  Ratio: %f TotalTime : %f秒\n", 
					i, naiveRatio, naiveTime);
			StdOut.printf("Brute --> N = %d  Ratio : %f TotalTime : %f秒\n",
					i, bruteForceRatio, bruteForceTime);
			StdOut.println();
		}
	}
	// output
	/*
	 * 	第一次运行 :
	 *  
	 *  Naive --> N = 200  Ratio: 0.015000 TotalTime : 0.015000秒
		Brute --> N = 200  Ratio : 0.010000 TotalTime : 0.010000秒
		
		Naive --> N = 400  Ratio: 2.200000 TotalTime : 0.033000秒
		Brute --> N = 400  Ratio : 3.500000 TotalTime : 0.035000秒
		
		Naive --> N = 800  Ratio: 7.666667 TotalTime : 0.253000秒
		Brute --> N = 800  Ratio : 7.371429 TotalTime : 0.258000秒
		
		Naive --> N = 1600  Ratio: 7.944664 TotalTime : 2.010000秒
		Brute --> N = 1600  Ratio : 6.403101 TotalTime : 1.652000秒
		
		Naive --> N = 3200  Ratio: 6.133333 TotalTime : 12.328000秒
		Brute --> N = 3200  Ratio : 6.144673 TotalTime : 10.151000秒
		
		Naive --> N = 6400  Ratio: 6.082414 TotalTime : 74.984000秒
		Brute --> N = 6400  Ratio : 7.330115 TotalTime : 74.408000秒
		
		Naive --> N = 12800  Ratio: 7.925331 TotalTime : 594.273000秒
		Brute --> N = 12800  Ratio : 8.351804 TotalTime : 621.441000秒
		
		
		第二次运行 :
		
		Naive --> N = 200  Ratio: 0.011000 TotalTime : 0.011000秒
		Brute --> N = 200  Ratio : 0.004000 TotalTime : 0.004000秒
		
		Naive --> N = 400  Ratio: 2.000000 TotalTime : 0.022000秒
		Brute --> N = 400  Ratio : 6.000000 TotalTime : 0.024000秒
		
		Naive --> N = 800  Ratio: 8.000000 TotalTime : 0.176000秒
		Brute --> N = 800  Ratio : 6.375000 TotalTime : 0.153000秒
		
		Naive --> N = 1600  Ratio: 6.744318 TotalTime : 1.187000秒
		Brute --> N = 1600  Ratio : 7.705882 TotalTime : 1.179000秒
		
		Naive --> N = 3200  Ratio: 8.927548 TotalTime : 10.597000秒
		Brute --> N = 3200  Ratio : 7.520780 TotalTime : 8.867000秒
		
		Naive --> N = 6400  Ratio: 6.795414 TotalTime : 72.011000秒
		Brute --> N = 6400  Ratio : 8.068231 TotalTime : 71.541000秒
		
		Naive --> N = 12800  Ratio: 7.830484 TotalTime : 563.881000秒
		Brute --> N = 12800  Ratio : 8.411009 TotalTime : 601.732000秒
	 */
}
