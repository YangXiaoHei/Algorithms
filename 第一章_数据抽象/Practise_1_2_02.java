package 第一章_数据抽象;

import edu.princeton.cs.algs4.*;

public class Practise_1_2_02 {
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
		intersectPrint(100);
	}
}
