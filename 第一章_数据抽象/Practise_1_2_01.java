package 第一章_数据抽象;

import java.awt.Color;
import edu.princeton.cs.algs4.*;

public class Practise_1_2_01 {
	/*
	 * 两点间最近距离，用红线描出
	 */
	public static void nearstDistance(int N) {
		StdDraw.setXscale(0, 100);
		StdDraw.setYscale(0, 100);
		StdDraw.setPenRadius(.001);
		Point2D[] points = new Point2D[N];
		for(int i = 0; i < N; i++) {
			points[i] = new Point2D(StdRandom.uniform(0, 100.0),
									StdRandom.uniform(0, 100.0));
			points[i].draw();
		}
		
		double distance = Double.MAX_VALUE;
		int index1 = 0, index2 = 0;
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				if (i != j) {
					double dis = points[i].distanceTo(points[j]);
					if (dis < distance && dis != 0) {
						distance = dis;
						index1 = i;
						index2 = j;
					}
				}
		StdOut.println("两点之间最近距离为 : " + distance);
		StdDraw.setPenRadius(.01);
		StdDraw.setPenColor(Color.red);
		StdDraw.line(points[index1].x(), points[index1].y(), 
					 points[index2].x(), points[index2].y());
	}
	public static void main(String[] args) {
		nearstDistance(100);
	}
}
