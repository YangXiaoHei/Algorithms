package Ch_1_1_Basic_Programming_Model;

import edu.princeton.cs.algs4.*;
import java.awt.Color;
import java.util.*;

public class Practise_1_1_31 {
	// x 轴边界
	private static double xScale = 100;
	// y 轴边界
	private static double yScale = 100;
	// 圆的半径
	private static double radius = 50;
	// 圆心
	private static Point center = new Point(50, 50);
	/*
	 * 点
	 */
	static class Point {
		private double x;
		private double y;
		public Point() { this(0,0); }
		public Point(double x, double y) { 
			this.x = x; 
			this.y = y; 
		}
		/*
		 * 获取该点偏移 angle，并相距为 distance 的点
		 */
		public Point newPoint(double distance, double angle) {
			double newX = 0;
			double newY = 0;
			if (angle >= 0 && angle <= Math.PI / 2) {
				newX = x + distance * Math.cos(angle);
				newY = y + distance * Math.sin(angle);
			} else if (angle > Math.PI / 2 && angle <= Math.PI) {
				newX = x - distance * Math.cos(Math.PI - angle);
				newY = y + distance * Math.sin(Math.PI - angle);
			} else if (angle > Math.PI && angle <= Math.PI * 1.5) {
				newX = x - distance * Math.cos(angle - Math.PI);
				newY = y - distance * Math.sin(angle - Math.PI);
			} else {
				newX = x + distance * Math.cos(Math.PI * 2 - angle);
				newY = y - distance * Math.sin(Math.PI * 2 - angle);
			}
			return new Point(newX, newY);
		}
	}
	
	/*
	 * 模拟在某个概率下，是否发生该事件
	 */
	public static boolean occur(double p) {
		int count = (int)(p * 100000);
		int random = StdRandom.uniform(100000);
		return random <= count;
	}
	
	/*
	 * 绘图
	 */
	public static void draw(int N, double p) {
		// 设置边界
		StdDraw.setXscale(0, xScale);
		StdDraw.setYscale(0, xScale);
		
		// 设置笔触颜色
		StdDraw.setPenColor(Color.GRAY);
		
		// 设置笔触大小
		StdDraw.setPenRadius(.001);
		
		// 生成 N 个在圆上等距离的点
		Point[] points = new Point[N];
		double angle = Math.PI * 2 / N;
		for(int i = 0; i < N; i++) 
			points[i] = center.newPoint(radius, angle * i);
		
		// 连线
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(j != i) {
					Point p1 = points[i];
					Point p2 = points[j];
					if (occur(p))
						StdDraw.line(p1.x, p1.y, p2.x, p2.y);
				}
			}
		}	
	}
	
	public static void main(String[] args) {
		draw(100, 0.1);
	}
	// output : execute to see graph drawing
}
