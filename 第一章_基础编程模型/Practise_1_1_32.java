package 第一章_基础编程模型;

import edu.princeton.cs.algs4.*;
import java.util.*;

public class Practise_1_1_32 {
	public static void draw(double arr[], int N, double l, double r) {
		// 设置 x 轴的边界
		StdDraw.setXscale(0, r);
		
		// 设置 y 轴的边界
		StdDraw.setYscale(0, arr.length);
		
		// 设置笔触的大小
		StdDraw.setPenRadius(.001);
		
		// 分配一个 N 段的数组
		int[] counter = new int[N];
		double[] sections = new double[N + 1];
		double average = (r - l) / N;
		for(int i = 0; i < N + 1; i++)
			sections[i] = l + average * i;
		
		// 在给自区间的桶中计数
		for(int i = 0; i < arr.length; i++) {
			double elem = arr[i];
			for(int j = 1; j < N + 1; j++) 
				if (elem > sections[j - 1] && elem <= sections[j])
					counter[j - 1]++;
		}
		
		// 绘制图像
		for(int i = 0; i < counter.length; i++) {
			double x = l + i * average;
			double y = counter[i];
			double rw = average / 2;
			double rh = y;
			StdDraw.filledRectangle(x, y, rw, rh);
		}	
	}
	/*
	 * 生成一个值在 l,r 之间的随机数组
	 */
	public static double[] randomArr(double l, double r) {
		int N = StdRandom.uniform(100);
		double[] arr = new double[N];
		for(int i = 0; i < N; i++)
			arr[i] = StdRandom.uniform(l, r);
		return arr;
	}

	public static void main(String[] args) {
		draw(randomArr(1, 3), 10, 1, 3);
	}
	// output : execute to see graph drawing
}
