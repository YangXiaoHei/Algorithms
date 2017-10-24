package 第一章_数据抽象;

import edu.princeton.cs.algs4.*;

public class Practise_1_2_02 {
	/*
	 * 打印相交
	 */
	public static void intersectPrint(int N) {
		Interval1D[] intervals = new Interval1D[N];
		for(int i = 0; i < N; i++) {
			double x1 = StdRandom.uniform(0, 100);
			double x2 = StdRandom.uniform(0, 100);
			if (x1 > x2) {
				double tmp = x1;
				x1 = x2;
				x2 = tmp;
			}
			intervals[i] = new Interval1D(x1, x2);
		}
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				if(i != j) {
					if(intervals[i].intersects(intervals[j])) {
						StdOut.println(intervals[i] + " 和 " + intervals[j] + " 相交");
					}
				}
	}
	public static void main(String[] args) {
		intersectPrint(10);
	}
	// output : 
	/*
	 * 	[11.0, 79.0] 和 [39.0, 86.0] 相交
		[11.0, 79.0] 和 [15.0, 76.0] 相交
		[11.0, 79.0] 和 [5.0, 85.0] 相交
		[11.0, 79.0] 和 [15.0, 46.0] 相交
		[11.0, 79.0] 和 [1.0, 73.0] 相交
		[11.0, 79.0] 和 [65.0, 66.0] 相交
		[11.0, 79.0] 和 [36.0, 99.0] 相交
		[11.0, 79.0] 和 [20.0, 82.0] 相交
		[11.0, 79.0] 和 [29.0, 95.0] 相交
		[39.0, 86.0] 和 [11.0, 79.0] 相交
		[39.0, 86.0] 和 [15.0, 76.0] 相交
		[39.0, 86.0] 和 [5.0, 85.0] 相交
		[39.0, 86.0] 和 [15.0, 46.0] 相交
		[39.0, 86.0] 和 [1.0, 73.0] 相交
		[39.0, 86.0] 和 [65.0, 66.0] 相交
		[39.0, 86.0] 和 [36.0, 99.0] 相交
		[39.0, 86.0] 和 [20.0, 82.0] 相交
		[39.0, 86.0] 和 [29.0, 95.0] 相交
		[15.0, 76.0] 和 [11.0, 79.0] 相交
		[15.0, 76.0] 和 [39.0, 86.0] 相交
		[15.0, 76.0] 和 [5.0, 85.0] 相交
		[15.0, 76.0] 和 [15.0, 46.0] 相交
		[15.0, 76.0] 和 [1.0, 73.0] 相交
		[15.0, 76.0] 和 [65.0, 66.0] 相交
		[15.0, 76.0] 和 [36.0, 99.0] 相交
		[15.0, 76.0] 和 [20.0, 82.0] 相交
		[15.0, 76.0] 和 [29.0, 95.0] 相交
		[5.0, 85.0] 和 [11.0, 79.0] 相交
		[5.0, 85.0] 和 [39.0, 86.0] 相交
		[5.0, 85.0] 和 [15.0, 76.0] 相交
		[5.0, 85.0] 和 [15.0, 46.0] 相交
		[5.0, 85.0] 和 [1.0, 73.0] 相交
		[5.0, 85.0] 和 [65.0, 66.0] 相交
		[5.0, 85.0] 和 [36.0, 99.0] 相交
		[5.0, 85.0] 和 [20.0, 82.0] 相交
		[5.0, 85.0] 和 [29.0, 95.0] 相交
		[15.0, 46.0] 和 [11.0, 79.0] 相交
		[15.0, 46.0] 和 [39.0, 86.0] 相交
		[15.0, 46.0] 和 [15.0, 76.0] 相交
		[15.0, 46.0] 和 [5.0, 85.0] 相交
		[15.0, 46.0] 和 [1.0, 73.0] 相交
		[15.0, 46.0] 和 [36.0, 99.0] 相交
		[15.0, 46.0] 和 [20.0, 82.0] 相交
		[15.0, 46.0] 和 [29.0, 95.0] 相交
		[1.0, 73.0] 和 [11.0, 79.0] 相交
		[1.0, 73.0] 和 [39.0, 86.0] 相交
		[1.0, 73.0] 和 [15.0, 76.0] 相交
		[1.0, 73.0] 和 [5.0, 85.0] 相交
		[1.0, 73.0] 和 [15.0, 46.0] 相交
		[1.0, 73.0] 和 [65.0, 66.0] 相交
		[1.0, 73.0] 和 [36.0, 99.0] 相交
		[1.0, 73.0] 和 [20.0, 82.0] 相交
		[1.0, 73.0] 和 [29.0, 95.0] 相交
		[65.0, 66.0] 和 [11.0, 79.0] 相交
		[65.0, 66.0] 和 [39.0, 86.0] 相交
		[65.0, 66.0] 和 [15.0, 76.0] 相交
		[65.0, 66.0] 和 [5.0, 85.0] 相交
		[65.0, 66.0] 和 [1.0, 73.0] 相交
		[65.0, 66.0] 和 [36.0, 99.0] 相交
		[65.0, 66.0] 和 [20.0, 82.0] 相交
		[65.0, 66.0] 和 [29.0, 95.0] 相交
		[36.0, 99.0] 和 [11.0, 79.0] 相交
		[36.0, 99.0] 和 [39.0, 86.0] 相交
		[36.0, 99.0] 和 [15.0, 76.0] 相交
		[36.0, 99.0] 和 [5.0, 85.0] 相交
		[36.0, 99.0] 和 [15.0, 46.0] 相交
		[36.0, 99.0] 和 [1.0, 73.0] 相交
		[36.0, 99.0] 和 [65.0, 66.0] 相交
		[36.0, 99.0] 和 [20.0, 82.0] 相交
		[36.0, 99.0] 和 [29.0, 95.0] 相交
		[20.0, 82.0] 和 [11.0, 79.0] 相交
		[20.0, 82.0] 和 [39.0, 86.0] 相交
		[20.0, 82.0] 和 [15.0, 76.0] 相交
		[20.0, 82.0] 和 [5.0, 85.0] 相交
		[20.0, 82.0] 和 [15.0, 46.0] 相交
		[20.0, 82.0] 和 [1.0, 73.0] 相交
		[20.0, 82.0] 和 [65.0, 66.0] 相交
		[20.0, 82.0] 和 [36.0, 99.0] 相交
		[20.0, 82.0] 和 [29.0, 95.0] 相交
		[29.0, 95.0] 和 [11.0, 79.0] 相交
		[29.0, 95.0] 和 [39.0, 86.0] 相交
		[29.0, 95.0] 和 [15.0, 76.0] 相交
		[29.0, 95.0] 和 [5.0, 85.0] 相交
		[29.0, 95.0] 和 [15.0, 46.0] 相交
		[29.0, 95.0] 和 [1.0, 73.0] 相交
		[29.0, 95.0] 和 [65.0, 66.0] 相交
		[29.0, 95.0] 和 [36.0, 99.0] 相交
		[29.0, 95.0] 和 [20.0, 82.0] 相交
	 */
}
