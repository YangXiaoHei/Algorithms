package 第一章_数据抽象;

import java.awt.Color;
import edu.princeton.cs.algs4.*;

public class Practise_1_2_03 {
	static class Rectangle {
		Point2D leftUp, leftDown, rightUp, rightDown;
		Rectangle(Point2D lu, Point2D ld, Point2D rd, Point2D ru) {
			leftUp = lu;
			leftDown = ld;
			rightUp = ru;
			rightDown = rd;
		}
		void drawPoint() {
			leftUp.draw();
			leftDown.draw();
			rightUp.draw();
			rightDown.draw();
		}
		void strokeLine() {
			StdDraw.line(leftUp.x(), leftUp.y(), rightUp.x(), rightUp.y());
			StdDraw.line(leftUp.x(), leftUp.y(), leftDown.x(), leftDown.y());
			StdDraw.line(leftDown.x(), leftDown.y(), rightDown.x(), rightDown.y());
			StdDraw.line(rightDown.x(), rightDown.y(), rightUp.x(), rightUp.y());
		}
	}
	
	public static void interval2DTest(int N, double min, double max) {
		StdDraw.setXscale(min, max);
		StdDraw.setYscale(min, max);
		StdDraw.setPenRadius(Math.abs(min - max) / 1000000.0);

		Rectangle[] rects = new Rectangle[N];
		Interval2D[] intervals = new Interval2D[N];
		for(int i = 0; i < N; i++) {
			double xlo = StdRandom.uniform(min, max);
			double xhi = StdRandom.uniform(min, max);
			if (xlo > xhi) {
				double tmp = xlo;
				xlo = xhi;
				xhi = tmp;
			}
			Interval1D x = new Interval1D(xlo, xhi);
			double ylo = StdRandom.uniform(min, max);
			double yhi = StdRandom.uniform(min, max);
			if (ylo > yhi) {
				double tmp = ylo;
				ylo = yhi;
				yhi = tmp;
			}
			Interval1D y = new Interval1D(ylo, yhi);
			Interval2D box = new Interval2D(x, y);
			StdDraw.setPenColor(Color.black);
			StdDraw.setPenRadius(Math.abs(min - max) / 1000000.0);
			box.draw();
			intervals[i] = box;
			rects[i] = new Rectangle(new Point2D(xlo, yhi), 
									 new Point2D(xlo, ylo),
									 new Point2D(xhi, ylo),
									 new Point2D(xhi, yhi));
		}
		
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				if (i != j) {
					
					// 是否相交
					if (intervals[i].intersects(intervals[j])) {
						StdOut.println(intervals[i] + " 与 " + intervals[j] + "相交");
						Rectangle r1 = rects[i];
						Rectangle r2 = rects[j];
						StdDraw.setPenColor(Color.red);
						StdDraw.setPenRadius(0.001);
						r1.strokeLine(); r2.strokeLine();
					}
					
					// 是否包含
					Rectangle r1 = rects[i];
					Rectangle r2 = rects[j];
					if (contains(r1, r2)) {
						StdOut.println(intervals[i] + " 包含 " + intervals[j]);
						StdDraw.setPenColor(Color.blue);
						StdDraw.setPenRadius(0.01);
						 r2.drawPoint();
						 StdDraw.setPenRadius(0.1);
						 r1.drawPoint();
					} else if (contains(r2, r1)) {
						StdDraw.setPenColor(Color.blue);
						StdDraw.setPenRadius(0.01);
						r1.drawPoint();
						StdDraw.setPenRadius(0.1);
						 r2.drawPoint();
						StdOut.println(intervals[j] + " 包含 " + intervals[i]);
					}
				}
	}
	
	/*
	 * 判断包含关系
	 */
	public static boolean contains(Rectangle r1, Rectangle r2) {
		if (r1.leftUp.x() < r2.leftUp.x() &&
			r1.leftUp.y() > r2.leftUp.y() &&
			r1.rightDown.x() > r2.rightDown.x() && 
			r1.rightDown.y() < r2.rightDown.y()) return true;
		return false;
	}

	public static void main(String[] args) {
		interval2DTest(12, 1.0, 100.0);
	}
}
